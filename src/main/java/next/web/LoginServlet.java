package next.web;

import core.db.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId") == null ? "" : req.getParameter("userId");
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");
        log.debug("userId: {}, password: {}", userId, password);

        if("".equals(userId) || "".equals(password)) {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.html");
            rd.forward(req, resp);
        }

        if (DataBase.findUserById(userId) != null && password.equals(DataBase.findUserById(userId).getPassword())) {
            log.debug("로그인 성공");

            resp.sendRedirect("/");
        } else {
            log.debug("로그인 실패");
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.html");
            rd.forward(req, resp);
        }

    }
}
