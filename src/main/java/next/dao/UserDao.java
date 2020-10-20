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
        jdbcTemplate.update2(sql, new PreparedStatementSetter() {
            @Override
            public void values(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getUserId());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getEmail());
            }
        });
    }

    public void update(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql =  "UPDATE USERS SET PASSWORD = ?, NAME = ?, EMAIL = ? WHERE USERID = ?";
        jdbcTemplate.update2(sql, new PreparedStatementSetter() {
            @Override
            public void values(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getUserId());
            }
        });
    }

    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS";

        return jdbcTemplate.query(sql,
                new PreparedStatementSetter() {
                    @Override
                    public void values(PreparedStatement pstmt) throws SQLException {

                    }
                },
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs) throws SQLException {
                        return new User(rs.getString("userId"),
                                        rs.getString("password"),
                                        rs.getString("name"),
                                        rs.getString("email"));
                    }
        }) ;

    }

    public User findByUserId(String userId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS";

        return jdbcTemplate.queryForObject(sql,
                new PreparedStatementSetter() {
                    @Override
                    public void values(PreparedStatement pstmt) throws SQLException {
                        pstmt.setString(1, userId);
                    }
                },
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs) throws SQLException {
                        return new User(rs.getString("userId"),
                                    rs.getString("password"),
                                    rs.getString("name"),
                                    rs.getString("email"));
                        }
                    }
        );
    }
}
