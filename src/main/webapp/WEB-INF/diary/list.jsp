<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.*"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Date"%>
<%!
public class MyCalendar {
	
//	년도를 넘겨받아 윤년/ 평년을 판단해 윤년이면 true, 평년이면 false를 리턴하는 메서드
	public boolean isLeapYear(int year) {
		return (year % 4 ==0) && (year % 100 !=0) ||(year % 400 ==0);
	}
	
//	년, 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메서드
	public int lastDay(int year ,int month ) {
		int[] m = {31,28,31,30,31,30,31,31,30,31,30,31};
		m[1]=isLeapYear(year)? 29:28;
		return m[month-1];
	}
	
	
//	년, 월, 일을 념겨받아 1년 1월 1일부터 지나온 날짜의 합계를 리턴하는 메서드	
	public int totalDay(int year, int month, int day) {
		int sum = (year-1)*365 +(year-1)/4 - (year-1)/100 +(year-1)/400;
		for (int i = 1; i < month; i++) {
			sum += lastDay(year,i);
		}
		return sum + day;
	}
	
//	년, 월, 일을 넘겨받아 요일을 숫자로 리턴하는 메서드, 일요일(0),월요일(1)....토요일(6)
	public int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}

}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/diary.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>음주 기록</title>
<!-- 스타일 적용시켜주기 -->
<style type="text/css">
/* 기본스타일  */	
	tr{height: 100px;}
	tr.top {height: 30px;}
	td{width: 200px; text-align: right; font-size: 12pt; font-family: D2coding; border: 1px solid #DCDCDF;}

/* 요일 스타일 */
	th.sunday{text-align: right; font-weight: bold; color: gray; font-family: D2coding; }
	th.saturday{text-align: right; font-weight: bold; color: gray; font-family: D2coding; }
	th.etcday{text-align: right; font-weight: bold; color: black; font-family: D2coding; }

/* 날짜 스타일 */
	td.sun{ text-align: right; font-size: 12pt; background-color: #F4F5F4; color: black; font-family: D2coding; vertical-align: top;}
	td.sat{ text-align: right; font-size: 12pt; background-color: #F4F5F4; color: black; font-family: D2coding; vertical-align: top;}
	td.etc{ text-align: right; font-size: 12pt; color: black; font-family: D2coding; vertical-align: top;}
	
	td.redbefore{ text-align: right; font-size: 12pt; background-color: #F4F5F4; color: #DCDCDF; font-family: D2coding; vertical-align: top;}
	td.before{ text-align: right; font-size: 12pt; color: #DCDCDF; font-family: D2coding; vertical-align: top;}
	
	input {border:0; outline:0; color: #79BD9A; background-color:transparent;}

</style>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="grad1">
		<br><br><br><br><br> D I A R Y <br><br><br>
	</div>
		
		
	<!-- 음주 기록 -->
	<!-- <div class="diaryBox1"> -->
	
	<!-- 음주 기록 추가 -->
	<!--  
	<button type="button" class="btn btn-outline-success" onclick="location.href='create'">음주 기록 추가하기</button>
	-->
	<%
	// 컴퓨터 시스템의 년, 월 받아오기
		Date date = new Date();
		MyCalendar MyCalendar = new MyCalendar();
		int year = date.getYear() +1900;
		int month = date.getMonth() +1;
	
		//	오류사항 걸러주기	
		try{
			List<Diary> tmpList = (List<Diary>) request.getAttribute("diaryList");
			List<Diary> diaryList = new ArrayList<Diary>();
			
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month"));
			
			if (month >= 13){
				year++;
				month = 1;
			} else if(month <= 0){
				year--;
				month = 12;
			}
			
			for (Diary diary : tmpList) {
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				String str = df.format(diary.getDrinkingDate());
				int tmpYear = Integer.parseInt(str.substring(0, 4));
				int tmpMonth = Integer.parseInt(str.substring(str.length() - 4, str.length() - 2));
				
				if (tmpYear == year && tmpMonth == month) {
					diaryList.add(diary);
				}
			}
			request.setAttribute("list", diaryList);
			
		}catch(Exception e){
			
		}
	
	%>
	
	<br>
	<!-- 달력 만들기 -->
	<div class="parent">
	<div class="diaryBox1">
	
	<!-- 날짜 -->
	<div class="date">
		<input type="button" value="◁" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
		<strong><%=year%>년  <%=month%>월</strong>
		<input type="button" value="▷" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
	</div><br/><br/>
	
	<table align ="center">
	<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
		<tr class="top">
			<th class = "sunday">일</th>
			<th class = "etcday">월</th>
			<th class = "etcday">화</th>
			<th class = "etcday">수</th>
			<th class = "etcday">목</th>
			<th class = "etcday">금</th>
			<th class = "saturday">토</th>
		</tr>
		
	<!-- 날짜 집어 넣기 -->
		<tr>
		<%
	//	1일의 요일을 계산한다(자주 쓰이기 때문에 변수로 선언해두기)
			int first = MyCalendar.weekDay(year, month, 1);
		
	//	1일이 출력될 위치 전에 전달의 마지막 날짜들을 넣어주기위해 전 달날짜의 시작일을 계산한다.
			int start = 0 ;
			start = month ==1? MyCalendar.lastDay(year-1, 12)- first : MyCalendar.lastDay(year, month-1)- first;
	
	//	1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하여 전달의날짜를 출력한다.
			for(int i= 1; i<= first; i++){
				if(i==1){
	/* 일요일(빨간색)과 다른날들의 색을 구별주기  */
					out.println("<td class = 'redbefore'>"+ (++start) +"일</td>");
				}else{
					out.println("<td class = 'before'>"+ (++start) +"일</td>");
					
				}
			}
	
	/* 1일부터 달력을 출력한 달의 마지막 날짜까지 반복하며 날짜를 출력 */
			List<Diary> diaryList = (List<Diary>) request.getAttribute("list");
			if (diaryList == null) {
				diaryList = (List<Diary>) request.getAttribute("currentDiaryList");
			}
			
			String[] list = new String[32];
			long[] idList = new long[32];
			for (int i = 0; i < 32; i++) {
				list[i] = "";
				idList[i] = -1;
			}
			if (diaryList != null) {
				for (Diary diary : diaryList) {
					DateFormat df = new SimpleDateFormat("yyyyMMdd");
					String str = df.format(diary.getDrinkingDate());
					int day = Integer.parseInt(str.substring(str.length() - 2, str.length()));
					for (Drink drink : diary.getDrinkingList()) {
						list[day] += "<br>" + drink.toCalendarString() + "\n";
					}
					idList[day] = diary.getDiaryId();
				}
			}
			
			
			for(int i = 1; i <= MyCalendar.lastDay(year, month); i++){
				String url = "<br><a href='view?diaryId=" + idList[i] + "' " + list[i] + "</a>";
				/* 요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기 */
				switch(MyCalendar.weekDay(year, month, i)){
					case 0 :
						out.println("<td class ='sun'>" + i + "일" + url + "</td>");
						break;
					case 6 :
						out.println("<td class ='sat'>" + i + "일" + url + "</td>");
						break;
					default :
						out.println("<td class ='etc'>" + i + "일" + url + "</td>");
						break;
				}
				
	/* 출력한 날짜(i)가 토요일이고 그달의 마지막 날짜이면 줄을 바꿔주기 */
				if(MyCalendar.weekDay(year, month, i) == 6 && i != MyCalendar.lastDay(year, month)){
					out.println("</tr><tr>");			
				}
			}
			if(MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month)) !=6){
				for(int i = MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month))+1; i < 7; i++){
					out.println("<td></td>");	
				}
			}
	
		%>
		</tr>
		
	</table>
	
	
	</div>
	<!-- 상세보기 -->
	<!-- 
	<div class="diaryBox2">
		여기서 include jsp파일
	</div>
	 --> 
	</div>
	
</body>
</html>