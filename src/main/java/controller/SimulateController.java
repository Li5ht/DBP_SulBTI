package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.*;
import model.*;

public class SimulateController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// UserManager userMan = UserManager.getInstance();
		// MemberDao mDao = new MemberDao();
		long drinkingCapacity = -1;
		/* 로그인 여부 확인
		if () { 
			// drinkingCapacity = mDao.getDrinking(유저아이디);
			// manager에서 접근하는 걸로 추후 변경
		} */
		
		request.setAttribute("drinkingCapacity", drinkingCapacity);
	
		if (request.getMethod().equals("GET")) {
			return "/simulate/simulateForm.jsp";
		}
		
		/*
		AlcoholManager alcoholMan = AlcoholManager.getInstance();
		List<Drink> drinkingList = alcoholMan.simulate(); 
		
		request.setAttribute("drinkingList", drinkingList); */
	}
}
