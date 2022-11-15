package model.dao;

import java.util.List;
import java.util.Scanner;

import model.Alcohol;
import model.Rank;

public class Test {
	
	private static RecommendDao recDao = new RecommendDao();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		/*rank test 코드
		List<Rank> recentRank = recDao.rankByRecentIncrease();
		System.out.println("최근 언급 랭킹");
		for (Rank r : recentRank) {
			System.out.println(r.toString());
		}
		System.out.println();
		
		List<Rank> popRank = recDao.rankByPopularity();
		System.out.println("인기순 랭킹");
		for (Rank r : popRank) {
			System.out.println(r.toString());
		}
		System.out.println();
		
		
		List<Rank> typeRank = recDao.rankByType("소주");
		System.out.println("소주 랭킹");
		for (Rank r : typeRank) {
			System.out.println(r.toString());
		}
		System.out.println();
		
		List<Rank> typeRank2 = recDao.rankByType("맥주");
		System.out.println("맥주 랭킹");
		for (Rank r : typeRank2) {
			System.out.println(r.toString());
		}
		System.out.println(); */
		
		
		/* 술 주종별 리스트
		System.out.println("주종별 리스트");
		List<Alcohol> aList = recDao.listByType("소주");
		for (Alcohol a : aList) {
			System.out.println(a.toString());
		}
		System.out.println();
		
		System.out.println("검색 기능");
		List<Alcohol> aList2 = recDao.searchAlcohol("참이슬");
		for (Alcohol a : aList2) {
			System.out.println(a.toString());
		}
		System.out.println();
		
		int pId = recDao.addPreferenceRate(4, 10001, (float) 3.5);
		System.out.println(pId);*/
		
		scanner.close();
	}

}
