package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.*;
import model.dao.*;
import model.service.*;

public class UpdateDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateDiaryController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Diary diary = null;
    	long diaryId = Long.parseLong(request.getParameter("diaryId"));
		DiaryDAO diaryDao = new DiaryDAO();
		diary = diaryDao.getDiary(diaryId);	
		if (request.getMethod().equals("GET")) {	
    		// GET request: 음주 기록 수정 form 요청	
			request.setAttribute("diary", diary);			
			
			return "/diary/updateForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
    	// POST request (음주 기록 정보가 parameter로 전송됨)
		// 술 마신 날짜
		// 마신 술 내역
    	diary.setCondition(Integer.parseInt(request.getParameter("condition")));
    	diary.setContent(request.getParameter("content"));
    	log.debug("Update Diary : {}", diary);

    	diaryDao.updateDiary(diary);
    	
        return "redirect:/diary/list";			
    }
}
