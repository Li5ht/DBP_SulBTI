package model.dao;

import java.util.*;

import model.*;
import model.service.AlcoholManager;
import model.service.RecommendManager;
import model.service.UserManager;

public class Test {
	
	private static MemberDao m = new MemberDao();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		RecommendManager recMan = RecommendManager.getInstance();
		List<Drink> drinkList = recMan.userRecommendList(41);
		
		if (drinkList == null) {
			System.out.println("없어요");
		}
		else {
			for (Drink drink : drinkList) {
				System.out.println(drink.toString());
			}
		}
		
		
		scanner.close();
	}

}
