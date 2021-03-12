package tech.uinb.tungus.service;

import org.springframework.data.domain.Page;
import tech.uinb.tungus.entity.TransferExt;
import tech.uinb.tungus.entity.po.PagePO;
import tech.uinb.tungus.entity.vo.PageDataVO;

public interface TransferExtService {
    long save(long extId);

    PageDataVO<TransferExt> query(PagePO condition);
}
