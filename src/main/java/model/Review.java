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
	
	final private String[] tasteHashTag = new String[] {"맛", "감칠맛", "단맛", "신맛", "쓴맛", "짠맛"};
	final private String[] flavorHashTag = new String[] {"향", "국화", "매화", "아카시아", "연꽃", "장미",
			"감귤", "딸기", "매실", "멜론", "바나나", "배", "복숭아", "사과", "산딸기", "살구", "유자", "자두", "참외", "파인애플",
			"감자", "갓 지은 밥", "고구마", "곡물가루", "누룽지", "밀", "생쌀", "옥수수",
			"땅콩", "밤", "아몬드", "잣",
			"감초", "계피", "구기자", "대추", "더덕/도라지", "산수유나무 열매", "생간", "송순", "쑥", "오가피", "오미자", "인삼", "황기",
			"나무", "나무껍질", "대나무잎", "무", "버섯", "봉숭아", "솔잎", "연잎", "지푸라기", "풀",
			"꿀", "엿기름", "조청", "카라멜",
			"바닐라", "박하", "청향", "후추",
			"버터", "요거트", "우유", "치즈",
			"간장", "누룩향", "메주", "숙성향", "효모",
			"가열취", "고무", "곰팡이", "기름냄새", "누룩취", "먼지", "삶은 채소", "상한 부추", "상한 양파", "상한 우유",
			"쉰내", "식초", "썩은내", "유황", "이끼", "종이", "지린내", "쉰내", "흙"};
	final private String[] corpsHashTag = new String[] {"바디감", "군칠감", "떫은맛", "목넘김", "무게감",
			"부드러움", "시원함", "여운", "자극적인 맛", "톡쏘는 맛"};
	private String tasteString;
	private String flavorString;
	private String corpsString;
	
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		this.tasteString = tasteHashTag[taste];
		this.flavorString = flavorHashTag[flavor];
		this.corpsString = corpsHashTag[corps];
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
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
		if (taste != 0) {
			this.tasteString = tasteHashTag[taste]; }
	}

	public int getFlavor() {
		return flavor;
	}

	public void setFlavor(int flavor) {
		this.flavor = flavor;
		if (flavor != 0) {
			this.flavorString = flavorHashTag[flavor]; }
	}

	public int getCorps() {
		return corps;
	}

	public void setCorps(int corps) {
		this.corps = corps;
		if (corps != 0) {
			this.corpsString = corpsHashTag[corps]; }
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTasteString() {
		return tasteString;
	}

	public void setTasteString(String taste) {
		tasteString = taste;
		int i = -1;
		for (i = 1; i < tasteHashTag.length; i++) {
			if (tasteString.equals(tasteHashTag[i]))
				break;
		}
		this.taste = i;
	}

	public String getFlavorString() {
		return flavorString;
	}

	public void setFlavorString(String flavor) {
		flavorString = flavor;
		int i = -1;
		for (i = 1; i < flavorHashTag.length; i++) {
			if (flavorString.equals(flavorHashTag[i]))
				break;
		}
		this.flavor = i;
	}

	public String getCorpsString() {
		return corpsString;
	}

	public void setCorpsString(String corps) {
		corpsString = corps;
		int i = -1;
		for (i = 1; i < corpsHashTag.length; i++) {
			if (corpsString.equals(corpsHashTag[i]))
				break;
		}
		this.corps = i;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", member=" + member.getNickname() + ", alcohol=" + alcohol.getName() + ", rate=" + rate
				+ ", regDate=" + regDate + ", taste=" + taste + ", flavor=" + flavor + ", corps=" + corps + ", content="
				+ content + "]";
	}
}

