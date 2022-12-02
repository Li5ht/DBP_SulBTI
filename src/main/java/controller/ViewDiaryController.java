package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Diary;
import model.dao.DiaryDAO;

public class ViewDiaryController implements Controller {
	 @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
	        request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));

	    	Diary diary = null;
	    	DiaryDAO diaryDao = new DiaryDAO();
			long diaryId = Long.parseLong(request.getParameter("diaryId"));
			diary = diaryDao.getDiary(diaryId);	// 음주 기록 일별 조회	
			
			if (diary != null) {
				request.setAttribute("diary", diary);	// 음주 기록 저장	
			}		
			return "/diary/detail.jsp";				// 음주 기록 보기 화면으로 이동
	    }
}
