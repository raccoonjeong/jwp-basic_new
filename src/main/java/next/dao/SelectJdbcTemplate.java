package next.dao;

import core.jdbc.ConnectionManager;
import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SelectJdbcTemplate {
    public Object select(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            // String sql = "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            return mapRow(rs);
//            List<User> userList = new ArrayList<>();
//            if (rs.next()) {
//                userList.add(new User(rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email")));
//            }
            // return userList;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }

//
//            User user = null;
//
//            if (rs.next()) {
//                user = new User(rs.getString("userId"),
//                        rs.getString("password"),
//                        rs.getString("name"),
//                        rs.getString("email"));
//            }
//
//            // return user;
//
//        } finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//
//            if (con != null) {
//                con.close();
//            }
//
//            if (rs != null) {
//                rs.close();
//            }
//        }
    }
    // abstract void setValues(PreparedStatement pstmt) throws SQLException;
    abstract Object mapRow(ResultSet rs) throws SQLException;

}
