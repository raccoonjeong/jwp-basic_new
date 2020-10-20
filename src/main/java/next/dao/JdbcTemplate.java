package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public void update2(String sql, PreparedStatementSetter pstmtSetter) throws DataAccessException {

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.values(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws DataAccessException {

        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pss.values(pstmt);
            rs = pstmt.executeQuery();

            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DataAccessException(e);
                }
            }
        }
    }

    public <T> T queryForObject(String sql,PreparedStatementSetter pss, RowMapper<T> rowMapper) {
       List<T> result = query(sql, pss, rowMapper);
       if (result.isEmpty()) {
           return null;
       }
       return result.get(0);
    }
}
