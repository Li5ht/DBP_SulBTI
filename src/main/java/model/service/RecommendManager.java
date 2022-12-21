package model.service;

import model.dao.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;

public class RecommendManager {
	private static RecommendManager recMan = new RecommendManager();
	private MemberDao memberDao;
	private RecommendDao recDao;
	private AlcoholDAO alcoholDao;
	
	private RecommendManager() {
		try {
			memberDao = new MemberDao();
			recDao = new RecommendDao();
			alcoholDao = new AlcoholDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static RecommendManager getInstance() {
		return recMan;
	}
	
	/* 사용자 별 추천 (술 5개 추천) */
	public List<Drink> userRecommendList(long userPK) {
		/*
		 *  1. 사용자가 자주 마시는 술 혹은 별점을 높게 준 술과 해시태그 비교
		 *  해시태그 3개 다 같음 -> 2개 같음 {(맛, 바디감) -> (맛, 향) -> (향, 바디감)} 순 (1개만 같은 경우는 X)
		 *  2. 기존 술에 해시태그 존재 X -> 해시태그 비교 불가능할 경우, 사용자가 좋아하는 술을 좋아하는 다른 사용자를 찾아 그 사용자가 좋아하는 술로 추천
		 *  3. 사용자가 자주 마시는 술의 주종에서 랜덤 추천
		 *  4. 전체 랜덤
		 *  술 추천 시, 사용자가 마신 적 없는 술 위주로 추천 (리뷰와 음주 기록에 없는) -> 그러고도 없을 경우 all 랜덤
		 */
		
		if (userPK == -1) {	// 로그인 x : return
			return null;
		}
		
		float userDC = memberDao.getDrinking(userPK); // 사용자 주량 (없을 경우, -1)
		List<Alcohol> userFavoriteList = alcoholDao.userFavorite(userPK); // 사용자가 자주 마시는 술 (TOP 5)
		List<Alcohol> userFavoriteByRateList = alcoholDao.userFavoriteByRate(userPK); // 사용자가 높게 평가한 술 (TOP 5)
		String type = alcoholDao.userFavoriteType(userPK); // 사용자가 통틀어서 자주 마시는 술 주종
		List<Alcohol> alcoholList = alcoholDao.viewAlcoholList(); // 모든 술 리스트 (해시태그 비교 용)
		
		// 사용자가 자주 마시는 술, 사용자가 높게 평가한 술 중복 제거
		for (Alcohol alcohol : userFavoriteByRateList) {
			boolean exist = false;
			for (Alcohol userList : userFavoriteList) {
				if (alcohol.getAlcoholId() == userList.getAlcoholId()) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				userFavoriteList.add(alcohol);
			}
		}
		
		
		List<Drink> userRecList = null;
		List<Alcohol> hashTag3 = null; // 해시태그 3개 같음
		List<Alcohol> hashTag2_1 = null; // 해시태그 맛, 바디감 같음
		List<Alcohol> hashTag2_2 = null; // 해시태그 맛, 향 같음
		List<Alcohol> hashTag2_3 = null; // 해시태그 향, 바디감 같음
		
		for (Alcohol alcohol : alcoholList) {
			if (alcohol.getTaste() != 0 && alcohol.getFlavor() != 0 && alcohol.getCorps() != 0) {
				// 해시태그가 없는 경우는 제외
				for (Alcohol userList : userFavoriteList) {
					if (userList.getFlavor() == 0 && userList.getTaste() == 0 && userList.getCorps() == 0) {
						// 해당 술에 해시태그 없는 경우 제외
						continue;
					} else if (alcohol.getAlcoholId() == userList.getAlcoholId()) {
						// 같은 술일 경우 pass
						continue;
					} else {
						if (userList.getTaste() == alcohol.getTaste() &&
								userList.getFlavor() == alcohol.getFlavor() &&
								userList.getCorps() == alcohol.getCorps()) {
							if (hashTag3 == null) {
								hashTag3 = new ArrayList<Alcohol>();
							}
							hashTag3.add(alcohol);
						} else if (userList.getTaste() == alcohol.getTaste() &&
								userList.getCorps() == alcohol.getCorps()) {
							if (hashTag2_1 == null) {
								hashTag2_1 = new ArrayList<Alcohol>();
							}
							hashTag2_1.add(alcohol);
						} else if (userList.getTaste() == alcohol.getTaste() &&
								userList.getFlavor() == alcohol.getFlavor()) {
							if (hashTag2_2 == null) {
								hashTag2_2 = new ArrayList<Alcohol>();
							}
							hashTag2_2.add(alcohol);
						} else if (userList.getFlavor() == alcohol.getFlavor() &&
								userList.getCorps() == alcohol.getCorps()) {
							if (hashTag2_3 == null) {
								hashTag2_3 = new ArrayList<Alcohol>();
							}
							hashTag2_3.add(alcohol);
						} 
					}
				}
			}
		}
		
		
		// 리스트 통합 (3 -> 2_1 -> 2_2 -> 2_3 순)
		int count = 0;
		CalcDrinkingCapacity dc = new CalcDrinkingCapacity();
		
		if (hashTag3 != null) {
			for (Alcohol alcohol : hashTag3) { // hashTag3
				if (count < 5) {
					if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) { 
						// 사용자가 접한 적 있는 술일 경우 pass
						continue;
					}
					if (userRecList == null) {
						userRecList = new ArrayList<Drink>();
					}
					int amount = 0;
					if (userDC != -1) {
						amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
					} 
					Drink drink = new Drink(alcohol, amount);
					userRecList.add(drink);
					count++;
				} else {
					break;
				}
			} 
		} 
		
		else if (hashTag2_1 != null) {
			for (Alcohol alcohol : hashTag2_1) { // hashTag2_1
				if (count < 5) {
					if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) {
						continue;
					}
					if (userRecList == null) {
						userRecList = new ArrayList<Drink>();
					}
					int amount = 0;
					if (userDC != -1) {
						amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
					} 
					Drink drink = new Drink(alcohol, amount);
					userRecList.add(drink);
					count++;
				} else {
					break;
				}
			}
		} 
		
		else if (hashTag2_2 != null) {
			for (Alcohol alcohol : hashTag2_2) { // hashTag2_2
				if (count < 5) {
					if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) {
						continue;
					}
					if (userRecList == null) {
						userRecList = new ArrayList<Drink>();
					}
					int amount = 0;
					if (userDC != -1) {
						amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
					} 
					Drink drink = new Drink(alcohol, amount);
					userRecList.add(drink);
					count++;
				} else {
					break;
				}
			}
		} 
		
		else if (hashTag2_3 != null) {
			for (Alcohol alcohol : hashTag2_3) { // hashTag2_3
				if (count < 5) {
					if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) {
						continue;
					}
					if (userRecList == null) {
						userRecList = new ArrayList<Drink>();
					}
					int amount = 0;
					if (userDC != -1) {
						amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
					} 
					Drink drink = new Drink(alcohol, amount);
					userRecList.add(drink);
					count++;
				} else {
					break;
				}
			}
		}
		
		
		// 사용자가 좋아하는 술을 좋아하는 다른 사용자를 찾아 그 사용자가 좋아하는 술로 추천
		List <Alcohol> subList = new ArrayList<Alcohol>();
		for (Alcohol alcohol : userFavoriteList) {
			List<Long> memberList = alcoholDao.memberListByAlcohol(userPK, alcohol.getAlcoholId());
			for (Long l : memberList) {
				List<Alcohol> otherFavoriteList = alcoholDao.userFavorite(l); // 그 사용자가 자주 마시는 술 (TOP 5)
				List<Alcohol> otherFavoriteByRateList = alcoholDao.userFavoriteByRate(l); // 그 사용자가 높게 평가한 술 (TOP 5)
				
				// 술 중복 제거
				for (Alcohol a : otherFavoriteByRateList) {
					boolean exist = false;
					for (Alcohol userList : otherFavoriteList) {
						if (a.getAlcoholId() == userList.getAlcoholId()) {
							exist = true;
							break;
						}
					}
					if (!exist) {
						otherFavoriteList.add(a);
					}
				}
				
				for (Alcohol a : otherFavoriteList) {
					if (recDao.findPreference2(userPK, a.getAlcoholId()) != -1) {
						continue;
					}
					if (userRecList == null) {
						userRecList = new ArrayList<Drink>();
					}
					boolean exist = false;
					for (Drink recList : userRecList) { // userRecList에 이미 존재하는 술이면 pass
						if (recList.getAlcohol().getAlcoholId() == a.getAlcoholId()) {
							exist = true;
							break;
						}
					}
					if (exist) {
						continue;
					}
					
					subList.add(a);
				}
				
			}
		}
		Collections.shuffle(subList);
		for (Alcohol a : subList) {
			if (count < 5) {
				int amount = 0;
				if (userDC != -1) {
					amount = dc.drinkableAmount(userDC, a.getAlcoholLevel());
				} 
				Alcohol al = alcoholDao.findAlcoholById(a.getAlcoholId());
				Drink drink = new Drink(al, amount);
				
				userRecList.add(drink);
				
				count++;
			}
		}
		
		
		// userRecList가 5개가 안 될 때 (해시태그가 같은 경우가 없을 때) -> 사용자가 즐겨 마시는 주종에서 랜덤
		List<Alcohol> alcoholListByType = alcoholDao.listByType(type);
		Collections.shuffle(alcoholListByType);
		for (Alcohol alcohol : alcoholListByType) {
			if (count < 5) {
				if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) {
					continue;
				}
				if (userRecList == null) {
					userRecList = new ArrayList<Drink>();
				}
				
				
				boolean exist = false;
				for (Drink recList : userRecList) { // userRecList에 이미 존재하는 술이면 pass
					if (recList.getAlcohol().getAlcoholId() == alcohol.getAlcoholId()) {
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
				
				
				int amount = 0;
				if (userDC != -1) {
					amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
				} 
				Drink drink = new Drink(alcohol, amount);
				userRecList.add(drink);
				count++;
			} else {
				break;
			}
		}

		// 그러고도 없을 때 전체 랜덤
		Collections.shuffle(alcoholList);
		for (Alcohol alcohol : alcoholList) {
			if (count < 5) {
				if (recDao.findPreference2(userPK, alcohol.getAlcoholId()) != -1) {
					continue;
				}
				if (userRecList == null) {
					userRecList = new ArrayList<Drink>();
				}
				
				boolean exist = false;
				for (Drink recList : userRecList) { // userRecList에 이미 존재하는 술이면 pass
					if (recList.getAlcohol().getAlcoholId() == alcohol.getAlcoholId()) {
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
				
				int amount = 0;
				if (userDC != -1) {
					amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
				} 
				Drink drink = new Drink(alcohol, amount);
				userRecList.add(drink);
				count++;
			} else {
				break;
			}
		}

		// 그러고도 없을 때 (사용자가 그 술을 경험한 적 있어도) 전체 랜덤
		Collections.shuffle(alcoholList);
		for (Alcohol alcohol : alcoholList) {
			if (count < 5) {
				if (userRecList == null) {
					userRecList = new ArrayList<Drink>();
				}
						
				boolean exist = false;
				for (Drink recList : userRecList) { // userRecList에 이미 존재하는 술이면 pass
					if (recList.getAlcohol().getAlcoholId() == alcohol.getAlcoholId()) {
						exist = true;
						break;
					}
				}
				if (exist) {
					continue;
				}
						
				int amount = 0;
				if (userDC != -1) {
					amount = dc.drinkableAmount(userDC, alcohol.getAlcoholLevel());
				} 
				Drink drink = new Drink(alcohol, amount);
				userRecList.add(drink);
				count++;
			} else {
				break;
			}
		}
		
		
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
	
	/* 리뷰 삭제 시 사용 (totalAmount가 0 이하일 땐 삭제) */
	public void deletePreference(long memberId, long alcoholId) {
		int result = recDao.deletePreference(memberId, alcoholId);
		if (result == 0) {
			long preferenceId = recDao.findPreference(memberId, alcoholId);
			recDao.updatePreferenceByRate(preferenceId, 0);
		}
	}
	
	public List<Long> findAllPreference(long id) {
		return recDao.findAllPreference(id);
	}
	
	public void deleteAllPreference(long id) {
		recDao.deleteAllPreference(id);
	}

}
