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
								         "member.userId, " +
								         "member.nickname, " +
								         "member.password, " +
								         "member.email, " +
								         "member.birth, " +
								         "member.gender, " +
								         "member.testType, " +
								         "member.drinkingCapacity ";	
	public long getDrinking(long id) {
		String searchQuery = "SELECT member.drinkingCapacity  "
				+ "FROM member "
				+ "WHERE member.id = ? ";
		
		Object[] param = new Object[] { id };
		jdbcUtil.setSqlAndParameters(searchQuery, param);

		long drinkingCapacity = -1;
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				drinkingCapacity = rs.getLong("drinkingCapacity");
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
				dto.setUserId(rs.getString("userId"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
				dto.setGender(rs.getInt("gender"));
				dto.setTestType(rs.getString("testType"));
				dto.setDrinkingCapacity(rs.getLong("drinkingCapacity"));
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
				dto.setUserId(rs.getString("userId"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPassword(rs.getString("password"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getDate("birth"));
				dto.setGender(rs.getInt("gender"));
				dto.setTestType(rs.getString("testType"));
				dto.setDrinkingCapacity(rs.getLong("drinkingCapacity"));
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
		String insertQuery = "INSERT INTO member (id, userId, nickname, password, email, birth, gender, testType, drinkingCapacity) " +
							 "VALUES (id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		Object[] param = new Object[] {mem.getUserId(), mem.getNickname(), mem.getPassword(), mem.getEmail(), mem.getBirth(), mem.getGender(), mem.getTestType(), mem.getDrinkingCapacity()};		
		jdbcUtil.setSql(insertQuery);
		jdbcUtil.setParameters(param);
				
		try {				
			result = jdbcUtil.executeUpdate();
			System.out.println(mem.getId() + " 회원정보가 삽입되었습니다.");
		} catch (SQLException ex) {
			System.out.println("입력오류 발생!!!");
			if (ex.getErrorCode() == 1)
				System.out.println("동일한 회원정보가 이미 존재합니다."); 
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return result;
	}
	
	// Member 객체에 설정되어 있는 정보를 토대로 테이블의 정보를 수정하는 메소드
	public int updateMember(Member mem) {
		
		String updateQuery = "UPDATE member SET ";
		int index = 0;
		Object[] tempParam = new Object[10];
		
		if(Long.valueOf(mem.getId()) != null) {
			updateQuery += "id = ?, ";		
			tempParam[index++] = mem.getId();
		}
		if (mem.getUserId() != null) {
			updateQuery += "userId = ?, ";
			tempParam[index++] = mem.getUserId();
		}
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
		if (mem.getBirth() != null) {		
			updateQuery += "birth = ?, ";	
			tempParam[index++] = mem.getBirth();		
		}
		if (Integer.valueOf(mem.getGender()) != null) {		
			updateQuery += "gender = ?, ";		
			tempParam[index++] = mem.getGender();		
		}
		if (mem.getTestType() != null) {		
			updateQuery += "testType = ?, ";		
			tempParam[index++] = mem.getTestType();		
		}
		if (Long.valueOf(mem.getDrinkingCapacity()) != null) {		
			updateQuery += "drinkingCapacity = ?, ";		
			tempParam[index++] = mem.getDrinkingCapacity();		
		}
		updateQuery += "WHERE id = ? ";
		updateQuery = updateQuery.replace(", WHERE", " WHERE");
		
		tempParam[index++] = mem.getId();
		
		Object[] newParam = new Object[index];
		for (int i=0; i < newParam.length; i++)
			newParam[i] = tempParam[i];
		
		jdbcUtil.setSql(updateQuery);
		jdbcUtil.setParameters(newParam);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}
	
	// 아이디를 전달받아 해당 아이디의 회원 정보를 삭제하는 메소드
	public int deleteMember(long id) {
		String deleteQuery = "DELETE FROM member WHERE id = ?";
		
		jdbcUtil.setSql(deleteQuery);			// JDBCUtil 에 query 문 설정
		Object[] param = new Object[] { id };
		jdbcUtil.setParameters(param);			// JDBCUtil 에 매개변수 설정
		
		try {
			int result = jdbcUtil.executeUpdate();		// delete 문 실행
			return result;						// delete 에 의해 반영된 레코드 수 반환
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();		
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection 반환
		}
		return 0;
	}

}
