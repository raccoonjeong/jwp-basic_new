package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//
//@WebServlet("/user/login")
public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if("GET".equals(request.getMethod())) {
            return "/user/login.jsp";

        } else if ("POST".equals(request.getMethod())) {
        String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
        String password = request.getParameter("password") == null ? "" : request.getParameter("password");
        log.debug("userId: {}, password: {}", userId, password);

            if("".equals(userId) || "".equals(password)) {
                return "/user/login_failed.jsp";

            }

            User user = DataBase.findUserById(userId);
            if ( user != null && password.equals(user.getPassword())) {
                log.debug("로그인 성공");

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                return "redirect:/";
            } else {
                log.debug("로그인 실패");
                return "/user/login_failed.jsp";
            }
        }
        return null;
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
//        rd.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String userId = req.getParameter("userId") == null ? "" : req.getParameter("userId");
//        String password = req.getParameter("password") == null ? "" : req.getParameter("password");
//        log.debug("userId: {}, password: {}", userId, password);
//
//        if("".equals(userId) || "".equals(password)) {
//            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
//            rd.forward(req, resp);
//        }
//
//        User user = DataBase.findUserById(userId);
//        if ( user != null && password.equals(user.getPassword())) {
//            log.debug("로그인 성공");
//
//            HttpSession session = req.getSession();
//            session.setAttribute("user", user);
//
////            req.setAttribute("sessonScope", session);
////            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
////            rd.forward(req, resp);
//
//            resp.sendRedirect("/");
//        } else {
//            log.debug("로그인 실패");
//            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
//            rd.forward(req, resp);
//        }
//
//    }
}
