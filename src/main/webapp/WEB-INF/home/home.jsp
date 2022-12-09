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
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function($) {
	// select 선택에 맞게 보여지는 div 변화
    $('#selectBox').change(function() {
        var result = $("select[name=selectBox]").val();
        if (result == 'soju') {
        	$('.beer').hide();
        	
          	$('.soju').show();
        } else {
          	$('.soju').hide();
          	
          	$('.beer').show();
        }
      }); 
});
</script>

</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	
	<!-- 배너 -->
	<div class="grad0">
		<br><br>주량이 소주 4병인 내가 와인 3병을 먹는다면?<br><br><br>
	</div>


	<br><br>
		<div class="rankBox">
			<div class="rankBox2">
				<b>종합 랭킹</b> <%= sf.format(nowTime) %> 기준<br><br>
				<c:forEach var="rank" items="${overallRank}">
					${rank.ranking}. 
					<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
					&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
					+${rank.numberOfMention}<br><br>
				</c:forEach>
			</div>
			<div class="rankBox2">
				<b>HOT</b> <%= sf.format(nowTime) %>기준<br><br>
				<c:forEach var="rank" items="${hotRank}">
					${rank.ranking}. 
					<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
					&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
					+${rank.numberOfMention}<br><br>
				</c:forEach>
			</div>
			<div class="rankBox2">
				<b>주종별 랭킹</b> <%= sf.format(nowTime) %>기준
				<select id="selectBox" name="selectBox">
					<option value="soju" selected="selected">소주</option>
					<option value="beer">맥주</option>
				</select><br><br>
				
				<div class="soju" id="soju">
					<c:forEach var="rank" items="${soju}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="beer" id="beer">
					<c:forEach var="rank" items="${beer}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
			</div>
		</div>
	
		<br><br>
	
		<div class="goBox">
			<div class="box">
				<a href="<c:url value='/diary/list' />">음주 기록하기</a>
			</div>
			<div class="box">
				<a href="<c:url value='/recommend/list' />">술 추천받기</a>
			</div>
			<div class="box">
				<a href="<c:url value='/simulate' />">주량 시뮬레이션하기</a>
			</div>
			<div class="box2">
				<a href="<c:url value='/recommend/test' />">술BTI 테스트 하러가기</a>
			</div>
		</div>
		<br><br>
</body>
</html>