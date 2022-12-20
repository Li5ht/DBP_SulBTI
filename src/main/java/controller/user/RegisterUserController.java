package controller.user;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	/* 로그인 여부 확인 (네비게이션 바를 위해서 넣은 것) */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			/* 로그인 X */
            request.setAttribute("noLogin", true);
        } else {
        	/* 로그인 O */
        	request.setAttribute("hasLogin", true);
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
    	
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");
		
			return "/user/registerForm.jsp";   // 검색한 커뮤니티 리스트를 registerForm으로 전송     	
	    }	

    	// POST request (회원정보가 parameter로 전송됨)
    	UserManager manager = UserManager.getInstance();
    	String userId = request.getParameter("userId");
       	Member user = manager.findUser(userId);
       	if (user != null) { // 이미 존재하는 아이디일 경우
       		request.setAttribute("registerFailed", true);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
       	}
       	user = new Member();
       	user.setUserId(userId);
       	user.setPassword(request.getParameter("password"));
       	user.setNickname(request.getParameter("nickname"));
       	user.setEmail(request.getParameter("email"));
       	user.setGender(Integer.parseInt(request.getParameter("gender")));
       	String year = request.getParameter("birth1");
        String month = request.getParameter("birth2");
        String day = request.getParameter("birth3");
        
        Date birthday = Date.valueOf(year+"-"+month+"-"+day);
        user.setBirth(birthday);
		
        log.debug("Create User : {}", user);

		try {
			
			manager.create(user);
	        return "redirect:/home/home";
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}

