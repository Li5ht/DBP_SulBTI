package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.*;
import model.service.RecommendManager;
import model.*;

public class RecommendController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String userId = null; /* 사용자 아이디 */
		long id = -1; /* 사용자 primary key */
		
		/* 로그인 여부 확인 */
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			request.setAttribute("noLogin", true);
        } else {
        	request.setAttribute("hasLogin", true);
        	userId = UserSessionUtils.getLoginUserId(request.getSession());
        	id = UserSessionUtils.getLoginUserPrimaryKey(request.getSession());
        	request.setAttribute("nickname", UserSessionUtils.getLoginUserNickname(request.getSession()));
        }


		RecommendManager manager = RecommendManager.getInstance();
		List<Drink> userRecList = manager.userRecommendList(userId);
		List<Rank> hotRank = manager.hotRank();
		List<Rank> overallRank = manager.overallRank();
		
		HashMap<String, List<Rank>> rankByType = new HashMap<String, List<Rank>>();
		List<Rank> rank = manager.typeRank("소주");
		rankByType.put("소주", rank);
		rank.clear();
		rank = manager.typeRank("맥주");
		rankByType.put("맥주", rank);
		// 그 이하는 일단 생략
		
		request.setAttribute("userRecList", userRecList);
		request.setAttribute("hotRank", hotRank);
		request.setAttribute("overallRank", overallRank);
		request.setAttribute("rankType", rankByType);
		
	
		return "/recommend/list.jsp";
	}
}
