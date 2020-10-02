package next.web;

import next.controller.Controller;
import next.controller.CreateUserController;
import next.controller.ForwardController;
import next.controller.HomeController;
import next.controller.ListUserController;
import next.controller.LoginController;
import next.controller.LogoutController;
import next.controller.UpdateUserFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private static Map<String, Controller> mappings = new HashMap<>();

    void initMapping() {

        mappings.put("/", new HomeController());

        mappings.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/user/list", new ListUserController());

        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());

        mappings.put("/user/form", new ForwardController("/user/form.jsp"));
        mappings.put("/user/create", new CreateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
        mappings.put("/user/update", new UpdateUserFormController());

        logger.info("initialized Request Mapping!");
    }

    public Controller findController(String url) {
        return mappings.get(url);
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }

}
