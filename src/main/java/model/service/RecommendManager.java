package model.service;

import model.dao.*;

import java.util.List;

import model.*;

public class RecommendManager {
	private static RecommendManager recMan = new RecommendManager();
	private MemberDao memberDao;
	private RecommendDao recDao;
	
	private RecommendManager() {
		try {
			memberDao = new MemberDao();
			recDao = new RecommendDao();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static RecommendManager getInstance() {
		return recMan;
	}
	
	/* 사용자 별 추천 */
	public List<Drink> userRecommendList(String userId) {
		// 회원 찾기
		List<Drink> userRecList = null;
		
		
		
		
		
		return userRecList;
	}
	
	public List<Rank> overallRank() {
		return recDao.rankByPopularity();
	}
	
	public List<Rank> hotRank() {
		// 최근 언급 많은
		return recDao.rankByRecentIncrease();
	}
	
	public List<Rank> typeRank(String type) {
		return recDao.rankByType(type);
	}
	
	public long create(long memberId, long alcoholId, float data) {
		if (recDao.findPreference(memberId, alcoholId) != -1) {
			// 이미 존재할 경우.... 어떡하지?
		}
		
		if (data > 0 && data <= 5) {
			// data가 rate일 경우
			return recDao.createPreferenceByRate(memberId, alcoholId, data);
		}
		
		// data가 amount일 경우
		return recDao.createPreferenceByAmount(memberId, alcoholId, data);
	}
	
	public void update(long memberId, long alcoholId, float data) {
		long preId = recDao.findPreference(memberId, alcoholId);
		
		if (preId == -1) {
			// 테이블이 존재하지 않을 경우,,
			
		}
		
		if (data > 0 && data <= 5) {
			// data가 rate일 경우
			recDao.updatePreferenceByRate(preId, data);
		} else {
			// data가 amount일 경우
			recDao.updatePreferenceByAmount(preId, data);
		}
	}

}
