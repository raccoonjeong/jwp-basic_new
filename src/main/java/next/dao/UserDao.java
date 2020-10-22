package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    public void insert(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";

        jdbcTemplate.update2(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql =  "UPDATE USERS SET PASSWORD = ?, NAME = ?, EMAIL = ? WHERE USERID = ?";

        jdbcTemplate.update2(sql,  user.getPassword(),
        user.getName(),
        user.getEmail(),
        user.getUserId());
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS";
        PreparedStatementSetter pss =  new PreparedStatementSetter() {
            @Override
            public void values(PreparedStatement pstmt) throws SQLException {

            }
        };

        RowMapper rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        };
        return jdbcTemplate.query(sql, pss, rm);

    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID = ?";


        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override
            public void values(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, userId);
            }
        };
//        RowMapper<User> rm = new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs) throws SQLException {
//                return new User(rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"));
//            }
//        };

        return jdbcTemplate.queryForObject(sql, pss,
                (ResultSet rs) ->
                new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"))
        );
    }
}
