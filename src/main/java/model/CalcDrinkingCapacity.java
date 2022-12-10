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
	
	/* 최종 계산 */
	public int calculate() {
		/* 상태에 따른 정수 결과 반환
		 * 사용자 주량 알콜량 계산 후, 마실 양의 총 알콜량을 계산하여 결과 반환
		 * 0 : 상태 이상 없음
		 * 1 : 약간 헤롱함
		 * 2 : ...정해야됨
		 *  */
		
		float userDC = theAmountOfAlcohol(userDrinkingCapacity);	// 사용자 알콜량
		float drinkingListDC = 0;	// 마실 양의 알콜량 저장할 변수
		
		for (Drink drink : drinkList) {
			drinkingListDC += theAmountOfAlcohol(drink);	// 마실 양의 알콜량 계산
		}
		
		float dcGap = userDC - drinkingListDC;		// 사용자 알콜량과 마실 양의 알콜량 차이
		
		if (dcGap > 0.5)	// 추후 수정!! 아직 안 정함
			return 0;
		else if (dcGap <= 0.5 && dcGap > -0.5)
			return 1;
		else
			return 2;
	}
}
