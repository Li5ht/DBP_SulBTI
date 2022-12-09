package controller.review;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.*;
import model.service.AlcoholManager;

public class ViewAlcoholController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			/* 로그인 X */
            request.setAttribute("noLogin", true);
            
            
            if (request.getParameter("createReview") != null) {
            	return "redirect:/user/login/form";
            }
        } else {
        	/* 로그인 O */
        	request.setAttribute("hasLogin", true);
			request.setAttribute("userId", UserSessionUtils.getLoginUserId(request.getSession()));
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		AlcoholManager alMan = AlcoholManager.getInstance();
		List<Alcohol> soju = alMan.listByType("소주");
		List<Alcohol> beer = alMan.listByType("맥주");
		List<Alcohol> traditional = alMan.listByType("전통주");
		List<Alcohol> wine = alMan.listByType("와인");
		List<Alcohol> spirits = alMan.listByType("양주");
		List<Alcohol> cocktail = alMan.listByType("칵테일");
		
		request.setAttribute("soju", soju);
		request.setAttribute("beer", beer);
		request.setAttribute("traditional", traditional);
		request.setAttribute("wine", wine);
		request.setAttribute("spirits", spirits);
		request.setAttribute("cocktail", cocktail);
		
		int detail = 0; // 상세 정보
		if (request.getParameter("detail") != null) {
			if (request.getParameter("detail").equals("detail")) {
				long aId = Long.parseLong(request.getParameter("aId"));
				HashMap<Alcohol, List<Review>> map = alMan.reviewListByAlcohol(aId);
				Alcohol alcohol = alMan.findAlcoholById(aId);
				List<Review> reviewList = null;
				for (Alcohol a : map.keySet()) {
					reviewList = map.get(a);
				}
					
				detail = 1;
					
				request.setAttribute("alcohol", alcohol);
				request.setAttribute("reviewList", reviewList);
			}
			request.setAttribute("detail", detail);
		}
		
		
		int createReview = 0;	// 리뷰 작성 버튼 클릭 시
		if (request.getParameter("createReview") != null) {
			if (request.getParameter("createReview").equals("createReview")) {
				long aId = Long.parseLong(request.getParameter("aId"));
				Alcohol alcohol = alMan.findAlcoholById(aId);
				request.setAttribute("alcohol", alcohol);
				
				Review review = alMan.findReview(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()), alcohol.getAlcoholId());
				if (review != null) {
					/* 이미 사용자의 해당 술에 대한 리뷰가 존재함 -> 그냥 다시 상세정보 보기로 */
					HashMap<Alcohol, List<Review>> map = alMan.reviewListByAlcohol(aId);
					alcohol = alMan.findAlcoholById(aId);
					List<Review> reviewList = null;
					for (Alcohol a : map.keySet()) {
						reviewList = map.get(a);
					}
						
					detail = 2;
					
					request.setAttribute("alcohol", alcohol);
					request.setAttribute("reviewList", reviewList);
					
					request.setAttribute("detail", detail);
				} else {
					createReview = 1;
					request.setAttribute("createReview", createReview);
				}
			}
		}
		
		
		if (request.getServletPath().equals("/review/create")) {	// 리뷰 등록
			Review review = new Review();
			
			Member member = new Member();
			member.setId(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()));
			member.setUserId(UserSessionUtils.getLoginUserId(request.getSession()));
			member.setNickname(UserSessionUtils.getLoginUserNickname(request.getSession()));
			review.setMember(member); // 작성자 등록
			
			long aId = Long.parseLong(request.getParameter("alcoholId"));
			Alcohol alcohol = alMan.findAlcoholById(aId);
			review.setAlcohol(alcohol); // 술 등록
			
			review.setRate(Float.parseFloat(request.getParameter("rate"))); // 별점 등록
			review.setContent(request.getParameter("content")); // 내용 등록
			
			int taste = Integer.parseInt(request.getParameter("taste"));
			int flavor = Integer.parseInt(request.getParameter("flavor"));
			int corps = Integer.parseInt(request.getParameter("corps"));
			
			review.setTaste(taste);
			review.setFlavor(flavor);
			review.setCorps(corps);
			
			alMan.insertReview(review);
			
			
			// 다시 상세정보 보기로
			HashMap<Alcohol, List<Review>> map = alMan.reviewListByAlcohol(aId);
			alcohol = alMan.findAlcoholById(aId);
			List<Review> reviewList = null;
			for (Alcohol a : map.keySet()) {
				reviewList = map.get(a);
			}
				
			detail = 1;
				
			request.setAttribute("alcohol", alcohol);
			request.setAttribute("reviewList", reviewList);
			
			request.setAttribute("detail", detail);
		}
		
		int updateReview = 0;	// 리뷰 수정 버튼 클릭 시
		if (request.getParameter("updateReview") != null) {
			long aId = Long.parseLong(request.getParameter("aId"));
			Alcohol alcohol = alMan.findAlcoholById(aId);
			request.setAttribute("alcohol", alcohol);
			
			Review review = alMan.findReview(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()), alcohol.getAlcoholId());
			if (review != null) {
				/* 리뷰가 존재함 */
				request.setAttribute("userReview", review);
				float rate = review.getRate();
				String userRate = Float.toString(rate);
				if (rate == 1.0 || rate == 2.0 || rate == 3.0 || rate == 4.0 || rate == 5.0) {	// 소수점 버리는 작업
					userRate = String.valueOf(Math.round(rate));
				}
				request.setAttribute("userRate", userRate);
				request.setAttribute("userTaste", String.valueOf(review.getTaste()));
				request.setAttribute("userFlavor", String.valueOf(review.getFlavor()));
				request.setAttribute("userCorps", String.valueOf(review.getCorps()));
				updateReview = 1;
				request.setAttribute("updateReview", updateReview);
				
			} else {
				/* 사용자의 리뷰가 존재하지 않음 */
				HashMap<Alcohol, List<Review>> map = alMan.reviewListByAlcohol(aId);
				alcohol = alMan.findAlcoholById(aId);
				List<Review> reviewList = null;
				for (Alcohol a : map.keySet()) {
					reviewList = map.get(a);
				}
					
				detail = 3;
				
				request.setAttribute("alcohol", alcohol);
				request.setAttribute("reviewList", reviewList);
				
				request.setAttribute("detail", detail);
			}
		}
		
		if (request.getServletPath().equals("/review/update")) {	// 리뷰 수정
			Review review = new Review();
			
			Member member = new Member();
			member.setId(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()));
			review.setMember(member); // 작성자 등록
			
			long aId = Long.parseLong(request.getParameter("alcoholId"));
			Alcohol alcohol = alMan.findAlcoholById(aId);
			review.setAlcohol(alcohol); // 술 등록
			
			review.setRate(Float.parseFloat(request.getParameter("rate"))); // 별점 등록
			review.setContent(request.getParameter("content")); // 내용 등록
			
			int taste = Integer.parseInt(request.getParameter("taste"));
			int flavor = Integer.parseInt(request.getParameter("flavor"));
			int corps = Integer.parseInt(request.getParameter("corps"));
			
			review.setTaste(taste);
			review.setFlavor(flavor);
			review.setCorps(corps);
			
			alMan.updateReview(review);
			
			
			// 다시 상세정보 보기로
			HashMap<Alcohol, List<Review>> map = alMan.reviewListByAlcohol(aId);
			alcohol = alMan.findAlcoholById(aId);
			List<Review> reviewList = null;
			for (Alcohol a : map.keySet()) {
				reviewList = map.get(a);
			}
				
			updateReview = 2;
			detail = 1;
				
			request.setAttribute("alcohol", alcohol);
			request.setAttribute("reviewList", reviewList);
			
			request.setAttribute("detail", detail);
			request.setAttribute("updateReview", updateReview);
		}
		
		return "/review/productInfo.jsp";
	}
}
