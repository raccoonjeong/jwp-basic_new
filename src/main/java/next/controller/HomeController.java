package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import core.mvc.View;
import next.dao.QuestionDao;
import next.dao.UserDao;

public class HomeController extends AbstractController {
    private UserDao userDao = new UserDao();
    private QuestionDao questionDao = new QuestionDao();
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

//        req.setAttribute("users", userDao.findAll());
//        QuestionDao questionDao = new QuestionDao();
//        req.setAttribute("questions", questionDao.listQuestions());
        // return new JspView("home.jsp");
        return jspView("home.jsp")
                .addObject("users", userDao.findAll())
                .addObject("questions", questionDao.listQuestions());

    }
}
