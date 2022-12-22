<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/default.css">
  <link rel="stylesheet" href="../css/main.css">
  <link rel="stylesheet" href="../css/qna.css">
  <link rel="stylesheet" href="../css/animation.css">
  <link rel="stylesheet" href="../css/result.css">
</head>

<body>
  	<!-- 네비게이션 바 -->
	<%@include file="/WEB-INF/navbar.jsp" %>
  <div class="container">
    <section id="main" class="mx-auto my-5 py-5 px-3">
      <h1>나와 어울리는 술 테스트하기</h1>
      <p>
        아래 시작하기 버튼을 눌러 시작해 주십시오.
      </p>
      <button type="button" class="btn btn-outline-danger mt-3" onclick="js:begin()">시작하기</button>
    </section>
    <section id="qna">
      <div class="status mx-auto mt-5">
        <div class="statusBar">
        </div>
      </div>
      <div class="qBox my-5 py-3 mx-auto">

      </div>
      <div class="answerBox">

      </div>
    </section>
    <section id="result" class="mx-auto my-5 py-5 px-3">
      <h1>당신의 결과는?!</h1>
      <br>
      <div class="resultname">

      </div>
      <div id="resultImg" class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">

      </div>
      <div class="resultDesc">

      </div>
    </section>
    <script src="../js/data.js" charset="utf-8"></script>
    <script src="../js/start.js" charset="utf-8"></script>
  </div>
</body>

</html>
