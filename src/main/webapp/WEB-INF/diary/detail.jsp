<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel=stylesheet href="<c:url value='/css/diary.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function diaryDelete() {
	// 삭제 알림
	alert("삭제되었습니다.");
}

</script>
<title>음주 기록 추가</title>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
	<div class="grad1">
		<br><br><br><br><br> D I A R Y <br><br><br>
	</div>
	
	<div class="row col-lg-12">
		<c:if test="${creationFailed}">
			<h6 class="text-danger"><c:out value="${exception.getMessage()}"/></h6>
		</c:if>
	</div>	  
	
	<!-- detail form  -->
	<form name="form">
    	<div class="form-group row">   
	        <label for="date" class="col-lg-2 col-form-label">날짜</label>
	        <div class="col-lg-10">
	            <input type="text" name="date" class="form-control" value="${diary.drinkingDate}"
	            	<c:if test="${creationFailed}">value="${diary.drinkingDate}"</c:if>>	            	
	        </div>
	    </div>       
	    <div class="form-group row">   
	        <label for="alcohol" class="col-lg-2 col-form-label">마신 술 목록</label>
	        <div class="col-lg-10">
	        	<input type="text" name="alcoholType" class="form-control" value="${diary.drinkingList[0].alcohol.type}"
	            	<c:if test="${creationFailed}">value="${diary.drinkingDate}"</c:if>>
	            <input type="text" name="date" class="form-control" value="${diary.drinkingList[0].alcohol.name}"
	            	<c:if test="${creationFailed}">value="${diary.drinkingDate}"</c:if>>
	            <input type="number" name="amount" class="form-control" value="${diary.drinkingList[0].amount}"
					<c:if test="${creationFailed}">value="${diary.drinkingList[0].amount}"</c:if>>
	        </div>
	    </div>    
	    <div class="form-group row">   
	        <label for="condition" class="col-lg-2 col-form-label">상태</label>
	        <div class="col-lg-10">
	            <input type="number" name="condition" class="form-control" value="${diary.condition}" 
					<c:if test="${creationFailed}">value="${diary.condition}"</c:if>>
	        </div>
	    </div>   
	    <div class="form-group row">   
	        <label for="content" class="col-lg-2 col-form-label">오늘의 일기</label>
	        <div class="col-lg-10">
	            <input type="text" name="content" class="form-control" value="${diary.content}"
					<c:if test="${creationFailed}">value="${diary.content}"</c:if>>
	        </div>
	    </div>      
	    <br>
	    <div class="form-group">      
	    	<a class="btn btn-outline-success"
	    	href="<c:url value='/diary/update'>
	    			<c:param name='diaryId' value='${diary.diaryId}' />
	    		</c:url>">수정하기</a>   	
	    	<a class="btn btn-outline-success"  onClick="diaryDelete()"
	    	href="<c:url value='/diary/delete'> 
	    			<c:param name='diaryId' value='${diary.diaryId}' />
	    		</c:url>">삭제하기</a>   
			<a href="<c:url value='/diary/list' />" class="btn btn-outline-success">음주 기록 목록</a>    		     
		</div>   
	</form>
</body>
</html>