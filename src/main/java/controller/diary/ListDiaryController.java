package controller.diary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.dao.*;
import model.service.AlcoholManager;
import model.service.DiaryManager;
import model.service.UserManager;

public class ListDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(ListDiaryController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		request.setAttribute("currentDiaryList", currentDiaryList);

		// 전체 음주 기록
		List<Diary> diaryList = dManager.findDiaryListByMemberId(id);
		request.setAttribute("diaryList", diaryList);
		
		request.setAttribute("drinkingDate", "2022-12-22");
		request.setAttribute("diary", diaryList.get(0));

		/* 술 목록 받아오기 */
		AlcoholManager alMan = AlcoholManager.getInstance();
		List<Alcohol> alcoholList = alMan.viewAlcoholList();
		String[] aSoju = alMan.nameListByType("소주");
		String[] aBeer = alMan.nameListByType("맥주");
		String[] aTraditional = alMan.nameListByType("전통주");
		String[] aWine = alMan.nameListByType("와인");
		String[] aSpirits = alMan.nameListByType("양주");

		request.setAttribute("aSoju", aSoju);
		request.setAttribute("aBeer", aBeer);
		request.setAttribute("aTraditional", aTraditional);
		request.setAttribute("aWine", aWine);
		request.setAttribute("aSpirits", aSpirits);
		request.setAttribute("alcoholList", alcoholList);

		/* 음주 기록 등록 */
		if (request.getServletPath().equals("/diary/create")) {
			Diary diary = new Diary();

			// diary 내용 받아오기
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 월은 대문자 MM, 분은 소문자 mm
			Date drinkingDate = df.parse(request.getParameter("drinkingDate")); // String --> java.util.Date 변환

			// 리퀘스트 파라미터로 술에 대한 정보 받아오기
			int count = Integer.parseInt(request.getParameter("count"));
			List<Drink> drinkingList = new ArrayList<Drink>(); // Drink 저장
			for (int i = 1; i <= count; i++) {
				String str = "drink" + Integer.toString(i);
				String drinkStr = request.getParameter(str);

				String[] arr = drinkStr.split("/"); // arr[0] = 주종, arr[1] = 술 이름, arr[2] = 양

				Alcohol alcohol = alMan.findAlcohol(arr[0], arr[1]);
				Drink drink = new Drink(alcohol, Integer.parseInt(arr[2]));

				drinkingList.add(drink);
			}

			int condition = Integer.parseInt(request.getParameter("condition"));
			String content = request.getParameter("content");

			UserManager userManager = UserManager.getInstance();
			Member member = userManager.findUser(userId);

			diary.setDrinkingDate(drinkingDate);
			diary.setDrinkingList(drinkingList);
			diary.setCondition(condition);
			diary.setContent(content);
			diary.setMember(member);

			System.out.println(diary);
			try {
				dManager.create(diary);

				log.debug("Create Diary : {}", diary);
				return "redirect:/diary/list"; // 성공 시 음주 기록 리스트 화면으로 redirect

			} catch (Exception e) { // 예외 발생 시 입력 form으로 forwarding
				request.setAttribute("creationFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("diary", diary);
			}
		}

		
		

		/* 음주 기록 수정 */
		if (request.getServletPath().equals("/diary/update")) {
			/* 음주 기록 수정 버튼 클릭 시 */
			if (request.getParameter("updateDiary") != null) {
				Diary diary = null;
				long diaryId = Long.parseLong(request.getParameter("diaryId"));
				diary = dManager.findDiary(diaryId);

				// GET request: 음주 기록 수정 form 요청
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String drinkingDate = df.format(diary.getDrinkingDate());

				/* 음주 기록 받아오기 */
				request.setAttribute("drinkingDate", drinkingDate);
				request.setAttribute("diary", diary);
			}
			
			if (request.getMethod().equals("POST")) {
				Diary diary = null;
				long diaryId = Long.parseLong(request.getParameter("diaryId"));
				diary = dManager.findDiary(diaryId);

				UserManager uManager = UserManager.getInstance();
				Member member = uManager.findUserByPrimaryKey(diary.getMember().getId());
				diary.setMember(member);
				diary.setCondition(Integer.parseInt(request.getParameter("condition")));
				diary.setContent(request.getParameter("content"));
				log.debug("Update Diary : {}", diary);

				dManager.update(diary);
			}
			
		}

		/* 음주 기록 삭제 */
		if (request.getServletPath().equals("/diary/delete")) {
			Diary diary = null;
			long diaryId = Long.parseLong(request.getParameter("diaryId"));
			diary = dManager.findDiary(diaryId);

			UserManager uManager = UserManager.getInstance();
			Member member = uManager.findUserByPrimaryKey(diary.getMember().getId());
			diary.setMember(member);

			dManager.deleteDiary(diary);
			
			return "redirect:/diary/list";	
		}

		return "/diary/list.jsp";
	}
}
