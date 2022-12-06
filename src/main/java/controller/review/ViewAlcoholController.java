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
		
		int detail = 0;
		if (request.getParameter("aId") != null) {
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
		
		return "/review/productInfo.jsp";
	}
}
