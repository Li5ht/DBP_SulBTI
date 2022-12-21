<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>술 정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%
	
%>
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
	.modal {
	display: block; /* Hidden by default */
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

.modal_close_btn {
	position: absolute;
	top: 10px;
	right: 10px;
}

.modal-content2 {
	margin: 15% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid;
	vertical-align: middle;
	width: 500px;
}
</style>
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
var search = '<c:out value="${search}"/>';
function selectDiv(str) {
	if (search == 0) {
		$('.alcohol1').hide();
		$('.alcohol2').hide();
		$('.alcohol3').hide();
		$('.alcohol4').hide();
		$('.alcohol5').hide();
		
		$('.searchDiv').show();
		
		search = search + 1;
	} else {
		$('.searchDiv').hide();
		if (str == '맥주') {
			$('.alcohol1').hide();
			$('.alcohol3').hide();
			$('.alcohol4').hide();
			$('.alcohol5').hide();
	    	
	      	$('.alcohol2').show();
		} else if (str == '전통주') {
			$('.alcohol2').hide();
			$('.alcohol1').hide();
			$('.alcohol4').hide();
			$('.alcohol5').hide();
	    	
	      	$('.alcohol3').show();
		} else if (str == '와인') {
			$('.alcohol2').hide();
			$('.alcohol3').hide();
			$('.alcohol1').hide();
			$('.alcohol5').hide();
	    	
	      	$('.alcohol4').show();
		} else if (str == '양주') {
			$('.alcohol2').hide();
			$('.alcohol3').hide();
			$('.alcohol4').hide();
			$('.alcohol1').hide();
	    	
	      	$('.alcohol5').show();
		} else {
			$('.alcohol2').hide();
			$('.alcohol3').hide();
			$('.alcohol4').hide();
			$('.alcohol5').hide();
	    	
	      	$('.alcohol1').show();
		}
	}
	
	var review = '<c:out value="${detail}"/>';
	if (review == 2) {
		alert('이미 리뷰가 존재합니다.');
	} else if (review == 3) {
		alert('리뷰가 존재하지 않습니다. 먼저 리뷰를 작성해주세요.');
	}
	
	var register = '<c:out value="${registerReview}"/>';
	if (register == 1) {
		alert('리뷰가 작성되었습니다.');
	}

	var updateReview = '<c:out value="${updateReview}"/>';
	
	var rate = '<c:out value="${userRate}"/>';
	var taste = '<c:out value="${userTaste}"/>';
	var flavor = '<c:out value="${userFlavor}"/>';
	var corps = '<c:out value="${userCorps}"/>';
	if (updateReview == 1) {
		$("#rate").val(rate).prop("selected", true);
		$("#taste").val(taste).prop("selected", true);
		$("#flavor").val(flavor).prop("selected", true);
		$("#corps").val(corps).prop("selected", true);
	} else if (updateReview == 2) {
		alert('수정이 완료되었습니다.');
	}
	
	var deleteReview = '<c:out value="${deleteReview}"/>';
	if (deleteReview == 1) {
		alert('리뷰가 삭제되었습니다.');
	}
	
	
	
}

$(document).ready(function() {
    $('#content').on('keyup', function() {
        $('#test_cnt').html("("+$(this).val().length+" / 100)");
 
        if($(this).val().length > 100) {
            $(this).val($(this).val().substring(0, 100));
            $('#test_cnt').html("(100 / 100)");
        }
    });
});


</script>
</head>

<body onload="selectDiv('${alcohol.type}');">  

<c:if test="${detail eq 1 || detail eq 2 || detail eq 3}">
<div id="myModal" class="modal">
	<div class="modal-content">	<!-- 상세정보 -->
			상세정보 <br>
			<table>
				<tr>
					<td>
						<img src='${alcohol.imageUrl}' width="auto" height="150px">
					</td>
					<td>
						${alcohol.name}<br>
						${alcohol.type} &nbsp; 도수${alcohol.alcoholLevel}%<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}<br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose><br>
					</td>
				</tr>
			</table>
			<br>
			<a href='javascript:void(0);' onclick="createReview('${alcohol.alcoholId}');">리뷰 등록</a>
			
			<hr width="100%">
			<c:forEach var="review" items="${reviewList}">
				${review.content}<br>
				<c:choose>
							<c:when test="${review.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />" width="70px" height="auto">
							</c:when>
							<c:when test="${review.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />" width="70px" height="auto">
							</c:when>
						</c:choose>
				#${review.tasteString} #${review.flavorString} #${review.corpsString}
				<c:if test="${review.member.id eq id}">
					<a href='javascript:void(0);' onclick="updateReview('${alcohol.alcoholId}');">리뷰 수정</a>
				</c:if>
				<hr width="100%">
			</c:forEach>
		
		<div class="modal_close_btn" onclick="close_pop();">
			닫기
		</div>
	</div>
</div>
</c:if>

<c:if test="${createReview eq 1 || updateReview eq 1}">
<div id="myModal" class="modal">
	<div class="modal-content">	<!-- 리뷰 등록 모달 -->
			<form method="POST" id="reviewForm" action="<c:url value='/review/create'/>">
				<input type="hidden" name="alcoholId" id="alcoholId" value="${alcohol.alcoholId }">
				${alcohol.type }<br>
				<input type="text" name="alcoholName" id="alcoholName" value="${alcohol.name }" readonly><br>
				별점 : <select name="rate" id="rate">	<!-- 추후에 드래그오버 식으로 변경해봄.. 시간이 되면 -->
					<option value="0.5">0.5</option>
					<option value="1">1</option>
					<option value="1.5">1.5</option>
					<option value="2">2</option>
					<option value="2.5">2.5</option>
					<option value="3">3</option>
					<option value="3.5">3.5</option>
					<option value="4">4</option>
					<option value="4.5">4.5</option>
					<option value="5">5</option>
				</select><br>
				taste : <select name="taste" id="taste">
					<c:forEach var="taste" items="${tasteHashTag}">
						<option value="${taste}">${taste}</option>
        			</c:forEach>
				</select>
				flavor : <select name="flavor" id="flavor">
					<c:forEach var="flavor" items="${flavorHashTag}">
						<option value="${flavor}">${flavor}</option>
        			</c:forEach>
				</select>
				corps : <select name="corps" id="corps">
					<c:forEach var="corps" items="${corpsHashTag}">
						<option value="${corps}">${corps}</option>
        			</c:forEach>
				</select><br>
				
				
				리뷰 작성 <br>
				
				<c:if test="${updateReview eq 1}">	<!-- 리뷰 수정 -->
					<textarea id="content" name="content" cols="40" rows="10" >${userReview.content}</textarea>
					<div id="test_cnt">(0 / 100)</div><br>
					<input type="button" value="리뷰 수정" onclick="reviewUpdate()">
					<input type="button" onclick="deleteReview(${alcohol.alcoholId})" value="리뷰 삭제">
				</c:if>
				<c:if test="${createReview eq 1}"> 	<!-- 리뷰 작성 -->
					<textarea id="content" name="content" cols="40" rows="10"></textarea>
					<div id="test_cnt">(0 / 100)</div><br>
				
					<input type="button" value="리뷰 등록" onclick="reviewCreate()">
				</c:if>
				
			</form>
			
			<div class="modal_close_btn" onclick="close_pop();">
				닫기
			</div>

	</div>
</div>
</c:if>


	
	<script type="text/javascript">
      
        function openAlcoholInfo(alcoholId) {
        	var form = document.createElement('form');
        	form.setAttribute("action", "<c:url value='/product/info'/>");
        	
        	var hiddenField = document.createElement('input');
			hiddenField.setAttribute("name", 'aId');
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("value", alcoholId);
			
			var hiddenField2 = document.createElement('input');
			hiddenField2.setAttribute("name", 'detail');
			hiddenField2.setAttribute("type", "hidden");
			hiddenField2.setAttribute("value", "detail");
			
			form.appendChild(hiddenField);
			form.appendChild(hiddenField2);
		    document.body.appendChild(form);
			form.submit();
			
        }
        function createReview(alcoholId) {
        	var form = document.createElement('form');
        	form.setAttribute("action", "<c:url value='/product/info'/>");
        	
        	var hiddenField = document.createElement('input');
			hiddenField.setAttribute("name", 'aId');
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("value", alcoholId);
			
			var hiddenField2 = document.createElement('input');
			hiddenField2.setAttribute("name", 'createReview');
			hiddenField2.setAttribute("type", "hidden");
			hiddenField2.setAttribute("value", "createReview");
			
			form.appendChild(hiddenField);
			form.appendChild(hiddenField2);
		    document.body.appendChild(form);
			form.submit();
			
        }

		function updateReview(alcoholId) {
        	var form = document.createElement('form');
        	form.setAttribute("action", "<c:url value='/product/info'/>");
        	
        	var hiddenField = document.createElement('input');
			hiddenField.setAttribute("name", 'aId');
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("value", alcoholId);
			
			var hiddenField2 = document.createElement('input');
			hiddenField2.setAttribute("name", 'updateReview');
			hiddenField2.setAttribute("type", "hidden");
			hiddenField2.setAttribute("value", "updateReview");
			
			form.appendChild(hiddenField);
			form.appendChild(hiddenField2);
		    document.body.appendChild(form);
			form.submit();
			
        }
		
		function deleteReview(alcoholId) {
			var form = document.getElementById("reviewForm");
			form.action = "<c:url value='/review/delete'/>";
			
			var hiddenField = document.createElement('input');
			hiddenField.setAttribute("name", 'aId');
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("value", alcoholId);
			
			form.appendChild(hiddenField);
			
			form.submit();
		}
		
		function reviewUpdate() {
			var form = document.getElementById("reviewForm");
			form.action = "<c:url value='/review/update'/>";
			
			if($("#content").val().trim().length < 1) {
    			alert("내용을 입력해주세요.");
    			return; 
			}
			
			form.submit();
		}
		
		function reviewCreate() {
			var form = document.getElementById("reviewForm");
			if($("#content").val().trim().length < 1) {
    			alert("내용을 입력해주세요.");
    			return; 
			}
			form.submit();
		}
		
		function searchAlcohol() {
			var form = document.getElementById("searchAlcohol");
			if($("#searchWord").val().trim().length < 1) {
    			alert("검색어를 입력해주세요.");
    			return; 
			}
			form.submit();
		}
		
        //팝업 Close 기능
        function close_pop(flag) {
             $('#myModal').hide();
        };
        
    </script>

	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
	
	<!-- 배너 -->
		<div class="grad1">
			<br><br><br><br><br> A L L &nbsp; P R O D U C T <br><br><br>
		</div>
		
		
		
		<br style="line-height:5px;">
		<form id="searchAlcohol" method="post" action="<c:url value='/product/search'/>" style="position: relative; text-align:right;">
			<p style="text-indent:500px"></p>
			<input type="text" width="15" name="searchWord" id="searchWord" placeholder="검색어를 입력해주세요.">
			<a href='javascript:void(0);' onclick="searchAlcohol();"><img src="<c:url value='/images/q.png' />" alt="search"/></a>
		</form>
		
		<table class="banner"><tr class="tr1">
			<td class="td1"><hr width="80%" ></td>
			<td> P R O D U C T </td>
			<td class="td1"><hr width="80%" ></td>
		</tr></table>
		
		<br>
		<div class="tableborder">
			<table class="recTable">
				<tr>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('소주');">소주</a></td>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('맥주');">맥주</a></td>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('전통주');">전통주</a></td>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('와인');">와인</a></td>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('양주');">양주</a></td>
					<td class="recTd"><a style = "color : black;" href='javascript:void(0);' onclick="selectDiv('칵테일');">칵테일</a></td>
				</tr>
			</table>
		</div>
		<br>
	
		<div class="alD">
			<div id="alcohol1" class="alcohol1">	<!-- 소주 -->
				<c:forEach var="alcohol" items="${soju}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}' width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>	
				</c:forEach>
			</div>
			
			<div id="alcohol2" class="alcohol2">	<!-- 맥주 -->
				<c:forEach var="alcohol" items="${beer}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol3" class="alcohol3">	<!-- 전통주 -->
				<c:forEach var="alcohol" items="${traditional}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol4" class="alcohol4">	<!-- 와인 -->
				<c:forEach var="alcohol" items="${wine}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>
				</c:forEach>
			</div>
			
			<div id="alcohol5" class="alcohol5">	<!-- 양주 -->
				<c:forEach var="alcohol" items="${spirits}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>
				</c:forEach>
			</div>
			
			
			
			<div id="searchDiv" class="searchDiv">
				<c:forEach var="alcohol" items="${searchList}">
					<div class="alDiv" onclick="openAlcoholInfo('${alcohol.alcoholId}');">
						<img src='${alcohol.imageUrl}'  width="auto" height="150px"> <br>
						${alcohol.name} <br>
						${alcohol.type} <br>
						<c:choose>
							<c:when test="${alcohol.rate lt 0.5}"> <!-- 별점이 < 0.5 -->
								<img src="<c:url value='/images/star_0.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1}"> <!-- 별점이 0.5 <= < 1 -->
								<img src="<c:url value='/images/star_05.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 1.5}"> <!-- 별점이 1 <= < 1.5 -->
								<img src="<c:url value='/images/star_1.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2}"> <!-- 별점이 1.5 <= < 2 -->
								<img src="<c:url value='/images/star_15.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 2.5}"> <!-- 별점이 2 <= < 2.5 -->
								<img src="<c:url value='/images/star_2.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3}"> <!-- 별점이 2.5 <= < 3 -->
								<img src="<c:url value='/images/star_25.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 3.5}"> <!-- 별점이 3 <= < 3.5 -->
								<img src="<c:url value='/images/star_3.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4}"> <!-- 별점이 3.5 <= < 4 -->
								<img src="<c:url value='/images/star_35.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 4.5}"> <!-- 별점이 4 <= < 4.5 -->
								<img src="<c:url value='/images/star_4.png' />">
							</c:when>
							<c:when test="${alcohol.rate lt 5}"> <!-- 별점이 4.5 <= < 5 -->
								<img src="<c:url value='/images/star_45.png' />">
							</c:when>
							<c:when test="${alcohol.rate ge 5}"> <!-- 별점이 5 이상 -->
								<img src="<c:url value='/images/star_5.png' />">
							</c:when>
						</c:choose>
						<br>
						#${alcohol.tasteString} #${alcohol.flavorString} #${alcohol.corpsString}
					</div>
				</c:forEach>
			</div>
		</div>
		
	<br><br>
	
</body>
</html>
