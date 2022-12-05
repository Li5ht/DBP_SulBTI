<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/diary.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<title>술 정보</title>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
$(document).ready(function($) {
    $(".scroll_move").click(function(event){
        console.log(".scroll_move");
        event.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top}, 500);
    });
)};

</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="grad1">
		<br><br><br><br><br>A L L &nbsp; P R O D U C T <br><br><br>
	</div>
	
	
	
	<!-- review  form  -->
	<form name="search" align="right" style="margin-right:70px;" method = "get"  action ="review.jsp" onsubmit="return keyword_check()">
		<!-- 리뷰 리스트, 작성, 삭제 서블릿 호출 -->
		<br><br>
		<div class="container">
			<div class="row">
				<form method="post" name="search" action="review.jsp">
					<table align="right" class="pull-left">
						<tr>
							<td><input type="text" class="form-control"
								placeholder="검색어 입력" name="searchText" maxlength="100"></td>
							<td><button type="submit" class="btn btn-success">검색</button></td>
						</tr>
	
					</table>
				</form>
			</div>
		</div>
		<table class="banner">
			<tr class="tr1">
				<td class="td1"><hr width="80%" ></td>
				<td> P R O D U C T </td>
				<td class="td1"><hr width="80%" ></td>
			</tr>
		</table>
		
		<table class="recTable">
			<tr>
				<td class="recTd"><a href="#alcoho1" class="scroll_move">소주</a></td>
				<td class="recTd"><a href="#alcoho2" class="scroll_move">맥주</a></td>
				<td class="recTd"><a href="#alcoho3" class="scroll_move">전통주</a></td>
				<td class="recTd"><a href="#alcoho4" class="scroll_move">와인</a></td>
				<td class="recTd"><a href="#alcoho5" class="scroll_move">양주</a></td>
				<td class="recTd"><a href="#alcoho6" class="scroll_move">칵테일</a></td>
			</tr>
		</table>
		
		<div class="d1">
		<!-- 일단 css 다 빼고 기능만 작성 -->
		
		<c:if test="${hasLogin}">
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
		</c:if>
		
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
</body>
</html>