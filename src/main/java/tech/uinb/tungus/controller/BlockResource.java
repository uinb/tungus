package tech.uinb.tungus.controller;

import static tech.uinb.tungus.entity.ObjType.ACCOUNT;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.writer.ListWriter;
import io.emeraldpay.polkaj.types.ByteData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.uinb.tungus.codec.EventRecord;
import tech.uinb.tungus.codec.EventWriter;
import tech.uinb.tungus.config.Constants;
import tech.uinb.tungus.entity.Ext;
import tech.uinb.tungus.entity.HashIdMap;
import tech.uinb.tungus.entity.TransferExt;
import tech.uinb.tungus.entity.po.AccountScanPO;
import tech.uinb.tungus.entity.po.PagePO;
import tech.uinb.tungus.entity.vo.PageDataVO;
import tech.uinb.tungus.service.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import tech.uinb.tungus.service.impl.ScheduleServiceImpl;

/**
 * REST controller for query some block info .
 */
@Api("web/api")
@RestController
@RequestMapping("/api/v1/fusotao")
public class BlockResource {
    private final Logger log = LoggerFactory.getLogger(BlockResource.class);

    @Autowired
    private HashIdMapService hashIdMapService;
    @Autowired
    private BlockService blockService;
    @Autowired
    private BlockExtService blockExtService;
    @Autowired
    private AccountExtrinsicService accountExtrinsicService;
    @Autowired
    private AccountTransactionService accountTransactionService;
    @Autowired
    private AccountStashService accountStashService;
    @Autowired
    private ScheduleServiceImpl scheduleService;
    @Autowired
    private ExtrinsicEventService extrinsicEventService;
    @Autowired
    private EventDataService eventDataService;
    @Autowired
    private TransferExtService transferExtService;

    @ApiOperation(value = "search", httpMethod = "GET")
    @GetMapping("/search/{hash}")
    public ResponseEntity search(@PathVariable String hash) {
        HashIdMap hashIdMap = hashIdMapService.getByHash(hash);
        if (hashIdMap == null) {
            if (StringUtils.isNumeric(hash)) {
                hashIdMap = hashIdMapService.getByBlockNumber(Long.valueOf(hash));
            }
        }
        if (hashIdMap != null) {
            Map resultMap = new HashMap();
            switch (hashIdMap.getType()) {
                case BLOCK:
                    resultMap.put("type", Constants.TYPE_BLOCK);
                    resultMap.put("hash", hashIdMap.getHash());
                    break;
                case EXTRINSICS:
                    Ext ext = blockService.getExtById(hashIdMap.getId());
                    String index = blockExtService.getExtIndexInBlockByExtId(ext.getId());
                    resultMap.put("data", new ByteData(ext.getData()).toString());
                    resultMap.put("type", Constants.TYPE_CALLABLE);
                    resultMap.put("index", index);
                    break;
                case ACCOUNT:
                    resultMap.put("type", Constants.TYPE_ACCOUNT);
                    break;
            }
            return new ResponseEntity(resultMap, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "scan", httpMethod = "POST")
    @PostMapping("/scan/{type}")
    public ResponseEntity scan(@PathVariable String type, @RequestBody AccountScanPO accountScanPO)
            throws IOException {
        HashIdMap hashIdMap = hashIdMapService.getByHash(accountScanPO.getAccount());
        if (hashIdMap == null || hashIdMap.getType() != ACCOUNT || !accountScanPO.check()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<Long> extIds = new ArrayList<>();
        switch (type) {
            case Constants.TYPE_CALLABLE:
                extIds = accountExtrinsicService.getExtIdsByAccount(hashIdMap.getId());
                break;
            case Constants.TYPE_TRANSFER:
                extIds = accountTransactionService.getTransferIdsByAccount(hashIdMap.getId());
                break;
            case Constants.TYPE_STASH:
                extIds = accountStashService.getStashIdsByAccount(hashIdMap.getId());
                break;
            default:
                return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List result = new ArrayList<>();

        long t1 = System.currentTimeMillis();


        for (int i = 0; i < extIds.size(); i++) {
            if (accountScanPO.getSize() * (accountScanPO.getPage() - 1) <= i && i < accountScanPO.getSize() * (accountScanPO.getPage())) {
                Ext ext = blockService.getExtById(extIds.get(i));
                long tt1 = System.currentTimeMillis();

                List<EventRecord> events = new ArrayList();
                List<Long> list = extrinsicEventService.getEventIdByExtId(ext.getId());
                list.forEach(id -> {
                    events.add(scheduleService.decodeEvent(eventDataService.getEventDataById(id).getData()));
                });

                long firstExtId = blockExtService.getFirstExtIdByExtId(ext.getId());
                Ext ext_1st = blockService.getExtById(firstExtId);

                ListWriter listWriter = new ListWriter(scheduleService.getEventWriter().get());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                listWriter.write(new ScaleCodecWriter(out), events);

                long tt2 = System.currentTimeMillis();

                result.add(Map.of(
                        "ext1st", new ByteData(ext_1st.getData()).toString(),
                        "ext", new ByteData(ext.getData()).toString(),
                        "extIndex", blockExtService.getExtIndexInBlockByExtId(extIds.get(i)),
                        "events", new ByteData(out.toByteArray()).toString(),
                        "block", blockExtService.getBlockIdByExtId(extIds.get(i))
                ));
                System.out.println(tt2 - tt1 + ":耗时");
            }
        }
        System.out.println(System.currentTimeMillis() - t1);
        return new ResponseEntity(PageDataVO.valueOf(result, accountScanPO.getPage(), accountScanPO.getSize(), extIds.size()), HttpStatus.OK);
    }

    @ApiOperation(value = "record", httpMethod = "POST")
    @PostMapping("/record/{type}")
    public ResponseEntity record(@PathVariable String type, @RequestBody PagePO pagePO) {
        PageDataVO pageDataVO = null;
        switch (type) {
            case Constants.TYPE_BLOCK:
                var lastNumber = blockService.lastBlockNumber();
                for (long i = lastNumber - pagePO.getSize() * (pagePO.getPage() - 1); i > lastNumber - pagePO.getSize() * (pagePO.getPage()); i--) {
                    var blockHeader = blockService.getBlockHeaderById(i);


                }
                break;
            case Constants.TYPE_TRANSFER:
                break;
            case Constants.TYPE_STASH:
                break;
            default:
                return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity(pageDataVO, HttpStatus.OK);
    }

    public ResponseEntity getTransfer(PagePO pageable) {
        var page = transferExtService.query(pageable);
        List<Ext> data = page.getList().stream()
                .map(item -> blockService.getExtById(item.getId()))
                .collect(Collectors.toList());
        var result = PageDataVO.valueOf(data, pageable.getPage(), pageable.getSize(), page.getTotal());
        return new ResponseEntity(result, HttpStatus.OK);
    }

//    @ApiOperation(value = "statistics", httpMethod = "GET")
//    @GetMapping("/statistics")
//    public ResponseEntity statistics(){
//
//        return new ResponseEntity(pageDataVO,HttpStatus.OK);
//    }

}

