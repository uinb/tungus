package tech.uinb.tungus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.entity.TransferExt;
import tech.uinb.tungus.entity.po.PagePO;
import tech.uinb.tungus.entity.vo.PageDataVO;
import tech.uinb.tungus.repository.SeqRepository;
import tech.uinb.tungus.repository.StashExtRepository;
import tech.uinb.tungus.repository.TransferExtRepository;
import tech.uinb.tungus.service.StashExtService;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.service.TransferExtService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TransferExtServiceImpl implements TransferExtService {
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private TransferExtRepository transferExtRepository;
    @Autowired
    private SeqRepository seqRepository;
    private LongSeqSplitter splitter;

    @Override
    public long save(long extId) {
        var seq = seqRepository.queryByPrefix(TableMetaService.TRANSFER_EXTRINSIC);
        var id = seq.incrementAndGet();
        var table = splitter.computeTable(id);
        transferExtRepository.save(id, extId, table.tableName());
        seqRepository.update(seq);
        return id;
    }

    @Override
    public PageDataVO<TransferExt> query(PagePO condition) {
        long end = condition.getSize().longValue() * condition.getPage();
        long start = end - condition.getSize();
        int startSuffix = splitter.computeTable(start).getSuffix();
        int endSuffix = splitter.computeTable(end).getSuffix();
        List<TransferExt> data = new ArrayList<>(condition.getSize());

        for (int i = startSuffix; i <= endSuffix; i++) {
            var table = splitter.getBySuffix(i);
            data.addAll(transferExtRepository.query(start, end, table.tableName()));
        }

        var seq = seqRepository.queryByPrefix(TableMetaService.TRANSFER_EXTRINSIC);
        long total = seq.getValue();
        PageDataVO<TransferExt> result = PageDataVO.valueOf(data, condition.getPage(), condition.getSize(), total);
        return result;
    }


    @PostConstruct
    public void init() {
        splitter = new LongSeqSplitter(tableMetaService.getByPrefix(TableMetaService.TRANSFER_EXTRINSIC));
    }
}
