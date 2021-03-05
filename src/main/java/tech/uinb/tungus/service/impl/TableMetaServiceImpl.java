package tech.uinb.tungus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.entity.TableMeta;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.repository.TableMetaRepository;

@Service
@Transactional
public class TableMetaServiceImpl implements TableMetaService {
    @Autowired
    private TableMetaRepository tableMetaRepository;

    @Override
    public TableMeta getByPrefix(String prefix) {
        return tableMetaRepository.getByPrefix(prefix);
    }
}
