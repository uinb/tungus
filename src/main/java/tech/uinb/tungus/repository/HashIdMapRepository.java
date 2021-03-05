package tech.uinb.tungus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.HashIdMap;
import tech.uinb.tungus.entity.ObjType;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HashIdMapRepository {
    @Autowired
    private JdbcTemplate template;
    private HashIdMapResultSetExtractor extractor = new HashIdMapResultSetExtractor();

    public void save(String hash, long id, ObjType type, String tableName) {
        template.execute((ConnectionCallback<Object>) con -> {
            var stat = con.prepareStatement("insert into " + tableName + " (hash, id, type) values (?, ?, ?)");
            stat.setString(1, hash);
            stat.setLong(2, id);
            stat.setString(3, type.name());
            stat.execute();
            return null;
        });
    }

    public HashIdMap queryByHash(String hash, String tableName) {
        return template.query(con -> {
            var stat = con.prepareStatement("select * from " + tableName + " where hash = ?");
            stat.setString(1, hash);
            return stat;
        }, extractor);
    }

    public HashIdMap queryByBlockNumber(long id, String tableName) {
        return template.query(con -> {
            var stat = con.prepareStatement("select * from " + tableName + " where id = ? and type = 'BLOCK' ");
            stat.setLong(1, id);
            return stat;
        }, extractor);
    }

    private class HashIdMapResultSetExtractor implements ResultSetExtractor<HashIdMap> {
        @Override
        public HashIdMap extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                var result = new HashIdMap();
                result.setHash(rs.getString("hash"));
                result.setId(rs.getLong("id"));
                result.setType(ObjType.valueOf(rs.getString("type")));
                return result;
            }
            return null;
        }
    }
}
