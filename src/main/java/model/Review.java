package model;

import java.util.Date;

public class Review {
	private long reviewId;
	private Member member;		// 작성자
	private Alcohol alcohol; 	// 술
	private float rate;			// 별점
	private Date regDate;
	private int taste;
	private int flavor;
	private int corps;
	private String content;
	
	public Review() {
		super();
	}
	
	public Review(Member member, Alcohol alcohol, float rate, int taste, 
			int flavor, int corps, String content) {
		super();
		this.member = member;
		this.alcohol = alcohol;
		this.rate = rate;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
		this.content = content;
	}
	
	public Review(Member member, Alcohol alcohol, float rate, Date regDate, int taste, 
			int flavor, int corps, String content) {
		super();
		this.member = member;
		this.alcohol = alcohol;
		this.rate = rate;
		this.regDate = regDate;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
		this.content = content;
	}
	
	public Review(long reviewId, Member member, Alcohol alcohol, float rate, int taste, 
			int flavor, int corps, String content) {
		super();
		this.reviewId = reviewId;
		this.member = member;
		this.alcohol = alcohol;
		this.rate = rate;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
		this.content = content;
	}

	public Review(long reviewId, Member member, Alcohol alcohol, float rate, Date regDate, int taste, 
			int flavor, int corps, String content) {
		super();
		this.reviewId = reviewId;
		this.member = member;
		this.alcohol = alcohol;
		this.rate = rate;
		this.regDate = regDate;
		this.taste = taste;
		this.flavor = flavor;
		this.corps = corps;
		this.content = content;
	}

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Alcohol getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getFlavor() {
		return flavor;
	}

	public void setFlavor(int flavor) {
		this.flavor = flavor;
	}

	public int getCorps() {
		return corps;
	}

	public void setCorps(int corps) {
		this.corps = corps;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", member=" + member.getNickname() + ", alcohol=" + alcohol.getName() + ", rate=" + rate
				+ ", regDate=" + regDate + ", taste=" + taste + ", flavor=" + flavor + ", corps=" + corps + ", content="
				+ content + "]";
	}
}

