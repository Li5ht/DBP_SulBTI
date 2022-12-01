package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import controller.user.UserSessionUtils;


public class TestController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		/* 로그인 여부 확인 (현재 생략) 
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			String userId = UserSessionUtils.getLoginUserId(request.getSession());
			request.setAttribute("userId", userId);
			// 검사 후 testType 반영하기 위해
        } */


		if (request.getMethod().equals("GET")) { // testForm 요청
			return "/recommend/testForm.jsp";
		}

		// test 결과 요청
		String[] choice = {
				request.getParameter("1"), request.getParameter("2"), request.getParameter("3"), 
				request.getParameter("4"), request.getParameter("5"), request.getParameter("6"),
				request.getParameter("7"), request.getParameter("8"), 
				request.getParameter("9"), request.getParameter("10")
		};	// 사용자가 선택한 답
		
		// 테스트 결과 계산, 추후 수정
		String testType = "";
		
		
		// 사용자 DB testType에 추가
		
		
		// 결과 띄우기
		if (testType.equals("")) {
			return "/recommend/testResult/ENFP.jsp"; 
		} else if (testType.equals("")) {
			return "/recommend/testResult/ENFP.jsp";
		}
		return "/recommend/testResult/ENFP.jsp";
	}
}
