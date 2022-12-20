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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function($) {
	// select 선택에 맞게 보여지는 div 변화
    $('#selectBox').change(function() {
        var result = $("select[name=selectBox]").val();
        if (result == 'soju') {
        	$('.beer').hide();
        	$('.traditional').hide();
        	$('.wine').hide();
        	$('.spirits').hide();
        	$('.cocktail').hide();
        	
          	$('.soju').show();
        } else if (result == 'beer') {
          	$('.soju').hide();
          	$('.traditional').hide();
        	$('.wine').hide();
        	$('.spirits').hide();
        	$('.cocktail').hide();
          	
          	$('.beer').show();
        } else if (result == 'traditional') {
          	$('.soju').hide();
          	$('.beer').hide();
        	$('.wine').hide();
        	$('.spirits').hide();
        	$('.cocktail').hide();
          	
          	$('.traditional').show();
        } else if (result == 'wine') {
          	$('.soju').hide();
          	$('.traditional').hide();
        	$('.beer').hide();
        	$('.spirits').hide();
        	$('.cocktail').hide();
          	
          	$('.wine').show();
        } else if (result == 'spirits') {
          	$('.soju').hide();
          	$('.traditional').hide();
        	$('.wine').hide();
        	$('.beer').hide();
        	$('.cocktail').hide();
          	
          	$('.spirits').show();
        } else if (result == 'cocktail') {
          	$('.soju').hide();
          	$('.traditional').hide();
        	$('.wine').hide();
        	$('.spirits').hide();
        	$('.beer').hide();
          	
          	$('.cocktail').show();
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
					<option value="traditional">전통주</option>
					<option value="wine">와인</option>
					<option value="spirits">양주</option>
					<option value="cocktail">칵테일</option>
				</select><br><br>
				
				<div class="soju" id="soju"> <!-- 소주 -->
					<c:forEach var="rank" items="${soju}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="beer" id="beer"> <!-- 맥주 -->
					<c:forEach var="rank" items="${beer}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="traditional" id="traditional"> <!-- 전통주 -->
					<c:forEach var="rank" items="${traditional}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="wine" id="wine"> <!-- 와인 -->
					<c:forEach var="rank" items="${wine}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="spirits" id="spirits"> <!-- 양주 -->
					<c:forEach var="rank" items="${spirits}">
						${rank.ranking}. 
						<img src='${rank.alcohol.imageUrl}' width="auto" height="60px">
						&nbsp;&nbsp;&nbsp; ${rank.alcohol.name}
						+${rank.numberOfMention}<br><br>
					</c:forEach>
				</div>
				
				<div class="cocktail" id="cocktail"> <!-- 칵테일 -->
					<c:forEach var="rank" items="${cocktail}">
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