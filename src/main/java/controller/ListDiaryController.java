package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Diary;
import model.dao.DiaryDAO;

public class ListDiaryController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		long id = -1; /* 사용자 primary key */
		
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
        } else {
        	request.setAttribute("hasLogin", true);
        	id = UserSessionUtils.getLoginUserPrimaryKey(request.getSession());
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
    	DiaryDAO diaryDao = new DiaryDAO();
		List<Diary> diaryList = diaryDao.findMonthlyDiary(id, "20221101", "20221201");
		
		// diaryList 객체를 request에 저장하여 음주 기록 리스트 화면으로 이동(forwarding)
		request.setAttribute("diaryList", diaryList);				
		return "/diary/list.jsp";        
    }
}
