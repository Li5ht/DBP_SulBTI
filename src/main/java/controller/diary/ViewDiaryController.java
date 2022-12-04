package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.dao.*;
import model.service.DiaryManager;

public class ViewDiaryController implements Controller {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
	        request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
	        
	    	Diary diary = null;
	    	DiaryManager dManager = DiaryManager.getInstance();
			long diaryId = Long.parseLong(request.getParameter("diaryId"));
			diary = dManager.findDiary(diaryId);// 음주 기록 일별 조회	
			
			if (diary != null) {
				request.setAttribute("diary", diary);	// 음주 기록 저장	
			}		
			return "/diary/detail.jsp";				// 음주 기록 보기 화면으로 이동
	    }
}
