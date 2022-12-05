<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>시뮬레이터</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
var count = 0;

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
	
	<div class="simulDiv">
	
	<form class="f1" id="f1" method="post" action="<c:url value='/simulate/result' />">
		
		<div class="simulDiv2" id="simulDiv2">
		
		
		<!-- 주량 -->
		주량 입력 &nbsp;&nbsp;&nbsp;
		<c:if test="${drinkingCapacity gt 0}"> <!-- 로그인 되어 있을 경우 -->
			<select id="sel1_1" name="sel1_1" onchange="categorychange(this, 1)">
				<option value="0">주종 선택</option>
				<option value="소주" selected="selected">소주</option>
				<option value="맥주">맥주</option>
				<option value="와인">와인</option>
			</select>&nbsp;&nbsp;
			
			<select id="sel2_1" name="sel2_1">
				<option value="0">술 선택</option>
				<c:forEach var="alcohol" items="${aSoju}">
					<c:if test="${alcohol.name eq '참이슬 오리지널'}">
						<option value="${alcohol.name}" selected="selected">${alcohol.name}</option>
					</c:if>
					<c:if test="${alcohol.name ne '참이슬 오리지널'}">
						<option value="${alcohol.name}">${alcohol.name}</option>
					</c:if>
				</c:forEach>
			</select>&nbsp;&nbsp;
			
			<input type="text" name="amount1" width="30" id="amount1" value="${drinkingCapacity}">
		</c:if>
		
		<c:if test="${drinkingCapacity le 0}"> <!-- 로그인 되어 있지 않을 경우 -->
			<select id="sel1_1" name="sel1_1" onchange="categorychange(this, 1)">
				<option value="0">주종 선택</option>
				<option value="소주">소주</option>
				<option value="맥주">맥주</option>
				<option value="와인">와인</option>
			</select>&nbsp;&nbsp;
			
			<select id="sel2_1" name="sel2_1">
				<option value="0">술 선택</option>
			</select>&nbsp;&nbsp;
			
			<input type="text" name="amount1" width="30" id="amount1">
		</c:if>
		<br><br>
		
		
		<!-- 마실 양 -->
		마실 양 &nbsp;&nbsp;&nbsp;
		<select id="sel1_2" onchange="categorychange(this, 2)">
			<option>주종 선택</option>
			<option value="소주">소주</option>
			<option value="맥주">맥주</option>
			<option value="와인">와인</option>
		</select>&nbsp;&nbsp;
		<select id="sel2_2">
			<option>술 선택</option>
		</select>
		<input type="text" width="30" id="amount2"> 
		<a href="#" onclick="plus()"> + </a>
		<br><br><br><br><br><br>
		
		<script>
    		function categorychange(e, num) {
    			var soju = ['참이슬 오리지널', '참이슬 후레쉬', '진로'];
        		var beer = ['카스', '테라'];
        		var wine = ['와인1', '와인2'];
        		var i = "sel2_" + num.toString();
        		var target = document.getElementById(i);
        		
        		if (e.value == "소주") var d = soju;
        		else if (e.value == "맥주") var d = beer;
        		else if (e.value == "와인") var d = wine;
        		
        		target.options.length = 0;
        		
        		for (x in d) {
        			var opt = document.createElement("option");
        			opt.value = d[x];
        			opt.innerHTML = d[x];
        			target.appendChild(opt);
        		}
    		}
    		
    		function plus() {
    			// 아직 안 됨
    			count++;
    			
    			var form = document.getElementById("f1");
    			
    			var type = $("#sel1_2").val();
    			var name = $("#sel2_2").val();
    			var amount = $("#amount2").val();
    			
    			var str = "drink" + count.toString();
    			var drinkStr = type + "/" + name + "/" + amount;
    			
    			var hiddenField = document.createElement('input');
    			hiddenField.setAttribute("name", str);
    			hiddenField.setAttribute("type", "hidden");
    			hiddenField.setAttribute("value", drinkStr);
    			form.appendChild(hiddenField);
    			
    			$("#sel1_2").val("0").prop("selected", true);
    			$("#sel2_2").val("0").prop("selected", true);
    			$("#amount2").val("");
    			
    			
    			var target = document.getElementById("simulDiv2");
    			
    			var c = count.toString();
    			var textBox = c + ". " + type + " " + name + " " + amount + "ml";
    			var newDiv = document.createElement('div');
    			var newText = document.createTextNode(textBox);
    			newDiv.className = "simulDiv4";
    			newDiv.appendChild(newText);
    			
    			target.appendChild(newDiv);
    		}
    		
    		function simulSubmit() {
    			var form = document.getElementById("f1");
    			
    			var hiddenField = document.createElement('input');
    			hiddenField.setAttribute("name", "count");
    			hiddenField.setAttribute("type", "hidden");
    			hiddenField.setAttribute("value", count);
    			form.appendChild(hiddenField);
    			
    			form.submit();
    		}
    	</script>
		</div>
		
		
		
		<div class="simulDiv3">
			<br><br><br>
			<input type="button" value="시뮬레이션" onclick="simulSubmit()">
		</div>
		
		
	</form>
	
	</div>
</body>
</html>