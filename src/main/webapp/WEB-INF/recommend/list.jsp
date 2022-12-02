<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>추천 리스트</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script>
$(document).ready(function($) {
    $(".scroll_move").click(function(event){
        console.log(".scroll_move");         
        event.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top}, 500);
    });

});

</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="d0">
		<div class="grad1">
			<br><br><br><br><br> A L L R E C O M M E N D A T I O N <br><br><br>
		</div>
		
		
		<br>
		<table class="t1"><tr class="tr1">
			<td class="td1"><hr width="80%" ></td>
			<td> R E C O M M E N D A T I O N </td>
			<td class="td1"><hr width="80%" ></td>
		</tr></table>
		
		<table><tr>
			<td><a href="#recommend1" class="scroll_move">나만을 위한 추천</a></td>
			<td><a href="#recommend2" class="scroll_move">랭킹 기반 추천</a></td>
			<td><a href="#recommend3" class="scroll_move">최근 인기 순위 추천</a></td>
			<td><a href="<c:url value='/recommend/test'/>">테스트 기반 추천</a></td>
		</tr></table>
	</div>
	
	<div class="d1">
		
		
		
		<!-- 일단 css 다 빼고 기능만 작성 -->
		
		<div id="recommend1">
			<br><br><br><br><br><br><br><br><br><br><br><br>
			<b>회원님을 위한 추천</b>
			<table><tr>
				<c:forEach var="drink" items="${userRecList}">
					<td>
						술 이름 : ${drink.alcohol.name} <br>
						이미지 링크 : ${drink.alcohol.imageUrl} <br>
						#${drink.alcohol.taste} #${drink.alcohol.flavor} #${rank.alcohol.corps} <br>
						마셔도 되는 양 : ${drink.amount} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		<br><br><br>
		<div id="recommend2">
			<b>랭킹</b>
			<table><tr>
				<c:forEach var="rank" items="${overallRank}">
					<td>
						순위 : ${rank.ranking} <br>
						술 이름 : ${rank.alcohol.name} <br>
						이미지 링크 : ${rank.alcohol.imageUrl} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		<br><br><br>
		<div id="recommend3">
			<b>최근 인기 순위 추천</b>
			<table><tr>
				<c:forEach var="rank" items="${hotRank}">
					<td>
						순위 : ${rank.ranking} <br>
						술 이름 : ${rank.alcohol.name} <br>
						이미지 링크 : ${rank.alcohol.imageUrl} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		<!-- 타입 별 랭킹....
		<div id="recommend4">
			
		</div> -->
		
	</div>
</body>
</html>