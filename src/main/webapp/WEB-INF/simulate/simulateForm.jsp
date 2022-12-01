<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<script>

</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	
	
	<!-- 배너 -->
	<div class="d0">
		<div class="grad1">
			<br><br><br><br><br> S I M U L A T O R <br><br><br>
		</div>
	</div>
	
	
	<form method="post" action="">
		<c:if test=""> <!-- 로그인 되어 있지 않을 경우 -->
			주량 입력 &nbsp;&nbsp;&nbsp;
			<select>
				<option value="소주">소주</option>
				<option value="맥주">맥주</option>
			</select>&nbsp;&nbsp;
			
		</c:if>
	</form>
</body>
</html>