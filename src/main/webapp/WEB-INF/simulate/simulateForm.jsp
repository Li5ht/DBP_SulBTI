<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script>
function categoryChange(e) {
    var soju = ["참이슬 오리지널", "참이슬 후레쉬", "진로", "처음처럼"];
    var beer = ["카스", "테라"];
    var wine = ["와인1", "와인2"];
    var target = document.getElementById("alcoholByType");
 
    if(e.value == "소주") var d = soju;
    else if(e.value == "맥주") var d = beer;
    else if(e.value == "와인") var d = wine;
 
    target.options.length = 0;
 
    for (x in d) {
        var opt = document.createElement("option");
        opt.value = d[x];
        opt.innerHTML = d[x];
        target.appendChild(opt);
    }    
}

function fillBlank() {
	$("dc").val("소주").prop("selected", true);
	$("alcoholByType").val("참이슬 오리지널").prop("selected", true);
})
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
	
	
	<form method="post" action="<c:url value='/simulate/result' />">
		주량 입력 &nbsp;&nbsp;&nbsp;
		<select id="dc" onchange="categoryChange(this)">
			<option value="소주">소주</option>
			<option value="맥주">맥주</option>
			<option value="와인">와인</option>
		</select>&nbsp;&nbsp;
		<select id="alcoholByType">
			<option>술 선택</option>
		</select>
		<input type="text" width="30">
		<br><br>
		
		마실 양 &nbsp;&nbsp;&nbsp;
		<select onchange="categoryChange(this)">
			<option value="소주">소주</option>
			<option value="맥주">맥주</option>
			<option value="와인">와인</option>
		</select>&nbsp;&nbsp;
		<select id="alcoholByType">
			<option>술 선택</option>
		</select>
		<input type="text" width="30">
		
		
			
		<c:if test="${drinkingCapacity} ne -1"> 
		<!-- 주량이 null이 아닌 경우 -->
			<script>
				fillBlank();
			</script>
		</c:if>
		
	</form>
</body>
</html>