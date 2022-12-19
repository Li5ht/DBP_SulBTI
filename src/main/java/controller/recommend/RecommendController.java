package controller.recommend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.RecommendManager;
import model.*;

public class RecommendController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String userId = null; /* 사용자 아이디 */
		
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
        } else {
        	request.setAttribute("hasLogin", true);
        	userId = UserSessionUtils.getLoginUserId(request.getSession());
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }


		RecommendManager manager = RecommendManager.getInstance();
		List<Drink> userRecList = manager.userRecommendList(userId);
		List<Rank> hotRank = manager.hotRank();
		List<Rank> overallRank = manager.overallRank();
		
		List<Rank> soju = manager.typeRank("소주");
		List<Rank> beer = manager.typeRank("맥주");
		List<Rank> traditional = manager.typeRank("전통주");
		List<Rank> wine = manager.typeRank("와인");
		List<Rank> spirits = manager.typeRank("양주");
		List<Rank> cocktail = manager.typeRank("칵테일");
		
		request.setAttribute("userRecList", userRecList);
		request.setAttribute("hotRank", hotRank);
		request.setAttribute("overallRank", overallRank);
		
		request.setAttribute("soju", soju);
		request.setAttribute("beer", beer);
		request.setAttribute("traditional", traditional);
		request.setAttribute("wine", wine);
		request.setAttribute("spirits", spirits);
		request.setAttribute("cocktail", cocktail);
		
	
		return "/recommend/list.jsp";
	}
}
