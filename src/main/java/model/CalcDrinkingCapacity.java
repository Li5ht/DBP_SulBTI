package model;

import java.util.*;

/* 주량 계산하는 클래스 */
public class CalcDrinkingCapacity {
	private Drink userDrinkingCapacity;
	private List<Drink> drinkList;
	
	public CalcDrinkingCapacity() {
		super();
	}
	
	public CalcDrinkingCapacity(Drink userDrinkingCapacity, List<Drink> drinkList) {
		super();
		this.userDrinkingCapacity = userDrinkingCapacity;
		this.drinkList = drinkList;
	}
	
	public Drink getUserDrinkingCapacity() {
		return userDrinkingCapacity;
	}
	public void setUserDrinkingCapacity(Drink userDrinkingCapacity) {
		this.userDrinkingCapacity = userDrinkingCapacity;
	}
	public List<Drink> getDrinkList() {
		return drinkList;
	}
	public void setDrinkList(List<Drink> drinkList) {
		this.drinkList = drinkList;
	}

	@Override
	public String toString() {
		return "CalcDrinkingCapacity [userDrinkingCapacity=" + userDrinkingCapacity + ", drinkList=" + drinkList + "]";
	}
	
	/* 알콜량 계산하는 메소드 */
	public float theAmountOfAlcohol(Drink drink) {
		// 도수 (ex. 4.5% => 0.045) * 양 = 알콜량
		return drink.getAmount() * (float) (drink.getAlcohol().getAlcoholLevel() * 0.01);
	}
	
	/* 알콜량에 따른 마실 수 있는 술의 양 */
	public int drinkableAmount(float amount, float alcoholLevel) { // 알콜량, 도수
		return (int) (amount / (alcoholLevel * 0.01));
	}
	
	/* 최종 계산 */
	public int calculate() {
		/* 상태에 따른 정수 결과 반환   
		 * 사용자 주량 알콜량 계산 후, 마실 양의 총 알콜량을 계산하여 결과 반환
		 * 0 : 상태 이상 없음 & 나른함
		 * 1 : 기분 업 & 말 많아짐
		 * 2 : 말이 꼬이기 시작 & 휘청거림
		 * 3 : 바닥과 인사 & 구토
		 * 4 : 기절
		 *  */
		
		float userDC = theAmountOfAlcohol(userDrinkingCapacity);	// 사용자 알콜량
		float drinkingListDC = 0;	// 마실 양의 알콜량 저장할 변수
		
		for (Drink drink : drinkList) {
			drinkingListDC += theAmountOfAlcohol(drink);	// 마실 양의 알콜량 계산
		}
		
		float value = drinkingListDC / userDC;		// 마실 양의 알콜량 / 주량(알콜량)
		
		
		if (value <= 0.5271219135)	// 상태 이상 없음 & 나른함
			return 0;
		else if (0.5271219135 < value && value <= 1)	// 기분 업 & 말 많아짐
			return 1;
		else if (1 < value && value <= 1.335416667)	// 말이 꼬이기 시작 & 휘청거림
			return 2;
		else if (1.461226852 < value && value <= 1.587037037)	// 바닥과 인사 & 구토
			return 3;
		else 	// 기절
			return 4;
	}
}
