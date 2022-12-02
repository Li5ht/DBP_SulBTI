package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Alcohol;
import model.Member;
import model.Review;

public class AlcoholDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AlcoholDAO() {
		jdbcUtil = new JDBCUtil();	
	}
	
	// 술 목록 보여주기 (read)
	public List<Alcohol> viewAlcoholList() throws SQLException{
		List<Alcohol> alcoholList = new ArrayList<Alcohol>();
		Alcohol alcohol = null;
		ResultSet rs = null;
		
		String query = "SELECT name, type, rate, alcohol_level, image, taste, flavor, corps FROM alcohol";
		jdbcUtil.setSqlAndParameters(query, null);
		
		try {
			rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				alcohol = new Alcohol();
				alcohol.setName(rs.getString("name"));
				alcohol.setType(rs.getString("type"));
				alcohol.setRate(rs.getFloat("rate"));
				alcohol.setAlcoholLevel(rs.getFloat("alcohol_level"));
				alcohol.setImageUrl(rs.getString("image"));
				alcohol.setTaste(rs.getInt("taste"));
				alcohol.setFlavor(rs.getInt("flavor"));
				alcohol.setCorps(rs.getInt("corps"));
				alcoholList.add(alcohol);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return alcoholList;
	}
	
	// 술 검색 기능 (search)
	public List<Alcohol> searchAlcohol(String name) {
		String query = "SELECT alcohol_id name, type, rate, alcohol_level, image, taste, flavor, corps "
				+ "FROM alcohol WHERE name Like ?";
		String sQuery = "%"+name+"%";
		Object[] param = new Object[] { sQuery };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		ArrayList<Alcohol> aList = null;
		
		try {
			rs = jdbcUtil.executeQuery();
			aList = new ArrayList<Alcohol>();
			
			while (rs.next()) {
				long alcoholId = rs.getLong("alcohol_id");
				String aName = rs.getString("name");
				String type = rs.getString("type");
				float rate = rs.getFloat("rate");
				float alcoholLevel = rs.getFloat("alcohol_level");
				String imageUrl = rs.getString("image");
				int taste = rs.getInt("taste");
				int flavor = rs.getInt("flavor");
				int corps = rs.getInt("corps");
				
				Alcohol alcohol = new Alcohol(alcoholId, aName, type, rate, alcoholLevel, imageUrl, taste, flavor, corps);
				
				aList.add(alcohol);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
    	return aList;
	}
	
	// 술 필터링 기능 (주종별)
	public List<Alcohol> listByType(String type) {
		String query = "SELECT alcohol_id, name, type, rate, alcohol_level, image, taste, flavor, corps "
				+ "FROM alcohol WHERE type = ?";
		Object[] param = new Object[] { type };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		ArrayList<Alcohol> aList = null;
		
		try {
			rs = jdbcUtil.executeQuery();
			aList = new ArrayList<Alcohol>();
			
			while (rs.next()) {
				long alcoholId = rs.getLong("alcohol_id");
				String name = rs.getString("name");
				float rate = rs.getFloat("rate");
				float alcoholLevel = rs.getFloat("alcohol_level");
				String imageUrl = rs.getString("image");
				int taste = rs.getInt("taste");
				int flavor = rs.getInt("flavor");
				int corps = rs.getInt("corps");
				
				Alcohol alcohol = new Alcohol(alcoholId, name, type, rate, alcoholLevel, imageUrl, taste, flavor, corps);
				
				aList.add(alcohol);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
    	return aList;
	}
	
	// 술 디테일 + 리뷰 (read)
	/*
	    HashMap<Alcohol, List<Review>>로 전달하므로 사용 시,
	    HashMap<Alcohol, List<Review>> map = alcoholDao.reviewListByAlcohol(alcoholId);
		Alcohol alcohol = null;
		List<Review> reviewList = null;
		for (Alcohol a : map.keySet()) {
			alcohol = a;
			reviewList = map.get(a);
		}
		이런 식으로 저장해서 사용할 수 있음 
		(어차피 이 메소드에서 HashMap이어도 put을 1번만 때문에 for문으로 써도 반복문 1번 실행하고 끝남)
	 */
	public HashMap<Alcohol, List<Review>> reviewListByAlcohol(long alcoholId) {
		List<Review> reviewList = null;
		Alcohol alcohol = null;
		Member member = null;
		Review review = null;
		HashMap<Alcohol, List<Review>> map = null;
		
		String query = "SELECT a.name AS \"aName\", a.type AS \"aType\", a.alcohol_level AS \"alcoholLevel\", "
				+ "a.rate AS \"aRate\", a.taste AS \"aTaste\", a.flavor AS \"aFlavor\", a.corps AS \"aCorps\", "
				+ "m.id AS \"mId\", m.nickname AS \"nickname\", p.rate AS \"pRate\", r.reg_date AS \"regDate\", "
				+ "r.taste AS \"rTaste\", r.flavor AS \"rFlavor\", r.corps AS \"rCorps\", r.content AS \"content\" "
				+ "FROM Review r, Alcohol a, Preference p, Member m "
				+ "WHERE r.preference_id = p.preference_id "
				+ "AND a.alcohol_id = p.alcohol_id "
				+ "AND p.member_id = m.id "
				+ "AND p.alcohol_id = ? "
				+ "ORDER BY r.reg_date DESC";
		
		Object[] param = new Object[] { alcoholId };
		
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		
		try {
			rs = jdbcUtil.executeQuery();
			
			alcohol = new Alcohol();
			reviewList = new ArrayList<Review>();
			map = new HashMap<Alcohol, List<Review>>();
			
			while (rs.next()) {
				alcohol.setName(rs.getString("aName"));
				alcohol.setType(rs.getString("aType"));
				alcohol.setAlcoholLevel(rs.getFloat("alcoholLevel"));
				alcohol.setRate(rs.getFloat("aRate"));
				alcohol.setTaste(rs.getInt("aTaste"));
				alcohol.setFlavor(rs.getInt("aFlavor"));
				alcohol.setCorps(rs.getInt("aCorps"));
				
				member = new Member();
				member.setId(rs.getLong("mId")); 			// 혹시나 필요할까봐,, member의 식별자도 추가
				member.setNickname(rs.getString("nickname"));	// 리뷰 보여줄 때 작성자의 닉네임만 알면 되므로 다른 컬럼 생략
				
				review = new Review();
				review.setAlcohol(alcohol);
				review.setMember(member);
				review.setRate(rs.getFloat("pRate"));
				review.setRegDate(rs.getDate("regDate"));
				review.setTaste(rs.getInt("rTaste"));
				review.setFlavor(rs.getInt("rFlavor"));
				review.setCorps(rs.getInt("rCorps"));
				review.setContent(rs.getString("content"));
				
				reviewList.add(review); // reviewList에 리뷰 저장
			}
			
			map.put(alcohol, reviewList); // HashMap에 put
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		
		return map;
	}
	
	
	// 리뷰 추가 (insert)
	public long insertReview(Review review) {
		String query = "INSERT INTO REVIEW (review_id, preference_id, reg_date, taste, flavor, corps, content) "
				+ "VALUES (review_id_seq.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?)";
		
		Object[] param = null;
		
		String key[] = { "review_id" };
		
		RecommendDao rcd = new RecommendDao();
		
		long preferenceId;
		ResultSet rs = null;
		long reviewId = -1;
		
		try {
			preferenceId = rcd.findPreference(review.getMember().getId(), review.getAlcohol().getAlcoholId());
			
			if (preferenceId == -1) {
				preferenceId = rcd.createPreferenceByRate(review.getMember().getId(), review.getAlcohol().getAlcoholId(), review.getRate());
			} else {
				rcd.updatePreferenceByRate(preferenceId, review.getRate());
			}
			
			param = new Object[] { preferenceId, review.getTaste(), 
					review.getFlavor(), review.getCorps(), review.getContent()};
			
			jdbcUtil.setSqlAndParameters(query, param);
			jdbcUtil.executeUpdate(key);
			rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				reviewId = rs.getInt(1);
			}
			
			jdbcUtil.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		
		return reviewId;
	}
	
	// 리뷰 수정 (update)
	public int updateReview(Review review) {
		int result = 0;
		String query = "UPDATE REVIEW "
				+ "SET taste=?, flavor=?, corps=?, content=? "
				+ "WHERE review_id=?";
		Object[] param = new Object[] { review.getTaste(), review.getFlavor(), review.getCorps(), review.getContent(), review.getReviewId() };
		jdbcUtil.setSqlAndParameters(query, param);
		
		RecommendDao rcd = new RecommendDao();
		
		long preferenceId;
		
		try {
			preferenceId = rcd.findPreference(review.getMember().getId(), review.getAlcohol().getAlcoholId());
			
			rcd.updatePreferenceByRate(preferenceId, review.getRate());
			
			result = jdbcUtil.executeUpdate();
			
			jdbcUtil.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return result;
	}
	
	// 리뷰 삭제 (delete)
	public int deleteReview(long reviewId) {
		int result = 0;
		
		String query = "DELETE FROM REVIEW WHERE review_id=?";
		Object[] param = new Object[] { reviewId };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			result = jdbcUtil.executeUpdate();
			
			jdbcUtil.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return result;
	}
}
