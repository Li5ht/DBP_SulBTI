package model.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.dao.*;

public class DiaryManager {
	private static DiaryManager diary = new DiaryManager();
	private DiaryDAO diaryDAO;

	private DiaryManager() {
		try {
			diaryDAO = new DiaryDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DiaryManager getInstance() {
		return diary;
	}

	public Diary create(Diary diary) throws SQLException, ExistingDiaryException {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(diary.getDrinkingDate());
		if (diaryDAO.getDiaryListByDate(diary.getMember().getId(), date) == null) {
			throw new ExistingDiaryException(date + "일자의 기록은 이미 존재합니다.");
		}
		return diaryDAO.createDiary(diary);
	}

	public int update(Diary diary) throws SQLException, DiaryNotFoundException {
		return diaryDAO.updateDiary(diary);
	}

	public int remove(Diary diary) throws SQLException, DiaryNotFoundException {
		return diaryDAO.removeDiary(diary);
	}

	/*
	 * memberId와 날짜를 기준으로 해당하는 구간의 기록 탐색
	 */
	public List<Diary> findDiaryListBydate(long memberId, String startDate, String endDate) throws SQLException {
		List<Diary> diaryList = null;
		diaryList = diaryDAO.getDiaryListByDate(memberId, startDate, endDate);
		
		if (diaryList == null || diaryList.size() == 0) {
			diaryList = null;
		}
		
		return diaryList;
	}

	/*
	 * memberId를 기준으로 해당하는 구간의 기록 탐색
	 */
	public List<Diary> findDiaryListByMemberId(long memberId) throws SQLException {
		List<Diary> diaryList = null;
		diaryList = diaryDAO.getDiaryListByMemberId(memberId);
		
		if (diaryList == null || diaryList.size() == 0) {
			diaryList = null;
		}
		
		return diaryList;
	}
	
	/*
	 * memberId와 날짜를 기준으로 해당하는 일자 기록 탐색
	 */
	public Diary findDiaryBydate(long memberId, String date) throws SQLException {
		List<Diary> diaryList = diaryDAO.getDiaryListByDate(memberId, date);
		Diary diary = null;
		
		if (!(diaryList == null || diaryList.size() == 0)) {
			diary = diaryList.get(0);
		}
		
		return diary;
	}

	/*
	 * diaryId로 diary 객체 찾음
	 */
	public Diary findDiary(long diaryId) throws SQLException, DiaryNotFoundException {
		Diary diary = diaryDAO.getDiary(diaryId);

		if (diary == null) {
			throw new DiaryNotFoundException(diaryId + "는 존재하지 않는 음주 기록 id입니다.");
		}
		return diary;
	}

	public DiaryDAO getDiaryDAO() {
		return this.diaryDAO;
	}
	
	public int updateDiary(Diary diary) throws SQLException, DiaryNotFoundException {
		return diaryDAO.updateDiary(diary);
	}
	
	public int deleteDiary(Diary diary) throws SQLException, DiaryNotFoundException {
		return diaryDAO.removeDiary(diary);
	}

}
