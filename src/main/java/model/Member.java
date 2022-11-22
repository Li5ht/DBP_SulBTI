package model;

import java.sql.Date;

public class Member {
	private long id; 				// primary key
	private String userId; 			// id
	private String password = null;	// pw
	private String nickname = null;	// 별명
	private String email = null; 	// 이메일
	private Date birth = null;		// 생일
	private int gender;	 			// 성별
	private String testType = null;	// 테스트 결과
	private long drinkingCapacity; 	// 주량

	public Member() {
		super();
	}
	
	public Member(long id, String userId, String password, String nickname) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
	}
	
	public Member(String userId, String password, String nickname, String email, Date birth, int gender,
			String testType, long drinkingCapacity) {
		super();
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.testType = testType;
		this.drinkingCapacity = drinkingCapacity;
	}
	
	public Member(long id, String userId, String password, String nickname, String email, Date birth, int gender,
			String testType, long drinkingCapacity) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.testType = testType;
		this.drinkingCapacity = drinkingCapacity;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public long getDrinkingCapacity() {
		return drinkingCapacity;
	}
	public void setDrinkingCapacity(long drinkingCapacity) {
		this.drinkingCapacity = drinkingCapacity;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", userId=" + userId + ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + ", birth=" + birth + ", gender=" + gender + ", testType=" + testType
				+ ", drinkingCapacity=" + drinkingCapacity + "]";
	}

}
