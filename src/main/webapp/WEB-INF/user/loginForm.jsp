<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function login() {
	if (form.userId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus();
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}		
	form.submit();
}
</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>
<div class="container">  
	<br>
	<br>
	<!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="col-lg-12">
		<c:if test="${loginFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	<!-- login form  -->
	<form class="col-md-6 col-lg-6" name="form" method="POST" action="<c:url value='/user/login' />">
		<div class="form-group">   
	        <label for="userId">사용자 ID</label>
	        <input type="text" name="userId" class="form-control" placeholder="사용자 ID"> 
	    </div>       
	    <div class="form-group">  
	        <label for="password">비밀번호</label>
	        <input type="password" name="password" class="form-control" placeholder="비밀번호"> 
	    </div> 
		<div class="form-group">        
			<input type="button" class="btn btn-outline-success" value="로그인" onClick="login()"> 
			<a href="<c:url value='/user/register' />" class="btn btn-link" style="color:#28a745;">회원 가입 </a>    		     
		</div>   	
	</form> 
</div>
</body>
</html>