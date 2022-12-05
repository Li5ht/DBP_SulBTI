package model.service;

import java.sql.SQLException;

import model.*;
import model.dao.*;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private MemberDao userDAO;
	
	private UserManager() {
		try {
			userDAO = new MemberDao();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(Member user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.insertMember(user);
	}
	
	public int update(Member user) throws SQLException, UserNotFoundException {
		return userDAO.updateMember(user);
	}
	
	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.deleteMember(userId);
	}
	
	public Member findUser(String userId)
			throws SQLException, UserNotFoundException {
			Member user = userDAO.getMemberById(userId);
			
			if (user == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}		
			return user;
		}
	
	public Member login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
			Member user = findUser(userId);

			if (!user.matchPassword(password)) {
				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
			}
			return user;
		}
	
	public MemberDao getUserDAO() {
		return this.userDAO;
	}
	
	public long getDrinking(long id) {
		return userDAO.getDrinking(id);
	}
	
	public boolean updateUserTestType(String testType, long id) throws SQLException {
		return userDAO.updateUserTestType(testType, id);
	}
}
