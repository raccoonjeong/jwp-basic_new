package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateUserController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if("GET".equals(request.getMethod())) {
            return "/user/form.jsp";
        } else if ("POST".equals(request.getMethod())) {
            User user = new User(request.getParameter("userId"), request.getParameter("password"),
                    request.getParameter("name"),
                    request.getParameter("email"));
            log.debug("user : {}", user);
            DataBase.addUser(user);
            return "/";
        }
        return null;
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");
//        rd.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
//                req.getParameter("email"));
//        log.debug("user : {}", user);
//        DataBase.addUser(user);
//        resp.sendRedirect("/");
//    }


}
