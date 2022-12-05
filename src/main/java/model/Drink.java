package model;

public class Drink {
	private Alcohol alcohol; // 술 정보
	private int amount; // 마신 양


	public Drink() {
		super();
	}

	public Drink(Alcohol alcohol, int amount) {
		super();
		this.alcohol = alcohol;
		this.amount = amount;
	}

	public Alcohol getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Drink [alcohol=" + alcohol + ", amount=" + amount + "]";
	}
	
	public String toCalendarString() {
		return alcohol.toCalendarString() + amount + "ml";
	}

}
