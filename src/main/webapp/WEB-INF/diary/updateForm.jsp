<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/diary.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<title>음주 기록 수정</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="grad1">
		<br><br><br><br><br> D I A R Y <br><br><br>
	</div>
	
	<!-- 회원가입이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
	<div class="row col-lg-12">
		<c:if test="${creationFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	
	<!-- registration form  -->
	<form name="form" method="POST" action="<c:url value='/diary/create' />">
    	<div class="form-group row">   
	        <label for="date" class="col-lg-2 col-form-label">날짜</label>
	        <div class="col-lg-10">
	            <input type="text" name="date" class="form-control" value="${diary.drinkingDate}"
	            	<c:if test="${creationFailed}">value="${diary.drinkingDate}"</c:if>>	            	
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="" class="col-lg-2 col-form-label">마신 술 목록</label>
	        <div class="col-lg-10">
	        	<select name="alcohol" form="myForm">
		        	<c:forEach var="alcohol" items="${alcoholList}">
		        		<option value=${alcohol.name}>
		        		<c:if test="${alcohol.name eq diary.drinkingList[0].name}">selected="selected"</c:if>
							>${alcohol.name}</option>
		        	</c:forEach>
		        </select>
	            <input type="number" name="amount" class="form-control" value="${diary.drinkingList[0].amount}"
					<c:if test="${creationFailed}">value="${diary.drinkingList[0].amount}"</c:if>>
	        </div>
	    </div>    
	    <div class="form-group row">   
	        <label for="condition" class="col-lg-2 col-form-label">상태</label>
	        <div class="col-lg-10">
	            <input type="number" name="condition" class="form-control" placeholder="컨디션" 
					<c:if test="${creationFailed}">value="${diary.condition}"</c:if>>
	        </div>
	    </div>   
	    <div class="form-group row">   
	        <label for="content" class="col-lg-2 col-form-label">오늘의 일기</label>
	        <div class="col-lg-10">
	            <input type="text" name="content" class="form-control" placeholder="오늘의 일기" 
					<c:if test="${creationFailed}">value="${diary.content}"</c:if>>
	        </div>
	    </div>      
	    <br>
	    <div class="form-group">        
			<input type="button" class="btn btn-success" value="생성" onClick="diaryCreate()"> 
			<a href="<c:url value='/diary/list' />" class="btn btn-outline-success">음주 기록 목록</a>    		     
		</div>   
	</form>
</body>
</html>