package controller.diary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.*;
import model.service.*;

public class DeleteDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeleteDiaryController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Diary diary = null;
    	long diaryId = Long.parseLong(request.getParameter("diaryId"));
    	DiaryManager dManager = DiaryManager.getInstance();
    	diary = dManager.findDiary(diaryId);

    	UserManager uManager = UserManager.getInstance();
    	Member member = uManager.findUserByPrimaryKey(diary.getMember().getId());
    	diary.setMember(member);
    	
    	dManager.deleteDiary(diary);
    	
        return "redirect:/diary/list";			
    }
}
