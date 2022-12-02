package model;

import java.util.*;

/* 주량 계산하는 클래스 */
public class CalcDrinkingCapacity {
	private float userDrinkingCapacity;
	private List<Drink> drinkList;
	
	public CalcDrinkingCapacity() {
		super();
	}
	
	public CalcDrinkingCapacity(float userDrinkingCapacity, List<Drink> drinkList) {
		super();
		this.userDrinkingCapacity = userDrinkingCapacity;
		this.drinkList = drinkList;
	}
	
	public float getUserDrinkingCapacity() {
		return userDrinkingCapacity;
	}
	public void setUserDrinkingCapacity(float userDrinkingCapacity) {
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
	
	public int calculate() {
		/* 상태에 따른 정수 결과 반환 (0이면 무슨 상태, 1이면 무슨 상태 이런 식) */
		
		// 추후 수정 ㅎ;
		
		
		return 0;
	}
}
