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
            
            
            if (request.getParameter("createReview") != null
            		|| request.getParameter("updateReview") != null) {
            	return "redirect:/user/login/form";
            }
        } else {
        	/* 로그인 O */
        	request.setAttribute("hasLogin", true);
			request.setAttribute("userId", UserSessionUtils.getLoginUserId(request.getSession()));
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }
		
		/* 전체 술 리스트 */
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
		
		
		/* 해당 술 상세 정보 */
		int detail = 0;
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
		
		
		/* 리뷰 작성 버튼 클릭 시 */
		int createReview = 0;	
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
		
		
		/* 리뷰 등록 */
		if (request.getServletPath().equals("/review/create")) {
			Review review = new Review();
			
			Member member = new Member();
			member.setId(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()));
			member.setUserId(UserSessionUtils.getLoginUserId(request.getSession()));
			member.setNickname(UserSessionUtils.getLoginUserNickname(request.getSession()));
			review.setMember(member); // 작성자 등록
			
			long aId = Long.parseLong(request.getParameter("alcoholId"));
			Alcohol alcohol = alMan.findAlcoholById(aId);
			review.setAlcohol(alcohol); // 술 등록
			
			float rate = Float.parseFloat(request.getParameter("rate"));
			review.setRate(rate); // 별점 등록
			review.setContent(request.getParameter("content")); // 내용 등록
			
			int taste = Integer.parseInt(request.getParameter("taste"));
			int flavor = Integer.parseInt(request.getParameter("flavor"));
			int corps = Integer.parseInt(request.getParameter("corps"));
			
			review.setTaste(taste);
			review.setFlavor(flavor);
			review.setCorps(corps);
			
			
			int num = alMan.numberOfReview(aId);	// 현재 등록되어 있는 리뷰 개수 (술의 별점 수정을 위해)
			int[] tasteH = alMan.numberOfTaste(aId);
			int[] flavorH = alMan.numberOfFlavor(aId);
			int[] corpsH = alMan.numberOfCorps(aId);	// 현재 등록되어 있는 해시태그..
			alMan.insertReview(review);
			float updateRate = alcohol.calRate(0, alcohol.getRate(), 0, num, rate);	// 기존 별점, 기존 인원, 추가할 별점
			int updateTaste = alcohol.calTaste(0, tasteH, -1, taste);
			int updateFlavor = alcohol.calFlavor(0, flavorH, -1, flavor);
			int updateCorps = alcohol.calCorps(0, corpsH, -1, corps);
			alMan.updateAlcohol(aId, updateRate, updateTaste, updateFlavor, updateCorps);	// 술의 별점, 해시태그 변경
			
			request.setAttribute("registerReview", 1);
			
			
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
		
		
		/* 리뷰 수정 버튼 클릭 시 */
		int updateReview = 0;
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
		
		
		/* 리뷰 수정 */
		if (request.getServletPath().equals("/review/update")) {
			long aId = Long.parseLong(request.getParameter("alcoholId"));
			Review oldReview = alMan.findReview(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()), aId);
			Review review = new Review();
			
			Member member = new Member();
			member.setId(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()));
			review.setMember(member); // 작성자 등록
			
			
			Alcohol alcohol = alMan.findAlcoholById(aId);
			review.setAlcohol(alcohol); // 술 등록
			
			float newRate = Float.parseFloat(request.getParameter("rate"));
			review.setRate(newRate); // 별점 등록
			review.setContent(request.getParameter("content")); // 내용 등록
			
			int newTaste = Integer.parseInt(request.getParameter("taste"));
			int newFlavor = Integer.parseInt(request.getParameter("flavor"));
			int newCorps = Integer.parseInt(request.getParameter("corps"));
			
			review.setTaste(newTaste);
			review.setFlavor(newFlavor);
			review.setCorps(newCorps);
			
			
			int num = alMan.numberOfReview(aId);	// 현재 등록되어 있는 리뷰 개수 (술의 별점 수정을 위해)
			int[] tasteH = alMan.numberOfTaste(aId);
			int[] flavorH = alMan.numberOfFlavor(aId);
			int[] corpsH = alMan.numberOfCorps(aId);	// 현재 등록되어 있는 해시태그..
			alcohol.calRate(1, alcohol.getRate(), oldReview.getRate(), num, newRate);
			int updateTaste = alcohol.calTaste(1, tasteH, oldReview.getTaste(), newTaste);
			int updateFlavor = alcohol.calFlavor(1, flavorH, oldReview.getFlavor(), newFlavor);
			int updateCorps = alcohol.calCorps(1, corpsH, oldReview.getCorps(), newCorps);
			alMan.updateReview(review);
			alMan.updateAlcohol(aId, newRate, updateTaste, updateFlavor, updateCorps);  // 술의 별점, 해시태그 변경
			
			
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
		
		/* 리뷰 삭제 */
		if (request.getServletPath().equals("/review/delete")) {
			long aId = Long.parseLong(request.getParameter("alcoholId"));
			Review oldReview = alMan.findReview(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()), aId);
			Alcohol alcohol = alMan.findAlcoholById(aId);
			int result = alMan.deleteReview(UserSessionUtils.getLoginUserPrimaryKey(request.getSession()), aId);
			
			if (result > 0) {
				int deleteReview = 1;
				
				request.setAttribute("deleteReview", deleteReview);
				
				int num = alMan.numberOfReview(aId);	// 현재 등록되어 있는 리뷰 개수 (술의 별점 수정을 위해)
				int[] tasteH = alMan.numberOfTaste(aId);
				int[] flavorH = alMan.numberOfFlavor(aId);
				int[] corpsH = alMan.numberOfCorps(aId);	// 현재 등록되어 있는 해시태그..
				float newRate = alcohol.calRate(2, alcohol.getRate(), oldReview.getRate(), num, 0);
				int updateTaste = alcohol.calTaste(1, tasteH, oldReview.getTaste(), -1);
				int updateFlavor = alcohol.calFlavor(1, flavorH, oldReview.getFlavor(), -1);
				int updateCorps = alcohol.calCorps(1, corpsH, oldReview.getCorps(), -1);
				alMan.updateAlcohol(aId, newRate, updateTaste, updateFlavor, updateCorps);
			}
			
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
		
		
		return "/review/productInfo.jsp";
	}
}
