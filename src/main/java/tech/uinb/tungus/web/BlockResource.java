package tech.uinb.tungus.web;

import io.emeraldpay.polkaj.types.ByteData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.uinb.tungus.config.Constants;
import tech.uinb.tungus.entity.Ext;
import tech.uinb.tungus.entity.HashIdMap;
import tech.uinb.tungus.service.BlockExtService;
import tech.uinb.tungus.service.BlockService;
import tech.uinb.tungus.service.HashIdMapService;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for query some block info .
 */
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

    @GetMapping("/search/{hash}")
    public ResponseEntity search(@PathVariable String hash){
        HashIdMap hashIdMap = hashIdMapService.getByHash(hash);
        if (hashIdMap == null){
            if (StringUtils.isNumeric(hash)){
                hashIdMap = hashIdMapService.getByBlockNumber(Long.valueOf(hash));
            }
        }
        if (hashIdMap != null){
            Map resultMap = new HashMap();
            switch (hashIdMap.getType()){
                case BLOCK:
                    resultMap.put("type", Constants.TYPE_BLOCK);
                    resultMap.put("hash",hashIdMap.getHash());
                    break;
                case EXTRINSICS:
                    Ext ext = blockService.getExtById(hashIdMap.getId());
                    long index = blockExtService.getExtIndexInBlockByExtId(ext.getId());
                    resultMap.put("data",new ByteData(ext.getData()).toString());
                    resultMap.put("type",Constants.TYPE_CALLABLE);
                    resultMap.put("index",index);
                    break;
                case ACCOUNT:
                    resultMap.put("type",Constants.TYPE_ACCOUNT);
                    break;
            }
            return new ResponseEntity(resultMap,HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }




}
