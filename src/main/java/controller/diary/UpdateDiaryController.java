package controller.diary;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.dao.*;
import model.service.*;

public class UpdateDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateDiaryController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	long id = -1; /* 사용자 primary key */
    	String userId = null; /* 사용자가 회원가입 시 입력한 아이디 */
    	/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
			return "redirect:/user/login/form";
        } else {
        	request.setAttribute("hasLogin", true);
        	id = UserSessionUtils.getLoginUserPrimaryKey(request.getSession());
        	userId = UserSessionUtils.getLoginUserId(request.getSession());
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		
		Diary diary = null;
    	long diaryId = Long.parseLong(request.getParameter("diaryId"));
    	DiaryManager dManager = DiaryManager.getInstance();
    	diary = dManager.findDiary(diaryId);
    	
    	
		if (request.getMethod().equals("GET")) {	
    		// GET request: 음주 기록 수정 form 요청	
			DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
	    	String drinkingDate = df.format(diary.getDrinkingDate());
	    	drinkingDate = drinkingDate.substring(2, drinkingDate.length());
	        
	    	// 술 목록 받아오기
	    	AlcoholDAO alcoholDao = new AlcoholDAO();
			List<Alcohol> alcoholList = alcoholDao.viewAlcoholList();
			
			request.setAttribute("drinkingDate", drinkingDate);
			request.setAttribute("alcoholList", alcoholList);	
			request.setAttribute("diary", diary);			
			
			return "/diary/updateForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
    	// POST request (음주 기록 정보가 parameter로 전송됨)
		UserManager uManager = UserManager.getInstance();
    	Member member = uManager.findUserByPrimaryKey(diary.getMember().getId());
    	diary.setMember(member);
    	diary.setCondition(Integer.parseInt(request.getParameter("condition")));
    	diary.setContent(request.getParameter("content"));
    	log.debug("Update Diary : {}", diary);

    	dManager.update(diary);
    	
        return "redirect:/diary/list";			
    }
}
