<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath %>">
    <link rel="stylesheet" type="text/css" href="/css/css.css">
    <link rel="stylesheet" href="/css/style.css">
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        
      function change_isCheck(checked,skuId) {
          var isChecked = "0";
          if(checked){
              isChecked = "1";
          }

          //发送ajax请求给后台
          $.post("change_isCheck.do",{isCheck:isChecked,skuId:skuId},function (data) {

              //重新刷新购物车列表
              $("#cartListInner").html(data);
          });
      }

    </script>
    <title>硅谷商城</title>
</head>
<body>

    <div class="search">
        <div class="logo"><img src="/images/logo.jpg" alt=""></div>
        <div class="search_on">
            <div class="se">
                <input type="text" name="search" class="lf">
                <input type="submit" class="clik" value="搜索" style="height: 32px;">
            </div>
            <div class="se">
                <a href="">取温神器</a>
                <a href="">1元秒杀</a>
                <a href="">吹风机</a>
                <a href="">玉兰油</a>
            </div>
        </div>
    </div>

    <div id="cartListInner">
        <jsp:include page="cartListInner.jsp"/>
    </div>

    <div class="footer">
        <div class="top"></div>
        <div class="bottom"><img src="/images/foot.jpg" alt=""></div>
    </div>

</body>
</html>