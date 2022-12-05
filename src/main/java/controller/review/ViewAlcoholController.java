package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

public class ViewAlcoholController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			/* 로그인 X */
            request.setAttribute("noLogin", true);
        } else {
        	/* 로그인 O */
        	request.setAttribute("hasLogin", true);
			request.setAttribute("userId", UserSessionUtils.getLoginUserId(request.getSession()));
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		
		return "/review/productInfo.jsp";
	}
}
