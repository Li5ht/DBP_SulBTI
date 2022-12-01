<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>홈 화면</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yy.MM.dd");
%>
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script>

</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	
	
	<c:if test="${noLogin}">
		<a href="<c:url value='/user/login/form' />" class="btn btn-link">로그인 </a>
		<a href="<c:url value='/user/register' />" class="btn btn-link">회원 가입 </a>
	</c:if>
	<c:if test="${hasLogin}">
		<a href="<c:url value='/user/logout' />" class="btn btn-link">로그아웃 </a>
	</c:if>
	
	
	
	<!-- 배너 -->
	<div class="grad0">	<!-- 클릭 시 이동하게 변경 -->
		<br><br>주량이 소주 4병인 내가 와인 3병을 먹는다면?<br><br><br>
	</div>


	<br><br>
		<div class="rankBox">
			<div class="rankBox2">
				<b>종합 랭킹</b> <%= sf.format(nowTime) %> 기준<br><br>
				<c:forEach var="rank" items="${overallRank}">
					${rank.ranking}. ${rank.alcohol.imageUrl} 
					&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
					+${rank.numberOfMention}<br>
				</c:forEach>
			</div>
			<div class="rankBox2">
				<b>HOT</b> <%= sf.format(nowTime) %>기준<br><br>
				<c:forEach var="rank" items="${hotRank}">
					${rank.ranking}. ${rank.alcohol.imageUrl} 
					&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
					+${rank.numberOfMention}<br>
				</c:forEach>
			</div>
			<div class="rankBox2">
				여기엔 무슨 랭킹을...
			</div>
		</div>
	
		<br><br>
	
		<div class="goBox">
			<div class="box">
				<a href="#">음주 기록하기</a>
			</div>
			<div class="box">
				<a href="<c:url value='/recommend/list' />">술 추천받기</a>
			</div>
			<div class="box">
				<a href="#">주량 시뮬레이션하기</a>
			</div>
			<div class="box2">
				<a href="<c:url value='/recommend/test' />">술BTI 테스트 하러가기</a>
			</div>
		</div>
</body>
</html>