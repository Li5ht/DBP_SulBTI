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
<style>
.modal {
	display: none; /* Hidden by default */
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

</style>
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
	var type = $("#sel1").val();
	var name = $("#sel2").val();
	var amount = $("#amount").val();
	var amountInt = parseInt(amount);
	if (!(type != null && name != null && amountInt > 0 && type != '0' && name != '0')) {
		alert("주랑을 입력하십시오.");
		form.sel1.focus();
		return false;
	}
	
	form.submit();
}

function userDelete() {
	document.getElementById("myModal").style.display="block";
}

//팝업 Close 기능
function close_pop(flag) {
     $('#myModal').hide();
};

</script>
</head>
<body>
<%@include file="/WEB-INF/navbar.jsp" %>

<div id="myModal" class="modal">
	<div class="modal-content">	<!-- 회원 탈퇴 확인 모달 -->
			<form method="POST" action="<c:url value='/user/delete'/>">
				정말 삭제하시겠습니까? <br><br>
				<input type="button" class="btn btn-outline-success" value="회원 탈퇴" onClick="location.href='<c:url value='/user/delete' />'">
				<input type="button" class="btn btn-outline-success" value="아니오" onClick="close_pop();">
			</form>
	</div>
</div>

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
	    <div class="form-group row">  
	    	<label for="email" class="col-lg-2 col-form-label">주량</label>
	    	<div class="col-lg-10">
	    		<select id="sel1" name="sel1" onchange="categorychange(this, 1)">
					<option value="0">주종 선택</option>
					<option value="소주">소주</option>
					<option value="맥주">맥주</option>
					<option value="전통주">전통주</option>
					<option value="와인">와인</option>
					<option value="양주">양주</option>
				</select>&nbsp;&nbsp;
			
				<select id="sel2" name="sel2">
					<option value="0">술 선택</option>
				</select>&nbsp;&nbsp;
			
				<input type="text" name="amount" width="30" id="amount">&nbsp;ml
	    	</div>
	    </div>
	    <%
			String sojuStr = "";
			String beerStr = "";
			String traditionalStr = "";
			String wineStr = "";
			String spiritsStr = "";
			
			String[] aSoju = (String[]) (request.getAttribute("aSoju"));
			String[] aBeer = (String[]) (request.getAttribute("aBeer"));
			String[] aTraditional = (String[]) (request.getAttribute("aTraditional"));
			String[] aWine = (String[]) (request.getAttribute("aWine"));
			String[] aSpirits = (String[]) (request.getAttribute("aSpirits"));
			
			for( int i = 0; i < aSoju.length; i++ ){
				if (i != (aSoju.length - 1)) {
					sojuStr = sojuStr + aSoju[i] + "/";
				} else {
					sojuStr = sojuStr + aSoju[i];
				}
			}
			
			for( int i = 0; i < aBeer.length; i++ ){
				if (i != (aBeer.length - 1)) {
					beerStr = beerStr + aBeer[i] + "/";
				} else {
					beerStr = beerStr + aBeer[i];
				}
			}
			
			for( int i = 0; i < aTraditional.length; i++ ){
				if (i != (aTraditional.length - 1)) {
					traditionalStr = traditionalStr + aTraditional[i] + "/";
				} else {
					traditionalStr = traditionalStr + aTraditional[i];
				}
			}
			
			for( int i = 0; i < aWine.length; i++ ){
				if (i != (aWine.length - 1)) {
					wineStr = wineStr + aWine[i] + "/";
				} else {
					wineStr = wineStr + aWine[i];
				}
			}
			
			for( int i = 0; i < aSpirits.length; i++ ){
				if (i != (aSpirits.length - 1)) {
					spiritsStr = spiritsStr + aSpirits[i] + "/";
				} else {
					spiritsStr = spiritsStr + aSpirits[i];
				}
			}
		%>
		
		
		<script>
    		function categorychange(e) {
    			var alcohol;
    			var aLength;
        		
        		
        		var i = "sel2";
        		var target = document.getElementById(i);
        		
        		if (e.value == "소주") { 
        			var sojuStr = '<%=sojuStr%>';
        			alcohol = sojuStr.split("/");
        			aLength = alcohol.length;
        		}
        		else if (e.value == "맥주") {
        			var beerStr = '<%=beerStr%>';
        			alcohol = beerStr.split("/");
        			aLength = alcohol.length;
        		}
        		else if (e.value == "전통주") {
        			var traditionalStr = '<%=traditionalStr%>';
        			alcohol = traditionalStr.split("/");
        			aLength = alcohol.length;
        		}
        		else if (e.value == "와인") {
        			var wineStr = '<%=wineStr%>';
        			alcohol = wineStr.split("/");
        			aLength = alcohol.length;
        		}
        		else if (e.value == "양주") {
        			var spiritsStr = '<%=spiritsStr%>';
        			alcohol = spiritsStr.split("/");
        			aLength = alcohol.length;
        		}
        		
        		target.options.length = 0;
        		
        		for (var i = 0; i < aLength; i++) {
        		    var opt = document.createElement("option");
        			opt.value = alcohol[i];
        			opt.innerHTML = alcohol[i];
        			target.appendChild(opt);
        		}
    		}
    	</script>
		<div class="form-group">       
			<input type="button" class="btn btn-outline-success" value="수정" onClick="userModify()">
			<input type="button" class="btn btn-outline-success" value="회원 탈퇴" onClick="userDelete()">
		</div>   
	</form>
</div>
</body>
</html>