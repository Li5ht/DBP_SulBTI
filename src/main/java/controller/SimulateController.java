package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.user.UserSessionUtils;
import model.dao.*;
import model.service.*;
import model.*;

public class SimulateController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		 UserManager userMan = UserManager.getInstance();
		long drinkingCapacity = -1; /* 사용자의 주량 (-1 : 사용자 주량 모름) */
		long id = -1; /* 사용자의 primary key */
		
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
        } else {
        	request.setAttribute("hasLogin", true);
        	id = UserSessionUtils.getLoginUserPrimaryKey(request.getSession());
        	drinkingCapacity = userMan.getDrinking(id);
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		request.setAttribute("drinkingCapacity", drinkingCapacity);
		
		/* 술 목록 받아오기 */
		AlcoholDAO alcoholDao = new AlcoholDAO();
		List<Alcohol> aSoju = alcoholDao.listByType("소주");
		List<Alcohol> aBeer = alcoholDao.listByType("맥주");
		
		request.setAttribute("aSoju", aSoju);
		request.setAttribute("aBeer", aBeer);
	
		if (request.getServletPath().equals("/simulate/result")) {
			/* 시뮬레이터 결과 페이지 */
			
			
		}
		
		/* 시뮬레이터 페이지 */
		return "/simulate/simulateForm.jsp";
	}
}
