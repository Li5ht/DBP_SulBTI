<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>술 정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
function selectDiv(str) {
	if (str == '소주') {
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		$('.alcohol6').hide();
    	
      	$('.alcohol1').show();
	} else if (str == '맥주') {
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
	}
}

</script>
</head>
<body>
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
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>	
				</c:forEach>
			</div>
			
			<div id="alcohol2" class="alcohol2">	<!-- 맥주 -->
				<c:forEach var="alcohol" items="${beer}">
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol3" class="alcohol3">	<!-- 전통주 -->
				<c:forEach var="alcohol" items="${traditional}">
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol4" class="alcohol4">	<!-- 와인 -->
				<c:forEach var="alcohol" items="${wine}">
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol5" class="alcohol5">	<!-- 양주 -->
				<c:forEach var="alcohol" items="${spirits}">
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol6" class="alcohol6">	<!-- 칵테일 -->
				<c:forEach var="alcohol" items="${cocktail}">
					<div class="alDiv">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						${alcohol.rate} <br>
						#${alcohol.taste} #${rank.alcohol.flavor} #${rank.alcohol.corps}
					</div>
				</c:forEach>
			</div>
		</div>
		
	<br><br>
</body>
</html>