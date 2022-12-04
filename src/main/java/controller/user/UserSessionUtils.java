package controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "userId";
    public static final String USER_PRIMARY_KEY = "id";
    public static final String USER_NICKNAME = "nickname";

    /* 현재 로그인한 사용자의 ID를 구함 */
    public static String getLoginUserId(HttpSession session) {
        String userId = (String)session.getAttribute(USER_SESSION_KEY);
        return userId;
    }
    
    /* 현재 로그인한 사용자의 primary key를 구함 */
    public static long getLoginUserPrimaryKey(HttpSession session) {
    	long id = (long) session.getAttribute(USER_PRIMARY_KEY);
    	return id;
    }
    
    /* 현재 로그인한 사용자의 nickname을 구함 */
    public static String getLoginUserNickname(HttpSession session) {
    	return (String)session.getAttribute(USER_NICKNAME);
    }

    /* 로그인한 상태인지를 검사 */
    public static boolean hasLogined(HttpSession session) {
        if (getLoginUserId(session) != null) {
            return true;
        }
        return false;
    }

    /* 현재 로그인한 사용자의 ID가 userId인지 검사 */
    public static boolean isLoginUser(String userId, HttpSession session) {
        String loginUser = getLoginUserId(session);
        if (loginUser == null) {
            return false;
        }
        return loginUser.equals(userId);
    }
}
