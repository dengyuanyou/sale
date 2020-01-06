<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
	$(function () {
		//使用js获取cookie
		var name = getMyCookie("nickname");
		$("#show_name").text(decodeURIComponent(name));

		function getMyCookie(key) {
		    var val = "";

		    var cookies = document.cookie;
		    cookies = cookies.replace(/\s/,"");
		    var cookieArray = cookies.split(";");
		    for(var i=0;i<cookieArray.length;i++){
				var array = cookieArray[i];
				var s = array.split("=");
				if(s[0]==key){
				    val = s[1];
				}
			}
			return val;
        }

    })
	
</script>
<title>硅谷商城</title>
</head>
<body>

	<div class="top">
		<div class="top_text">
			<c:if test="${empty user}">
				<a href="goto_login.do">用户登录:<span id="show_name" style="color: red"></span></a>
				<a href="goto_logout.do">用户注销</a>
			</c:if>
			<c:if test="${not empty user}">
				<<a href="">用户名称:${user.name}</a>
				<<a href="">用户订单</a>
			</c:if>
		</div>
	</div>

</body>
</html>