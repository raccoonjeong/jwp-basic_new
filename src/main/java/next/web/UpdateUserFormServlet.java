package next.web;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        User loginedUser = (User)value;

        System.out.println("예예");

        String parameterUserId = req.getParameter("userId");

        if (!parameterUserId.equals(loginedUser.getUserId())) {
            resp.sendRedirect("/user/list");
            return;
        }

        User user = DataBase.findUserById(parameterUserId);

        log.debug("update 하기 전의 userId : {}", parameterUserId);

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");
        rd.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        log.debug("update한 후의 user : {}", user);

        DataBase.addUser(user);

        resp.sendRedirect("/user/list");

    }
}
