package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.Member;

import java.sql.*;


public class MemberDao {

	private JDBCUtil jdbcUtil = null;
	
	public MemberDao() {			
		jdbcUtil = new JDBCUtil();		
	}

	// Member 의 기본 정보를 포함하는 query 문
	private static String query = "SELECT member.id, " +
								         "member.user_id, " +
								         "member.nickname, " +
								         "member.password, " +
								         "member.email, " +
								         "member.birth, " +
								         "member.gender, " +
								         "member.test_type, " +
								         "member.drinking_capacity ";	
	public long getDrinking(long id) {
		String searchQuery = "SELECT member.drinking_capacity  "
				+ "FROM member "
				+ "WHERE member.id = ? ";
		
		Object[] param = new Object[] { id };
		jdbcUtil.setSqlAndParameters(searchQuery, param);

		long drinkingCapacity = -1;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				drinkingCapacity = rs.getLong("drinking_capacity");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return drinkingCapacity;
		
	}
		
	// 전체 회원정보를 List 로 반환하는 메소드
	public List<Member> getMemberList() {
		String allQuery = query + "FROM member ";		
		jdbcUtil.setSqlAndParameters(allQuery, null);
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();
			List<Member> list = new ArrayList<Member>();
			while (rs.next()) {	
				Member dto = new Member();
				dto.setId(rs.getLong("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
				dto.setGender(rs.getInt("gender"));
				dto.setTestType(rs.getString("test_type"));
				dto.setDrinkingCapacity(rs.getFloat("drinking_capacity"));
				list.add(dto);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}		
		return null;	
	}

	// 회원의 아이디로 회원정보를 검색하여 해당회원의 정보를 갖고 있는 Member 객체를 반환하는 메소드
	public Member getMemberById(String userId) {
		String searchQuery = query +  "FROM member " +
		  							  "WHERE member.user_id = ? ";	 
		
		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(searchQuery, param);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			Member dto = null;
			if (rs.next()) {
				dto = new Member();
				dto.setId(rs.getLong("id"));
				dto.setUserId(rs.getString("user_id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
				dto.setGender(rs.getInt("gender"));
				dto.setTestType(rs.getString("test_type"));
				dto.setDrinkingCapacity(rs.getFloat("drinking_capacity"));
			}
			return dto;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// Member 객체에 담겨 있는 회원의 정보를 기반으로 회원정보를 member 테이블에 삽입하는 메소드
	public int insertMember(Member mem) {
		int result = 0;
		String insertQuery = "INSERT INTO member (id, user_id, nickname, password, email, birth, gender, test_type, drinking_capacity) " +
							 "VALUES (id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		Object[] param = new Object[] {mem.getUserId(), mem.getNickname(), mem.getPassword(), mem.getEmail(), mem.getBirth(), mem.getGender(), mem.getTestType(), mem.getDrinkingCapacity()};		
		jdbcUtil.setSqlAndParameters(insertQuery, param);
				
		try {				
			result = jdbcUtil.executeUpdate();
			System.out.println(mem.getId() + " 회원정보가 삽입되었습니다.");
			jdbcUtil.commit();
		} catch (SQLException ex) {
			jdbcUtil.rollback();
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 회원정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.close();
		}		
		return result;
	}
	
	// Member 객체에 설정되어 있는 정보를 토대로 테이블의 정보를 수정하는 메소드
	public int updateMember(Member mem) {
		
		String updateQuery = "UPDATE member SET ";
		int index = 0;
		Object[] tempParam = new Object[10];
		
//		if(Long.valueOf(mem.getId()) != null) {
//			updateQuery += "id = ?, ";		
//			tempParam[index++] = mem.getId();
//		}
//		if (mem.getUserId() != null) {
//			updateQuery += "user_id = ?, ";
//			tempParam[index++] = mem.getUserId();
//		}
		if (mem.getNickname() != null) {		
			updateQuery += "nickname = ?, ";		
			tempParam[index++] = mem.getNickname();		
		}
		if (mem.getPassword() != null) {		
			updateQuery += "password = ?, ";	
			tempParam[index++] = mem.getPassword();		
		}
		if (mem.getEmail() != null) {		
			updateQuery += "email = ?, ";	
			tempParam[index++] = mem.getEmail();		
		}
//		if (mem.getBirth() != null) {		
//			updateQuery += "birth = ?, ";	
//			tempParam[index++] = mem.getBirth();		
//		}
//		if (Integer.valueOf(mem.getGender()) != null) {		
//			updateQuery += "gender = ?, ";		
//			tempParam[index++] = mem.getGender();		
//		}
//		if (mem.getTestType() != null) {		
//			updateQuery += "test_type = ?, ";		
//			tempParam[index++] = mem.getTestType();		
//		}
//		if (Float.valueOf(mem.getDrinkingCapacity()) != null) {		
//			updateQuery += "drinking_capacity = ?, ";		
//			tempParam[index++] = mem.getDrinkingCapacity();		
//		}
		updateQuery += "WHERE id = ? ";
		updateQuery = updateQuery.replace(", WHERE", " WHERE");
		
		tempParam[index++] = mem.getId();
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSqlAndParameters(updateQuery, newParam);
		
		try {
			int result = jdbcUtil.executeUpdate();
			jdbcUtil.commit();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.close();
		}		
		return 0;
	}
	
	// 아이디를 전달받아 해당 아이디의 회원 정보를 삭제하는 메소드
	public int deleteMember(String userId) {
		String deleteQuery = "DELETE FROM member WHERE user_id = ?";
		
		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(deleteQuery, param);
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			jdbcUtil.commit();
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}
	
	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingUser(String userId) throws SQLException {
		String sql = "SELECT count(*) FROM member WHERE user_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	
	/* 사용자 testType 반영 */
	public boolean updateUserTestType(String testType, long id) throws SQLException {
		String sql = "UPDATE MEMBER SET test_type=? WHERE id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {testType, id});
		
		try {
			int result = jdbcUtil.executeUpdate();
			if (result > 0) {
				jdbcUtil.commit();
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		
		return false;
	}

}
