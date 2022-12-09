<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>나와 어울리는 술 테스트하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
var count = 1;
function createSelect(choice) {
	var form = document.getElementById("testForm");
	
	var hiddenField = document.createElement('input');
	hiddenField.setAttribute("name", count.toString());
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("value", choice);
	
	form.appendChild(hiddenField);
	
	if (count == 10) {
		form.submit();
	}
	
	var c = count.toString();
	var str = ".testDiv"+c;
	$(str).hide();
	
	count++;
	c = count.toString();
	var nextStr = ".testDiv"+c;
	$(nextStr).show();
	
}
</script>
</head>
<body>
	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<form method="post" id="testForm" action="<c:url value='/recommend/test' />">
		<div class="testDiv1" id="testDiv1">
			Q1. 11111111111111111111111111111<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv2" id="testDiv2">
			Q2. 22222222222222222222222222222<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv3" id="testDiv3">
			Q3. 33333333333333333333333333333<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv4" id="testDiv4">
			Q4. 44444444444444444444444444444<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv5" id="testDiv5">
			Q5. 5555555555555555555555555555<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv6" id="testDiv6">
			Q6. 6666666666666666666666666666<br>
			<div class="testDiv" onclick="createSelect('1');">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv7" id="testDiv7">
			Q7. 77777777777777777777777777<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv8" id="testDiv8">
			Q8. 8888888888888888888888888888<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv9" id="testDiv9">
			Q9. 9999999999999999999999999999<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
		
		<div class="testDiv10" id="testDiv10">
			Q10. 10101010101010101010101010<br>
			<div class="testDiv" onclick="createSelect('1')">
				선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1선택1
			</div>
			<div class="testDiv" onclick="createSelect('2')">
				선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2선택2
			</div>
		</div>
	</form>

</body>
</html>