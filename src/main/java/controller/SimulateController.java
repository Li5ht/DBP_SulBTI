package controller;

import java.util.ArrayList;
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
			
			// 리퀘스트 파라미터로 사용자 주량 알아내기 (술 정보 받아오기)
			String userAlcoholType = request.getParameter("sel1_1");
			String userAlcoholName = request.getParameter("sel2_1");
			int userAmount = Integer.parseInt(request.getParameter("amount1"));
			Alcohol userAlcohol = alcoholDao.findAlcohol(userAlcoholType, userAlcoholName); // -> 도수 알아내는 용도!!
			Drink userDC = new Drink(userAlcohol, userAmount);
			
			// 잘 되는지 result.jsp에서 확인 용
			request.setAttribute("userDC", userDC);
			
			// 리퀘스트 파라미터로 술에 대한 정보 받아오기
			int count = Integer.parseInt(request.getParameter("count"));
			List<Drink> drinkingList = new ArrayList<Drink>(); 	// Drink 저장
			for (int i = 1; i <= count; i++) {
				String str = "drink"+Integer.toString(i);
				String drinkStr = request.getParameter(str);
				
				String[] arr = drinkStr.split("/"); 	// arr[0] = 주종, arr[1] = 술 이름, arr[2] = 양
				
				Alcohol alcohol = alcoholDao.findAlcohol(arr[0], arr[1]);
				Drink drink = new Drink(alcohol, Integer.parseInt(arr[2]));
				
				drinkingList.add(drink);
			}
			
			request.setAttribute("drinkingList", drinkingList);
			
			/* 주량 계산 (상태 반환) */
			CalcDrinkingCapacity calcDC = new CalcDrinkingCapacity(userDC, drinkingList);
			int condition = calcDC.calculate();
			
			request.setAttribute("condition", Integer.toString(condition));
			
			return "/simulate/result.jsp";
		}
		
		/* 시뮬레이터 페이지 */
		return "/simulate/simulateForm.jsp";
	}
}
