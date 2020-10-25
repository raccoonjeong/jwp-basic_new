package next.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;
import next.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    public List<Answer> findByQuestionId(Long questionId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        String sql = "SELECT answerId, writer, contents, createdDate, " +
                "questionId FROM ANSWERS where questionId = ?";
        RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(
                        rs.getLong("answerId"),
                        rs.getString("writer"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getLong("questionId"));
            }
        };

        return jdbcTemplate.query(sql, rm, questionId);
    }
}
