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

        function save_param(attr_id,val_id,attr_name) {
            //点击属性和属性值按钮存储属性和属性值
            $("#paramArea").append("<input name='attrArray' type='text' value='{\"attr_id\":"+attr_id+",\"val_id\":"+val_id+"}'>"+attr_name);

            //发送ajax请求
            get_list_by_attr();

        }

        function get_list_by_attr() {

            //定义一个json对象
            var jsonArray = {};
            //获取一个input集合并遍历集合
            $("#paramArea input[name='attrArray']").each(function (i,data) {
                //获取单个input中的value值
               var json = $.parseJSON(data.value);
               //封装成json数组
               jsonArray["list_attr["+i+"].attrId"] = json.attr_id;
               jsonArray["list_attr["+i+"].valueId"] = json.val_id;
               jsonArray["classTwoId"] = ${classTwoId};
            });

            //根据属性值查询商品列表
            $.post("get_list_by_attr.do",jsonArray,function (data) {
                //将返回的HTML页面设置到商品列表上
                $("#skuListInner").html(data);

            });

        }

    </script>

    <title>硅谷商城</title>
</head>
<body>
        <div style="display: none" id="paramArea"></div>
        <hr>
        属性列表<br>
        <c:forEach items="${list_attr}" var="attr">
            ${attr.name}:
            <c:forEach items="${attr.list_value}" var="val">
                <a href="javascript:save_param(${attr.id},${val.id},'${val.value}${val.name}');">${val.value}${val.name}</a>
            </c:forEach>
            <br>
        </c:forEach>
        <br>
</body>
</html>