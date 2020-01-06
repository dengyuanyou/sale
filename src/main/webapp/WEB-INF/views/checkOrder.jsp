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

        //提交订单
        function order_submit() {

            $("#order_form").submit();

        }

        //选中地址单选框改变地址
        function address_show(address) {
            $("#show_addr").html(address);
        }

    </script>
    <title>硅谷商城</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="searchArea.jsp"/>

    <div class="message">
        <div class="msg_title">
            收货人信息
        </div>

        <form action="/save_order.do" id="order_form">
        <c:forEach items="${list_addr}" var="addr">
            <div class="msg_addr">
                <span class="msg_left">
                    ${addr.receiver} 北京
                </span>
                <span class="msg_right">
                    ${addr.userAddress} &nbsp;&nbsp;<input id="address_radio" onclick="address_show('${addr.userAddress}${addr.receiver}${addr.phone}')" type="radio" name="address.id" value="${addr.id}"/>
                </span>
            </div>
        </c:forEach>
        </form>

        <span class="addrs">查看更多地址信息</span>
        <div class="msg_line"></div>

        <div class="msg_title">
            送货清单
        </div>

        <c:forEach items="${order.list_flow}" var="flow">
            <div style="margin-top: 20px;">
                <c:forEach items="${flow.list_info}" var="order_info" varStatus="index">
                    <div class="msg_list">
                        <div class="msg_list_left">
                            <c:if test="${index.index==0}">
                                配送方式：${flow.currentPlace}
                                <div class="left_title">
                                    ${flow.deliver}
                                </div>
                            </c:if>
                        </div>
                        <div class="msg_list_right">
                            <div class="msg_img">
                                <img width="80px" height="80px" src="/upload/image/${order_info.productImage}" alt="">
                            </div>
                            <div class="msg_name">
                                ${order_info.skuName}
                            </div>
                            <div class="msg_price">
                                ￥${order_info.skuPrice}
                            </div>
                            <div class="msg_mon">
                                ${order_info.skuNumber}
                            </div>
                            <div class="msg_state">
                                有货
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>

        <div class="msg_line"></div>

        <div class="msg_sub">
            <div class="msg_sub_tit">
                应付金额:
                <b>￥${order.totalAmout}</b>
            </div>
            <div class="msg_sub_adds">
                寄送至：<span id="show_addr"></span>
            </div>

            <button class="msg_btn" style="cursor: pointer;" onclick="order_submit()">提交订单</button>

        </div>

    </div>

</body>
</html>