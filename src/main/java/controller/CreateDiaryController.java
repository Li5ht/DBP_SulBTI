package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.*;
import model.dao.*;

public class CreateDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateDiaryController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Diary diary = new Diary();		
    	// diary 내용 받아오기 
        
		try {
			DiaryDAO diaryDao = new DiaryDAO();
			diaryDao.createDiary(diary);
			
	    	log.debug("Create Diary : {}", diary);
	        return "redirect:/diary/list";	// 성공 시 음주 기록 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("diary", diary);
			return "/diary/registerForm.jsp";
		}
    }
}
