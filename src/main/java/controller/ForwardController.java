package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.user.UserSessionUtils;

public class ForwardController implements Controller {
    private String forwardUrl;

    public ForwardController(String forwardUrl) {
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
        this.forwardUrl = forwardUrl;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	if (!UserSessionUtils.hasLogined(req.getSession())) {
    		req.setAttribute("noLogin", true);
        } else {
        	req.setAttribute("hasLogin", true);
        }
    	
    	return forwardUrl;
    }
}
