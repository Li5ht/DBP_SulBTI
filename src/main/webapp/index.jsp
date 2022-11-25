<!-- response.sendRedirect(request.getContextPath() + "/recommend/list"); -->
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>홈 화면</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<script>


</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	
	
	
	<!-- 배너 -->
	<div class="grad0">	<!-- 클릭 시 이동하게 변경 -->
		<br><br>주량이 소주 4병인 내가 와인 3병을 먹는다면?<br><br><br>
	</div>



	<!-- 여긴 그냥 내가 이동하기 쉬우려고..^^ 일단.. -->
	<br><form name="form" method="POST" action="<c:url value='/recommend/list' />">
		<input type="submit" value="술 추천" >
	</form>
	
	
</body>
</html>