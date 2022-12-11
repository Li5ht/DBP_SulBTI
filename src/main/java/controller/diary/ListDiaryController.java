package controller.diary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.dao.*;
import model.service.DiaryManager;

public class ListDiaryController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		long id = -1; /* 사용자 primary key */
		
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
			return "redirect:/user/login/form";
        } else {
        	request.setAttribute("hasLogin", true);
        	id = UserSessionUtils.getLoginUserPrimaryKey(request.getSession());
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		// 현재 날짜 계산
		DiaryManager dManager = DiaryManager.getInstance();
		Date date = new Date();
		
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		
		String startDate = String.format("%02d%02d01", year - 2000, month);
		month++;
		if (month >= 13) {
			year++;
			month = 1;
		}
		String endDate = String.format("%02d%02d01", year - 2000, month);
		
		// 이번 달 음주 기록
		List<Diary> currentDiaryList = dManager.findDiaryListBydate(id, startDate, endDate);
		// 전체 음주 기록
		List<Diary> diaryList = dManager.findDiaryListByMemberId(id);
		
		// diaryList 객체를 request에 저장하여 음주 기록 리스트 화면으로 이동(forwarding)
		request.setAttribute("currentDiaryList", currentDiaryList);
		request.setAttribute("diaryList", diaryList);				
		return "/diary/list.jsp";        
    }
}
