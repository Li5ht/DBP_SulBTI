package model;

import java.util.Date;
import java.util.List;

public class Diary {
	private long diaryId; 
	private Member member; // diary 작성자
	private Date drinkingDate; // 술을 마신 날짜
	private int condition; // 술을 마신 후 컨디션
	private List<Drink> drinkingList; // 마신 술 내역
	private String content; // 내용

	public Diary() {
		super();
	}
	
	public Diary(Member member, Date drinkingDate, int condition, List<Drink> drinkingList,
			String content) {
		super();
		this.member = member;
		this.drinkingDate = drinkingDate;
		this.condition = condition;
		this.drinkingList = drinkingList;
		this.content = content;
	}

	public Diary(long diaryId, Member member, Date drinkingDate, int condition, List<Drink> drinkingList,
			String content) {
		super();
		this.diaryId = diaryId;
		this.member = member;
		this.drinkingDate = drinkingDate;
		this.condition = condition;
		this.drinkingList = drinkingList;
		this.content = content;
	}

	public long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getDrinkingDate() {
		return drinkingDate;
	}

	public void setDrinkingDate(Date drinkingDate) {
		this.drinkingDate = drinkingDate;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public List<Drink> getDrinkingList() {
		return drinkingList;
	}

	public void setDrinkingList(List<Drink> drinkingList) {
		this.drinkingList = drinkingList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Diary [diaryId=" + diaryId + ", member=" + member + ", drinkingDate=" + drinkingDate + ", condition="
				+ condition + ", drinkingList=" + drinkingList + ", content=" + content + "]";
	}

}
