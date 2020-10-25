package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Answer;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnswerDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void findByQuestionIdTest() {
        AnswerDao answerDao = new AnswerDao();
        List<Answer> result = answerDao.findByQuestionId(8L);
        assertEquals(3, result.size());
    }
}
