<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>술 정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
	.modal {
	display: block; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto; /* Enable scroll if needed */
	background-color: rgba(0,0,0,0.3); /* 검정색 투명도 조절 */
}
    
/* 모달 안에 들어갈 부분 css */
.modal-content {
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid;
	vertical-align: middle;
	width: 500px;
}

.modal_close_btn {
	position: absolute;
	top: 10px;
	right: 10px;
}
</style>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function selectDiv(str) {
	if (str == '맥주') {
		$('.alcohol1').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol2').show();
	} else if (str == '전통주') {
		$('.alcohol2').hide();
		$('.alcohol1').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol3').show();
	} else if (str == '와인') {
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol1').hide();
		$('.alcohol5').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol4').show();
	} else if (str == '양주') {
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol1').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol5').show();
	} else if (str == '칵테일') {
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		$('.alcohol1').hide();
    	
      	$('.alcohol6').show();
	} else {
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol1').show();
	}
}
</script>
</head>

<body onload="selectDiv('${alcohol.type}');">  
<c:if test="${detail eq 1}">
<div id="myModal" class="modal">
	<div class="modal-content">	<!-- 모달 안에 들어갈 부분 -->
			상세정보 <br>
			<table>
				<tr>
					<td>
						<img src='${alcohol.imageUrl}' width="auto" height="150px">
					</td>
					<td>
						${alcohol.name}<br>
						${alcohol.type} &nbsp; 도수${alcohol.alcoholLevel}%<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}<br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose><br>
					</td>
				</tr>
			</table>
			<br>
			<a href="#">리뷰 등록</a>
			<hr width="100%">
			<c:forEach var="review" items="${reviewList}">
				${review.content}<br>
				<c:choose>
							<c:when test="${review.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />" width="70px" height="auto">
							</c:when>
						</c:choose>
				#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}<br>
				<hr width="100%">
			</c:forEach>
		
		<div class="modal_close_btn" onclick="close_pop();">
			닫기
		</div>
	</div>
</div>
</c:if>
	
	<script type="text/javascript">
      
        function openAlcoholInfo(alcoholId) {
        	var form = document.createElement('form');
        	form.setAttribute("action", "<c:url value='/product/info'/>");
        	
        	var hiddenField = document.createElement('input');
			hiddenField.setAttribute("name", 'aId');
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("value", alcoholId);
			form.appendChild(hiddenField);
		    document.body.appendChild(form);
			form.submit();
			
        }
        //팝업 Close 기능
        function close_pop(flag) {
             $('#myModal').hide();
        };
        
    </script>

	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
		<div class="grad1">
			<br><br><br><br><br> A L L &nbsp; P R O D U C T <br><br><br>
		</div>
		
		
		<br>
		<table class="banner"><tr class="tr1">
			<td class="td1"><hr width="80%" ></td>
			<td> P R O D U C T </td>
			<td class="td1"><hr width="80%" ></td>
		</tr></table>
		
		<br>
		<table class="recTable">
			<tr>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('소주');">소주</a></td>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('맥주');">맥주</a></td>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('전통주');">전통주</a></td>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('와인');">와인</a></td>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('양주');">양주</a></td>
				<td class="recTd"><a href='javascript:void(0);' onclick="selectDiv('칵테일');">칵테일</a></td>
			</tr>
		</table><br><br>
		
		<div class="alD">
			<div id="alcohol1" class="alcohol1">	<!-- 소주 -->
				<c:forEach var="alcohol" items="${soju}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}' width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>	
				</c:forEach>
			</div>
			
			<div id="alcohol2" class="alcohol2">	<!-- 맥주 -->
				<c:forEach var="alcohol" items="${beer}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol3" class="alcohol3">	<!-- 전통주 -->
				<c:forEach var="alcohol" items="${traditional}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol4" class="alcohol4">	<!-- 와인 -->
				<c:forEach var="alcohol" items="${wine}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol5" class="alcohol5">	<!-- 양주 -->
				<c:forEach var="alcohol" items="${spirits}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol6" class="alcohol6">	<!-- 칵테일 -->
				<c:forEach var="alcohol" items="${cocktail}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
		</div>
		
	<br><br>
	<div id="alsldlfl">
	
	</div>
	
</body>
</html>