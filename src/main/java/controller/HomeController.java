package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import model.service.*;

public class HomeController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            request.setAttribute("noLogin", true);
        } else {
        	request.setAttribute("hasLogin", true);
        }
		
		RecommendManager manager = RecommendManager.getInstance();
		List<Rank> hotRank = manager.hotRank();
		List<Rank> overallRank = manager.overallRank();
		
		request.setAttribute("hotRank", hotRank);
		request.setAttribute("overallRank", overallRank);
		
		return "/home/home.jsp";
	}
}
