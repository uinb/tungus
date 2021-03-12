package tech.uinb.tungus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.repository.PledgeExtRepository;
import tech.uinb.tungus.repository.SeqRepository;
import tech.uinb.tungus.repository.StashExtRepository;
import tech.uinb.tungus.service.PledgeExtService;
import tech.uinb.tungus.service.StashExtService;
import tech.uinb.tungus.service.TableMetaService;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class PledgeExtServiceImpl implements PledgeExtService {
    @Autowired
    private TableMetaService tableMetaService;
    @Autowired
    private PledgeExtRepository pledgeExtRepository;
    @Autowired
    private SeqRepository seqRepository;
    private LongSeqSplitter splitter;

    @Override
    public long save(long extId) {
        var seq = seqRepository.queryByPrefix(TableMetaService.PLEDGE_EXTRINSIC);
        var id = seq.incrementAndGet();
        var table = splitter.computeTable(id);
        pledgeExtRepository.save(id, extId, table.tableName());
        return id;
    }

    @PostConstruct
    public void init() {
        splitter = new LongSeqSplitter(tableMetaService.getByPrefix(TableMetaService.PLEDGE_EXTRINSIC));
    }
}
