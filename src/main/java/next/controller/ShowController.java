package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        req.setAttribute("question", questionDao.findByQuestionId(questionId));
        req.setAttribute("answers", answerDao.findByQuestionId(questionId));
        return "/qna/show.jsp";
    }
}
