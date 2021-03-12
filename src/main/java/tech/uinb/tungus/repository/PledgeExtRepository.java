package tech.uinb.tungus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

@Repository
public class PledgeExtRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(long id, long extId, String tableName) {
        template.execute("insert into " + tableName + " (id, ext_id) values (?, ?)",
                (PreparedStatementCallback<Void>) ps -> {
                    ps.setLong(1, id);
                    ps.setLong(2, extId);
                    ps.execute();
                    return null;
                });
    }
}
