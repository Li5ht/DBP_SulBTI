package model;

import java.util.Date;

public class Member {
	private long id; 				// primary key
	private String user_id; 			// id
	private String password = null;	// pw
	private String nickname = null;	// 별명
	private String email = null; 	// 이메일
	private Date birth = null;		// 생일
	private int gender;	 			// 성별
	private String test_type = null;	// 테스트 결과
	private float drinking_capacity; 	// 주량

	public Member() {
		super();
	}
	
	public Member(long id, String user_id, String password, String nickname) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
	}
	
	public Member(String user_id, String password, String nickname, String email, Date birth, int gender,
			String test_type, float drinking_capacity) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.test_type = test_type;
		this.drinking_capacity = drinking_capacity;
	}
	
	public Member(long id, String user_id, String password, String nickname, String email, Date birth, int gender,
			String test_type, float drinking_capacity) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.test_type = test_type;
		this.drinking_capacity = drinking_capacity;
	}

	public Member(String password, String nickname, String email) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String user_id) {
		this.user_id = user_id;
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
		return test_type;
	}
	public void setTestType(String test_type) {
		this.test_type = test_type;
	}
	public float getDrinkingCapacity() {
		return drinking_capacity;
	}
	public void setDrinkingCapacity(float drinking_capacity) {
		this.drinking_capacity = drinking_capacity;
	}
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", user_id=" + user_id + ", password=" + password + ", nickname=" + nickname
				+ ", email=" + email + ", birth=" + birth + ", gender=" + gender + ", test_type=" + test_type
				+ ", drinking_capacity=" + drinking_capacity + "]";
	}

}
