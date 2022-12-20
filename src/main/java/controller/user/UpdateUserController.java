package controller.user;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.AlcoholManager;
import model.service.UserManager;
import model.*;

public class UpdateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	// 로그인 여부 확인 (네비게이션 바 때문에)
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
    		request.setAttribute("noLogin", true);
    		return "redirect:/user/login/form";		// login form 요청으로 redirect
        } else {
        	request.setAttribute("hasLogin", true);
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
    	
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 수정 form 요청	
    		String updateId = request.getParameter("userId");

    		log.debug("UpdateForm Request : {}", updateId);
    		
    		UserManager manager = UserManager.getInstance();
			Member user = manager.findUser(updateId);	// 수정하려는 사용자 정보 검색
			request.setAttribute("user", user);			

			HttpSession session = request.getSession();
			if (UserSessionUtils.isLoginUser(updateId, session) ||
				UserSessionUtils.isLoginUser("admin", session)) {
				// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
				
				/* 술 목록 받아오기 (주량 수정) */
				AlcoholManager alMan = AlcoholManager.getInstance();
				String[] aSoju = alMan.nameListByType("소주");
				String[] aBeer = alMan.nameListByType("맥주");
				String[] aTraditional = alMan.nameListByType("전통주");
				String[] aWine = alMan.nameListByType("와인");
				String[] aSpirits = alMan.nameListByType("양주");
				String[] aCocktail = alMan.nameListByType("칵테일");
				
				request.setAttribute("aSoju", aSoju);
		    	request.setAttribute("aBeer", aBeer);
		    	request.setAttribute("aTraditional", aTraditional);
		    	request.setAttribute("aWine", aWine);
		    	request.setAttribute("aSpirits", aSpirits);
		    	request.setAttribute("aCocktail", aCocktail);
				
				return "/user/updateForm.jsp";   // 검색한 사용자 정보를 updateForm으로 전송     
			}    
			
			// else (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", 
					new IllegalStateException("타인의 정보는 수정할 수 없습니다."));            
			return "/user/view.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
	    }	
    	
    	// POST request (회원정보가 parameter로 전송됨)
       	String year = request.getParameter("birth1");
        String month = request.getParameter("birth2");
        String day = request.getParameter("birth3");
        
        Date birthday = Date.valueOf(year+"-"+month+"-"+day);
        
        String alcoholType = request.getParameter("sel1");
        String alcoholName = request.getParameter("sel2");
        int amount = Integer.parseInt(request.getParameter("amount"));
        AlcoholManager alMan = AlcoholManager.getInstance();
        Alcohol alcohol = alMan.findAlcohol(alcoholType, alcoholName); // 술 정보 받아옴 (도수 확인용)
        Drink drink = new Drink(alcohol, amount);
        CalcDrinkingCapacity dc = new CalcDrinkingCapacity();
        float alcoholAmount = dc.theAmountOfAlcohol(drink);

        Member updateUser = new Member(
        	request.getParameter("userId"),
    		request.getParameter("password"),
    		request.getParameter("nickname"),
    		request.getParameter("email"),
    		request.getParameter("gender"),
    		birthday);
        updateUser.setDrinkingCapacity(alcoholAmount);

    	log.debug("Update User : {}", updateUser);

		UserManager manager = UserManager.getInstance();
		manager.update(updateUser);	
		request.setAttribute("user", updateUser);
        return "/user/view.jsp";			
    }
}