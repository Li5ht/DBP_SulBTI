package model.dao;

import java.util.*;

import model.*;
import model.service.AlcoholManager;
import model.service.UserManager;

public class Test {
	
	private static MemberDao m = new MemberDao();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		AlcoholManager alMan = AlcoholManager.getInstance();
		Alcohol alcohol = alMan.findAlcoholById(10042);
		int[] taste = alMan.numberOfTaste(10042);
		int[] flavor = alMan.numberOfFlavor(10042);
		int[] corps = alMan.numberOfCorps(10042);
		
		for (int i = 0; i < taste.length; i++) {
			System.out.print(taste[i]);
		}
		System.out.println();
		for (int i = 0; i < flavor.length; i++) {
			System.out.print(flavor[i]);
		}
		System.out.println();
		for (int i = 0; i < corps.length; i++) {
			System.out.print(corps[i]);
		}
		System.out.println();
		
		System.out.println(alcohol.calTaste(0, taste, -1, 1));
		
		scanner.close();
	}

}
