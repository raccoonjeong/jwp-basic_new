package next.controller;

import core.db.DataBase;
import next.utils.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    private static final long serialVersionUID = 1L;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login";
        }
        request.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession httpSession = req.getSession();
//        Object value = httpSession.getAttribute("user");
//
//        if (value == null) {
//            resp.sendRedirect("/user/login");
//            return;
//        }
//
//        req.setAttribute("users", DataBase.findAll());
//        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
//        rd.forward(req, resp);
//    }
}
