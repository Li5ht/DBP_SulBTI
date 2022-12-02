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
<title>음주 기록</title>
<!-- 스타일 적용시켜주기 -->
<style type="text/css">
/* 기본스타일  */	
	
	tr{height: 60px;}
	td{width: 100px; text-align: right; font-size: 15pt; font-family: D2coding;}
/* 타이틀 스타일 */
	th#title {font-size: 20pt; font-weight: bold; font-family: D2coding; }

/* 요일 스타일 */
	td.sunday{ text-align: center; font-weight: bold; color: red; font-family: D2coding; }
	td.saturday{ text-align: center; font-weight: bold; color: blue; font-family: D2coding; }
	td.etcday{ text-align: center; font-weight: bold; color: black; font-family: D2coding; }

/* 날짜 스타일 */
	td.sun{ text-align: right; font-size: 15pt; color: red; font-family: D2coding; vertical-align: top;}
	td.sat{ text-align: right; font-size: 15pt; color: blue; font-family: D2coding; vertical-align: top;}
	td.etc{ text-align: right; font-size: 15pt; color: black; font-family: D2coding; vertical-align: top;}
	
	td.redbefore{ text-align: right; font-size: 12pt; color: red; font-family: D2coding; vertical-align: top;}
	td.before{ text-align: right; font-size: 12pt; color: gray; font-family: D2coding; vertical-align: top;}
	
	input {border:0; outline:0; color:#79bd9a; background-color:transparent;}

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
		
	<%
	// 컴퓨터 시스템의 년, 월 받아오기
		Date date = new Date();
		MyCalendar MyCalendar = new MyCalendar();
		int year = date.getYear() +1900;
		int month = date.getMonth() +1;
	
		//	오류사항 걸러주기	
		try{
			year = Integer.parseInt(request.getParameter("year"));
			month = Integer.parseInt(request.getParameter("month"));
			
			if(month>=13){
				year++;
				month =1;
			}else if(month <=0){
				year--;
				month =12;
			}
		}catch(Exception e){
			
		}
	
	%>
	<!-- 달력 만들기 -->
	<table width ="700" align ="center" border ="1" cellpadding="5" cellspacing="0">
		<tr>
	<!-- 이전달 버튼 만들기 -->
			<th>
			<%-- <a href ="?year=<%=year%>&month=<%month-1%>">이전 달</a> --%>
			<input type="button" value="◀" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
			</th>
			
	<!-- 제목 만들기 -->
			<th id = "title" colspan = "5">
			<%=year%>년  <%=month%>월
			</th>
			
	<!-- 다음달 버튼 만들기 -->
			<th>
			<%-- <a href ="?year=<%=year%>&month=<%month+1%>">다음 달</a> --%>
			<input type="button" value="▶" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
			
			</th>
		</tr>
	<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
		<tr>
			<td class = "sunday">일</td>
			<td class = "etcday">월</td>
			<td class = "etcday">화</td>
			<td class = "etcday">수</td>
			<td class = "etcday">목</td>
			<td class = "etcday">금</td>
			<td class = "saturday">토</td>
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
					out.println("<td class = 'redbefore'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
				}else{
					out.println("<td class = 'before'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
					
				}
			}
	
	/* 1일부터 달력을 출력한 달의 마지막 날짜까지 반복하며 날짜를 출력 */
			for(int i = 1; i <= MyCalendar.lastDay(year, month); i++){
				/* 요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기 */
				switch(MyCalendar.weekDay(year, month, i)){
					case 0 :
						out.println("<td class ='sun'>" +i +"</td>");
						break;
					case 6 :
						out.println("<td class ='sat'>" +i +"</td>");
						break;
					default :
						out.println("<td class ='etc'>" +i +"</td>");
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
		
		<!-- 상세보기 --> 
		<!-- 시간되면 비동기로 구현.. -->
		<!--
		<div class="diaryBox2">
			<table>
			</table>
		</div>
		  -->	
</body>
</html>