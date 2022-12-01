<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>나와 어울리는 술 테스트하기</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel=stylesheet href="<c:url value='/css/common.css' />" type="text/css">
<script>

</script>
</head>
<body>
	테스트 폼 (추후 수정;;ㅋㅋ)
	<form method="post" action="<c:url value='/recommend/test' />">
		<input type='radio' name="1" value="0" />선택1
  		<input type='radio' name="1" value="1" />선택2<br>
  		
  		<input type='radio' name="2" value="0" />선택1
  		<input type='radio' name="2" value="1" />선택2<br>
  		
  		<input type='radio' name="3" value="0" />선택1
  		<input type='radio' name="3" value="1" />선택2<br>
  		
  		<input type='radio' name="4" value="0" />선택1
  		<input type='radio' name="4" value="1" />선택2<br>
  		
  		<input type='radio' name="5" value="0" />선택1
  		<input type='radio' name="5" value="1" />선택2<br>
  		
  		<input type='radio' name="6" value="0" />선택1
  		<input type='radio' name="6" value="1" />선택2<br>
  		
  		<input type='radio' name="7" value="0" />선택1
  		<input type='radio' name="7" value="1" />선택2<br>
  		
  		<input type='radio' name="8" value="0" />선택1
  		<input type='radio' name="8" value="1" />선택2<br>
  		
  		<input type='radio' name="9" value="0" />선택1
  		<input type='radio' name="9" value="1" />선택2<br>
  		
  		<input type='radio' name="10" value="0" />선택1
  		<input type='radio' name="10" value="1" />선택2<br>
  		
  		<input type="submit" value="제출">
	</form>
</body>
</html>