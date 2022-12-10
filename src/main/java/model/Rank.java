package model;

public class Rank {
	private int ranking; // 순위
	private Alcohol alcohol; // 파라미터가 name, url인 생성자 이용
	private int numberOfMention; // 언급 수
	
	public Rank() {
		super();
	}

	public Rank(int ranking, String name, String imageUrl, int taste, int flavor, int corps, int numberOfMention) {
		super();
		this.ranking = ranking;
		this.alcohol = new Alcohol(name, imageUrl, taste, flavor, corps);
		this.numberOfMention = numberOfMention;
	}
	
	public Rank(int ranking, Alcohol alcohol, int numberOfMention) {
		super();
		this.ranking = ranking;
		this.alcohol = alcohol;
		this.numberOfMention = numberOfMention;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public Alcohol getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}

	public int getNumberOfMention() {
		return numberOfMention;
	}

	public void setNumberOfMention(int numberOfMention) {
		this.numberOfMention = numberOfMention;
	}

	@Override
	public String toString() {	// 확인 용도로 출력
		return "순위: " + ranking + ", 이름: " + alcohol.getName() + 
				", 이미지링크: " + alcohol.getImageUrl() + 
				", 언급 수: " + numberOfMention;
	}
	
	
}
