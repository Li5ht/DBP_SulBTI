<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand bg-dark navbar-dark">
  <!-- Links -->
  <ul class="navbar-nav">
  	<li class="nav-brand">
  		<a class="nav-link btn-link" href="<c:url value='/home/home' />">
  			<img src="<c:url value='/images/logo.png' />" alt="logo"/>
  		</a>
  	</li>
  	
    <li class="nav-item dropdown">
    	<br style="line-height:10px;">
      <a class="nav-link dropdown-toggle"  href="<c:url value='/recommend/list' />" id="navbardrop" data-toggle="dropdown">
       	술 추천
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<c:url value='/recommend/test' />">술BTI</a>
        <a class="dropdown-item" href="<c:url value='/recommend/list' />">나만을 위한 추천</a>
        <a class="dropdown-item" href="<c:url value='/recommend/list' />">랭킹</a>
      </div>
    </li>
    
    <li class="nav-item">
   		 <br style="line-height:10px;">
		<a class="nav-link btn-link"  href="<c:url value='/simulate'/>">주량 시뮬레이션</a>
    </li>
    
    <li class="nav-item">
    	<br style="line-height:10px;">
		<a class="nav-link btn-link" href="<c:url value='/diary/list'/>">음주 기록</a>
    </li>
    
    <li class="nav-item">
    	<br style="line-height:10px;">
		<a class="nav-link btn-link" href="<c:url value='/product/info'/>">술 정보</a>
    </li>
    
  </ul>
  
    <span style="width: 50%;"></span>
    
    <c:if test="${noLogin}">
   	<span class="nav-log">
    	<a type="button" class="nav-link btn-log" style="color: #eeff9c;" href="<c:url value='/user/login/form'/>">로그인</a>
  	</span>
  	<span class="nav-log" style="margin-left: 10px;">
    	<a class="nav-link btn-log" style="color: #eeff9c;" href="<c:url value='/user/register'/>">회원 가입</a>
  	</span>
	</c:if>
	<c:if test="${hasLogin}">
   		<span class="nav-log">
 			<a type="button" class="nav-link"style="color: #eeff9c;" href="<c:url value='/user/view'>
						   <c:param name='userId' value='${userId}'/>
				 		 </c:url>">
			${nickname}님</a>
		</span>
   		<span class="nav-log" style="margin-left: 10px;">
		    <a type="button" class="nav-link"style="color: #eeff9c;" href="<c:url value='/user/logout'/>">로그아웃</a>
	  	</span>
	</c:if>
	
</nav>
