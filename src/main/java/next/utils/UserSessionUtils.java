package next.utils;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static boolean isLogined(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user == null) {
            return false;
        }
        return true;
    }
}
