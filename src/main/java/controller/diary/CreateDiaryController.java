package controller.diary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.service.DiaryManager;
import model.service.UserManager;

public class CreateDiaryController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateDiaryController.class);

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
    	
    	Diary diary = new Diary();	
    	// 술 목록 받아오기
    	AlcoholDAO alcoholDao = new AlcoholDAO();
		List<Alcohol> alcoholList = alcoholDao.viewAlcoholList();
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 음주 기록 등록 form 요청	
			request.setAttribute("alcoholList", alcoholList);	
			
			return "/diary/registerForm.jsp";   // 검색한 정보를 update form으로 전송     
	    }	
    	
    	// diary 내용 받아오기 
    	DateFormat df = new SimpleDateFormat("yyyyMMdd"); // 월은 대문자 MM, 분은 소문자 mm
        Date drinkingDate = df.parse(request.getParameter("date")); // String --> java.util.Date 변환

    	List<Drink> drinkingList = new ArrayList<Drink>(); // 술 목록
    	// 추후에 반복문으로 수정.. 일단 테스트부터 하고
    	Drink drink = new Drink();
    	//String alcoholName = request.getParameter("alcohol");
//    	String alcoholName = "카스";
//    	List<Alcohol> alcohol = alcoholDao.searchAlcohol(alcoholName);
//    	if (alcohol.size() != 0) {
//    		drink.setAlcohol(alcohol.get(0));
//    	}
    	long n = 10001;
    	float f1 = 0;
    	float f2 = (float) 4.5;
    	drink.setAlcohol(new Alcohol(n, "카스", "맥주", f1, f2, "https://w.namu.la/s/4f1303df52a3acaf98f0f74756c12df81600bd5720083f2322c16be36a3d1fb79efb5467d530e6cfd1a159af4cd95f68930601542b383aa8e3ce55c2db6dfbbeba404188c4afde712c831d2b5ae543057d48ba70a4544d9ba8555ee45001a375", 0, 0, 0));
    	drink.setAmount(Integer.parseInt(request.getParameter("amount")));
    	drinkingList.add(drink);
    	
    	int condition = Integer.parseInt(request.getParameter("condition"));
    	String content = request.getParameter("content");
    	
    	UserManager userManager = UserManager.getInstance();
    	Member member = userManager.findUser(userId);
    	
    	diary.setDrinkingDate(drinkingDate);
    	diary.setDrinkingList(drinkingList);
    	diary.setCondition(condition);
    	diary.setContent(content);
    	diary.setMember(member);
    	
		try {
			DiaryManager dManager = DiaryManager.getInstance();
			dManager.create(diary);
			
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
