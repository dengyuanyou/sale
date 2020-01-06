<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">

        function b() {
        }

    </script>

    <title>硅谷商城</title>
</head>
<body>
        商品列表<br>
        <c:forEach items="${list_sku}" var="sku">
            <div style="margin-left: 12px;margin-top: 15px;  float: left;border: 1px red solid;width: 200px;height: 200px">
                <img src="/upload/image/${sku.product.imageUrl}" alt="" width="200px" height="100px"><br>
                <a href="goto_sku_detail.do?skuId=${sku.id}&productId=${sku.productId}" target="_blank">名称：${sku.skuName}</a><br>
                价格：${sku.price}<br>
                库存：${sku.number}<br>
            </div>
        </c:forEach>

</body>
</html>