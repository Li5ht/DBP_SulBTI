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
	public List<Alcohol> viewAlcoholList() {
		List<Alcohol> alcoholList = new ArrayList<Alcohol>();
		Alcohol alcohol = null;
		ResultSet rs = null;
		
		String query = "SELECT alcohol_id, name, type, rate, alcohol_level, image, taste, flavor, corps FROM alcohol";
		jdbcUtil.setSqlAndParameters(query, null);
		
		try {
			rs = jdbcUtil.executeQuery();
			
			while (rs.next()) {
				alcohol = new Alcohol();
				alcohol.setAlcoholId(rs.getLong("alcohol_id"));
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
		String query = "SELECT alcohol_id, name, type, rate, alcohol_level, image, taste, flavor, corps "
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
	
	// 술 정보 받아오기 (타입, 이름)
	public Alcohol findAlcohol(String type, String name) {
		String query = "SELECT alcohol_id, name, type, rate, alcohol_level, image, taste, flavor, corps "
				+ "FROM alcohol WHERE name=? AND type=?";
		Object[] param = new Object[] { name, type };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		Alcohol alcohol = null;
			
		try {
			rs = jdbcUtil.executeQuery();
				
			if (rs.next()) {
				long alcoholId = rs.getLong("alcohol_id");
				String aName = rs.getString("name");
				String aType = rs.getString("type");
				float rate = rs.getFloat("rate");
				float alcoholLevel = rs.getFloat("alcohol_level");
				String imageUrl = rs.getString("image");
				int taste = rs.getInt("taste");
				int flavor = rs.getInt("flavor");
				int corps = rs.getInt("corps");
					
				alcohol = new Alcohol(alcoholId, aName, aType, rate, alcoholLevel, imageUrl, taste, flavor, corps);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
	    return alcohol;
	}
	
	// 술 정보 받아오기 (alcoholId)
		public Alcohol findAlcoholById(long alcoholId) {
			String query = "SELECT alcohol_id, name, type, rate, alcohol_level, image, taste, flavor, corps "
					+ "FROM alcohol WHERE alcohol_id=?";
			Object[] param = new Object[] { alcoholId };
			jdbcUtil.setSqlAndParameters(query, param);
			ResultSet rs = null;
			Alcohol alcohol = null;
				
			try {
				rs = jdbcUtil.executeQuery();
					
				if (rs.next()) {
					long aId = rs.getLong("alcohol_id");
					String aName = rs.getString("name");
					String aType = rs.getString("type");
					float rate = rs.getFloat("rate");
					float alcoholLevel = rs.getFloat("alcohol_level");
					String imageUrl = rs.getString("image");
					int taste = rs.getInt("taste");
					int flavor = rs.getInt("flavor");
					int corps = rs.getInt("corps");
						
					alcohol = new Alcohol(aId, aName, aType, rate, alcoholLevel, imageUrl, taste, flavor, corps);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {	
				jdbcUtil.close();
			}	
		    return alcohol;
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
			
			String query = "SELECT a.name AS \"aName\", a.type AS \"aType\", a.alcohol_level AS \"alcoholLevel\", a.image AS \"imageUrl\", "
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
					alcohol.setImageUrl(rs.getString("imageUrl"));
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
				+ "WHERE preference_id=?";
		
		
		RecommendDao rcd = new RecommendDao();
		
		long preferenceId;
		
		try {
			preferenceId = rcd.findPreference(review.getMember().getId(), review.getAlcohol().getAlcoholId());
			
			rcd.updatePreferenceByRate(preferenceId, review.getRate());
			
			Object[] param = new Object[] { review.getTaste(), review.getFlavor(), review.getCorps(), review.getContent(), preferenceId };
			jdbcUtil.setSqlAndParameters(query, param);
			
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
	public int deleteReview(long memberId, long alcoholId) {
		int result = 0;
		
		RecommendDao rcd = new RecommendDao();
		
		long preferenceId;
		
		String query = "DELETE FROM REVIEW WHERE preference_id=?";
		
		try {
			preferenceId = rcd.findPreference(memberId, alcoholId);
			Object[] param = new Object[] { preferenceId };
			jdbcUtil.setSqlAndParameters(query, param);
			
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
	
	public Review findReview(long memberId, long alcoholId) {
		String query = "SELECT alcohol_id, rate, taste, flavor, corps, content "
				+ "FROM REVIEW r JOIN PREFERENCE p ON r.preference_id = p.preference_id "
				+ "WHERE member_id = ? and alcohol_id = ?";
		
		Object[] param = new Object[] { memberId, alcoholId };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		Review review = null;
		
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				review = new Review();
				Alcohol alcohol = new Alcohol();
				alcohol.setAlcoholId(rs.getLong("alcohol_id"));
				review.setAlcohol(alcohol);
				review.setContent(rs.getString("content"));
				review.setRate(rs.getFloat("rate"));
				review.setTaste(rs.getInt("taste"));
				review.setFlavor(rs.getInt("flavor"));
				review.setCorps(rs.getInt("corps"));
				
				return review;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		
		return review;
	}
	
	/* 술 별점, 해시태그 수정 */
	public int updateAlcohol(long alcoholId, float rate, int taste, int flavor, int corps) {
		int result = 0;
		String query = "UPDATE Alcohol "
				+ "SET rate=?, taste=?, flavor=?, corps=? "
				+ "WHERE alcohol_id=?";
		Object[] param = new Object[] { rate, taste, flavor, corps, alcoholId };
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
	
	/* 리뷰 개수 */
	public int numberOfReview(long alcoholId) {
		String query = "select count(review_id) AS \"num\" "
				+ "from review r join preference p on r.preference_id = p.preference_id "
				+ "where alcohol_id = ?";
		Object[] param = new Object[] { alcoholId };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				int num = rs.getInt("num");
				
				return num;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return 0;
	}
	
	public int[] numberOfTaste(long alcoholId) {
		String query = "select taste, count(taste) AS \"tasteC\" "
				+ "from review r join preference p on r.preference_id = p.preference_id "
				+ "where alcohol_id = ?"
				+ "group by taste";
		Object[] param = new Object[] { alcoholId };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		int[] num = {0, 0, 0, 0, 0};
		
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				int taste = rs.getInt("taste");
				int count = rs.getInt("tasteC");
				num[taste] = count;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return num;
	}
	
	public int[] numberOfFlavor(long alcoholId) {
		String query = "select flavor, count(flavor) AS \"flavorC\" "
				+ "from review r join preference p on r.preference_id = p.preference_id "
				+ "where alcohol_id = ?"
				+ "group by flavor";
		Object[] param = new Object[] { alcoholId };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		int[] num = new int[91];
		for (int i = 0; i < 91; i++) {
			num[i] = 0;
		}
		
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				int flavor = rs.getInt("flavor");
				int count = rs.getInt("flavorC");
				num[flavor] = count;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return num;
	}
	
	public int[] numberOfCorps(long alcoholId) {
		String query = "select corps, count(corps) AS \"corpsC\" "
				+ "from review r join preference p on r.preference_id = p.preference_id "
				+ "where alcohol_id = ?"
				+ "group by corps";
		Object[] param = new Object[] { alcoholId };
		jdbcUtil.setSqlAndParameters(query, param);
		ResultSet rs = null;
		int[] num = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				int corps = rs.getInt("corps");
				int count = rs.getInt("corpsC");
				num[corps] = count;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return num;
	}
	
	// 술 이름만 가져오는 (주종별)
	public List<String> nameListByType(String type) {
		String query = "SELECT name FROM alcohol WHERE type = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] { type  });
		ResultSet rs = null;
		ArrayList<String> nameList = null;
			
		try {
			rs = jdbcUtil.executeQuery();
			nameList = new ArrayList<String>();
				
			while (rs.next()) {
				String name = rs.getString("name");
					
				nameList.add(name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
	    return nameList;
	}
	
	/* 사용자가 자주 마시는 술 TOP 5 */
	public List<Alcohol> userFavorite(long id) {
		String query = "select a.alcohol_id AS \"alcohol_id\", totalAmount, taste, flavor, corps "
				+ "from preference p, alcohol a "
				+ "where p.alcohol_id = a.alcohol_id AND totalAmount > 0 AND p.member_id = ? "
				+ "order by totalAmount DESC";
		jdbcUtil.setSqlAndParameters(query, new Object[] { id });
		ResultSet rs = null;
		List<Alcohol> alcoholList = null;
		try {
			rs = jdbcUtil.executeQuery();
			
			alcoholList = new ArrayList<Alcohol>();
			int count = 0;
			
			while (rs.next()) {
				if (count < 5) {
					Alcohol alcohol = new Alcohol();
					alcohol.setAlcoholId(rs.getLong("alcohol_id"));
					alcohol.setTaste(rs.getInt("taste"));
					alcohol.setFlavor(rs.getInt("flavor"));
					alcohol.setCorps(rs.getInt("corps"));
					
					alcoholList.add(alcohol);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return alcoholList;
	}
	
	/* 사용자가 자주 마시는 술 주종 */
	public String userFavoriteType(long id) {
		String query = "select type, sum(totalAmount) AS \"amount\" "
				+ "from preference p, alcohol a "
				+ "where p.alcohol_id = a.alcohol_id AND totalAmount > 0 AND p.member_id = ? "
				+ "group by type order by sum(totalAmount) DESC";
		jdbcUtil.setSqlAndParameters(query, new Object[] { id });
		ResultSet rs = null;
		String type = null;
		try {
			rs = jdbcUtil.executeQuery();
			
			if (rs.next()) {
				type = rs.getString("type");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return type;
	}
	
	/* 사용자가 높게 평가한 술 TOP 5 */
	public List<Alcohol> userFavoriteByRate(long id) {
		String query = "select p.rate AS \"rate\", a.alcohol_id AS \"alcohol_id\", a.taste AS \"taste\", a.flavor AS \"flavor\", a.corps AS \"corps\" "
				+ "from preference p, alcohol a "
				+ "where p.alcohol_id = a.alcohol_id AND p.member_id = ? "
				+ "order by p.rate DESC";
		jdbcUtil.setSqlAndParameters(query, new Object[] { id });
		ResultSet rs = null;
		List<Alcohol> alcoholList = null;
		try {
			rs = jdbcUtil.executeQuery();
			
			alcoholList = new ArrayList<Alcohol>();
			int count = 0;
			
			while (rs.next()) {
				if (count < 5) {
					Alcohol alcohol = new Alcohol();
					alcohol.setAlcoholId(rs.getLong("alcohol_id"));
					alcohol.setTaste(rs.getInt("taste"));
					alcohol.setFlavor(rs.getInt("flavor"));
					alcohol.setCorps(rs.getInt("corps"));
					
					alcoholList.add(alcohol);
				}
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			jdbcUtil.close();
		}	
		return alcoholList;
	}
}
