package tech.uinb.tungus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.TransferExt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransferExtRepository {
    @Autowired
    private JdbcTemplate template;
    private TransferExtRowMapper mapper = new TransferExtRowMapper();

    public void save(long id, long extId, String tableName) {
        template.execute("insert into " + tableName + " (id, ext_id) values (?, ?)",
                (PreparedStatementCallback<Void>) ps -> {
                    ps.setLong(1, id);
                    ps.setLong(2, extId);
                    ps.execute();
                    return null;
                });
    }

    public List<TransferExt> query(long start, long end, String tableName) {
        return template.query(con -> {
            var stat = con.prepareStatement("select * from " + tableName + " where id >= ? and id <= ?");
            stat.setLong(1, start);
            stat.setLong(2, end);
            return stat;
        }, mapper);
    }

    private class TransferExtRowMapper implements RowMapper<TransferExt> {
        @Override
        public TransferExt mapRow(ResultSet rs, int rowNum) throws SQLException {
            var result = new TransferExt();
            result.setId(rs.getLong("id"));
            result.setExtId(rs.getLong("ext_id"));
            return result;
        }
    }
}
