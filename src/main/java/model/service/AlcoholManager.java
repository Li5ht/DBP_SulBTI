package model.service;

import java.sql.SQLException;
import java.util.*;

import model.*;
import model.dao.*;

public class AlcoholManager {
	private static AlcoholManager alMan = new AlcoholManager();
	private AlcoholDAO alDao;
	
	private AlcoholManager() {
		try {
			alDao = new AlcoholDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static AlcoholManager getInstance() {
		return alMan;
	}
	
	/* 술 목록 */
	public List<Alcohol> viewAlcoholList() throws SQLException {
		return alDao.viewAlcoholList();
	}
	
	/* 검색 기능 */
	public List<Alcohol> searchAlcohol(String name) {
		return alDao.searchAlcohol(name);
	}
	
	/* 하나의 술에 대한 정보 받아오기 (시뮬레이터에서 사용) */
	public Alcohol findAlcohol(String type, String name) {
		return alDao.findAlcohol(type, name);
	}
	
	public Alcohol findAlcoholById(long alcoholId) {
		return alDao.findAlcoholById(alcoholId);
	}
	
	/* 술 필터링 (주종별) */
	public List<Alcohol> listByType(String type) {
		return alDao.listByType(type);
	}
	
	/* 술 디테일 + 리뷰 */
	public HashMap<Alcohol, List<Review>> reviewListByAlcohol(long alcoholId) {
		return alDao.reviewListByAlcohol(alcoholId);
	}
	
	/* 리뷰 추가 */
	public long insertReview(Review review) {
		return alDao.insertReview(review);
	}
	
	/* 리뷰 수정 */
	public int updateReview(Review review) {
		return alDao.updateReview(review);
	}
	
	/* 리뷰 삭제 */
	public int deleteReview(long memberId, long alcoholId) {
		return alDao.deleteReview(memberId, alcoholId);
	}
	
	/* 리뷰 유무 확인 */
	public Review findReview(long memberId, long alcoholId) {
		return alDao.findReview(memberId, alcoholId);
	}
	
	/* 술 별점, 해시태그 수정 */
	public int updateAlcohol(long alcoholId, float rate, int taste, int flavor, int corps) {
		return alDao.updateAlcohol(alcoholId, rate, taste, flavor, corps);
	}
	
	/* 리뷰 개수 */
	public int numberOfReview(long alcoholId) {
		return alDao.numberOfReview(alcoholId);
	}
	
	public int[] numberOfTaste(long alcoholId) {
		return alDao.numberOfTaste(alcoholId);
	}
	
	public int[] numberOfFlavor(long alcoholId) {
		return alDao.numberOfFlavor(alcoholId);
	}
	
	public int[] numberOfCorps(long alcoholId) {
		return alDao.numberOfCorps(alcoholId);
	}
	
	/* 술 이름 목록 (자바스크립트에서 사용하기 위해 배열로 전달) */
	public String[] nameListByType(String type) {
		List<String> alcohol = alDao.nameListByType(type);
		
		return alcohol.toArray(new String[alcohol.size()]);
	}
	
	/* 사용자가 자주 마시는 술 탑 5 */
	public List<Alcohol> userFavorite(long id) {
		return alDao.userFavorite(id);
	}
	
	/* 사용자가 자주 마시는 술 주종 */
	public String userFavoriteType(long id) {
		return alDao.userFavoriteType(id);
	}
	
	/* 사용자가 높게 평가한 술 TOP 5 */
	public List<Alcohol> userFavoriteByRate(long id) {
		return alDao.userFavoriteByRate(id);
	}
	
	/* 그 술에 대한 리뷰 (rate가 5, 4.5, 4.0 준 사용자 조회 (PK 리스트 반환)) */
	public List<Long> memberListByAlcohol(long id, long alcohol_id) {
		return alDao.memberListByAlcohol(id, alcohol_id);
	}
}
