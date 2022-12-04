<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script>
function fillBlank() {
	$("dc").val("소주").prop("selected", true);
	$("alcoholByType").val("참이슬 오리지널").prop("selected", true);
})
</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="d0">
		<div class="grad1">
			<br><br><br><br><br> S I M U L A T O R <br><br><br>
		</div>
	</div>
	<br><br>
	
	<form class="f1" method="post" action="<c:url value='/simulate/result' />">
		
		주량 입력 &nbsp;&nbsp;&nbsp;
		
		<select id="s1" onchange="optionChange();">
			<option></option>
			<option value="소주">소주</option>
			<option value="맥주">맥주</option>
			<option value="와인">와인</option>
		</select>&nbsp;&nbsp;
		
		<select id="s2">
			<option></option>
		</select>
		
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    	<script>
    		var soju = ['참이슬 오리지널', '참이슬 후레쉬', '진로'];
    		var beer = ['카스', '테라'];
    		var wine = ['와인1', '와인2'];
    		
    		var type = $('#s1').val();
    		var o;
    		if (v == '소주') {
    			o = soju;
    		} else if (v == '맥주') {
    			o = beer;
    		} else if (v == '와인') {
    			o = wine;
    		} else {
    			o = [];
    		}
    		
    		$('#s2').empty();
    		$('#s2').append('<option></option>');
    		for (var i = 0; i < o.length; i++) {
    			$('#s2').append('<option>' + o[i] + '</option>');
    		}
    
    	</script>
		
		<input type="text" width="30">
		<br><br>
		
		마실 양 &nbsp;&nbsp;&nbsp;
		<select>
			<option value="소주">소주</option>
			<option value="맥주">맥주</option>
			<option value="와인">와인</option>
		</select>&nbsp;&nbsp;
		<select>
			<option>술 선택</option>
		</select>
		<input type="text" width="30">
		
		
			
		<c:if test="${drinkingCapacity} ne -1"> 
		<!-- 주량이 null이 아닌 경우 -->
			<script>
				fillBlank();
			</script>
		</c:if>

	</form>
</body>
</html>