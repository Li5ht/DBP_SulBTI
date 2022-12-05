<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>사용자 관리 - 회원 정보 수정</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function userModify() {
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	if (form.password.value != form.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		form.name.focus();
		return false;
	}
	if (form.name.value == "") {
		alert("이름을 입력하십시오.");
		form.name.focus();
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
	<h4>회원 정보 수정</h4>
	<br>
	<!-- Update Form  -->
	<form name="form" method="POST" action="<c:url value='/user/update' />">
		<input type="hidden" name="userId" value="${user.userId}"/>	  
		<div class="form-group row">   
	        <label class="col-lg-2 col-form-label">사용자 ID</label>
	        <div class="col-lg-10">
	        	<p class="form-control-static">${user.userId}</p> 
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="password" class="col-lg-2 col-form-label">비밀번호</label>
	        <div class="col-lg-10">
	            <input type="password" name="password" class="form-control" value="${user.password}"> 
	        </div>
	    </div>       
	    <div class="form-group row">  
	        <label for="password2" class="col-lg-2 col-form-label">비밀번호 확인</label>
	        <div class="col-lg-10">
	        	<input type="password" name="password2" class="form-control" value="${user.password}">
	        </div> 
	    </div> 
		<div class="form-group row">   
	        <label for="nickname" class="col-lg-2 col-form-label">닉네임</label>
	        <div class="col-lg-10">
	        	<input type="text" name="nickname" class="form-control" value="${user.nickname}">
	        </div>
	    </div>       
	    <div class="form-group row">  
	        <label for="email" class="col-lg-2 col-form-label">이메일 주소</label>
	        <div class="col-lg-10">
	        	<input type="text" name="email" class="form-control" value="${user.email}">
	        </div>
	    </div> 
	   	<br>
		<div class="form-group row">   
	        <label for="name" class="col-lg-2 col-form-label">생일</label>
	        <div class="col-lg-10">
	        		<select name="birth1">
	        			<% java.util.Calendar cal = java.util.Calendar.getInstance(); %>

				    	<%for(int i = cal.get(java.util.Calendar.YEAR) - 19; i>=1900; i--){ %>
				    	<option value="<%=i %>"><%=i %></option>
				    	<%} %>
				    </select>년&nbsp;
				    <select name="birth2">
				       <%for(int i=1; i<=12; i++){ %>
				       <option value="<%=i %>"><%=i %></option>
				       <%} %>
				    </select>
				    <select name="birth3">
				       <%for(int i=1; i<=31; i++){ %>
				       <option value="<%=i %>"><%=i %></option>
				       <%} %>
				    </select>일<br><br>
				    
	        </div>
	    </div>       
	    <div class="form-group row">  
	        <label for="email" class="col-lg-2 col-form-label">성별</label>
	        <div class="col-lg-10">
				<input type="radio" name="gender" value="0"> 남자&nbsp;&nbsp;
   				<input type="radio" name="gender" value="1" checked="checked"> 여자<br><br>
	        </div>
	    </div> 
		<div class="form-group">       
			<input type="button" class="btn btn-outline-success" value="수정" onClick="userModify()">
		</div>   
	</form>
</div>
</body>
</html>