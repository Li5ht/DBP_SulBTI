<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>테스트 결과</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

</script>
</head>
<body>
<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>

<!-- 답 전달 확인용 (추후엔 이미지만 띄우는 식으로~) -->
<c:forEach var="choice" items="${choice }">
	${choice} <br>
</c:forEach>

${testType }

</body>
</html>