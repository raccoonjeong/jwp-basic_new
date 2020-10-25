package next.dao;

import core.jdbc.ConnectionManager;
import next.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import static org.junit.Assert.assertEquals;

import java.util.List;

public class QuestionDaoTest {
    @Before
    public void setup() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("jwp.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    public void listQuestionTest() {
        QuestionDao questionDao = new QuestionDao();
        List<Question> result = questionDao.listQuestions();
        assertEquals(8, result.size());
    }

    @Test
    public void findByQuestionIdTest() {
        QuestionDao questionDao = new QuestionDao();
        Question result = questionDao.findByQuestionId(8L);
        assertEquals("자바지기", result.getWriter());
    }
}
