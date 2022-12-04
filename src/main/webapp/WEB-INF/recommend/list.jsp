<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>추천 리스트</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function($) {
    $(".scroll_move").click(function(event){
        console.log(".scroll_move");
        event.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top}, 500);
    });

    // select 선택에 맞게 보여지는 div 변화 (안먹힘ㅠ)
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
		<div class="grad1">
			<br><br><br><br><br> A L L R E C O M M E N D A T I O N <br><br><br>
		</div>
		
		
		<br>
		<table class="banner"><tr class="tr1">
			<td class="td1"><hr width="80%" ></td>
			<td> R E C O M M E N D A T I O N </td>
			<td class="td1"><hr width="80%" ></td>
		</tr></table>
		
		<br>
		<table class="recTable"><tr>
			<td class="recTd"><a href="#recommend1" class="scroll_move">나만을 위한 추천</a></td>
			<td class="recTd"><a href="#recommend2" class="scroll_move">랭킹 기반 추천</a></td>
			<td class="recTd"><a href="#recommend3" class="scroll_move">최근 인기 순위 추천</a></td>
			<td class="recTd"><a href="<c:url value='/recommend/test'/>">테스트 기반 추천</a></td>
		</tr></table>
		<br><br>
	
	
	
	<div class="d1">
		<!-- 일단 css 다 빼고 기능만 작성 -->
		
		<div id="recommend1">
			<h3>회원님을 위한 추천</h3>
			<table class="recTable2"><tr>
				<c:forEach var="drink" items="${userRecList}">
					<td class="recTd2">
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
			<h3>랭킹</h3>
			<table class="recTable2"><tr>
				<c:forEach var="rank" items="${overallRank}">
					<td class="recTd2">
						<img src='${rank.alcohol.imageUrl}'  width="auto" height="100px"> <br>
						${rank.alcohol.name} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		<br><br><br>
		
		<div id="recommend3">
			<h3>최근 인기 순위 추천</h3>
			<table class="recTable2"><tr>
				<c:forEach var="rank" items="${hotRank}">
					<td class="recTd2">
						<img src='${rank.alcohol.imageUrl}'  width="auto" height="100px"> <br>
						${rank.alcohol.name} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		
		<br><br><br>
		<!-- 타입별 랭킹.... -->
		<select id="selectBox" name="selectBox">
			<option value="soju" selected="selected">소주</option>
			<option value="beer">맥주</option>
		</select> <h3>주종별 랭킹</h3><br>
		
		<div id="soju" class="soju">
			<table class="recTable2"><tr>
				<c:forEach var="rank" items="${soju}">
					<td class="recTd2">
						<img src='${rank.alcohol.imageUrl}'  width="auto" height="100px"> <br>
						${rank.alcohol.name} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		
		<div id="beer" class="beer">
			<table class="recTable2"><tr>
				<c:forEach var="rank" items="${beer}">
					<td class="recTd2">
						<img src='${rank.alcohol.imageUrl}'  width="auto" height="100px"> <br>
						${rank.alcohol.name} <br>
						#${rank.alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps} <br>
					</td>
				</c:forEach>
			</tr></table>
		</div>
		
		
		
	</div>
	<br><br>
</body>
</html>