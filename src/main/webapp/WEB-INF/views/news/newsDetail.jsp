<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/19 0019
  Time: 下午 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>新闻详情</title>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
        }
        .main{
            width: 1000px;
            margin: 0 auto;
        }
        .title{
            font: bold 24px Microsoft Yahei;
            padding: 0 0 20px 0;
            letter-spacing: -1.5px;
            color: #222222;
        }
        .time{
            float: left;
            width: 250px;
            color: #999;
            line-height: 22px;
            width: 100%;
            font-size: 12px;
        }
        .line{
            border-top: 1px solid #ddd;
            height: 9px;
            overflow: hidden;
            clear: both;
            width: 100%;
        }
        .content{
            text-indent: 28px;
            font-size: 16px;
            line-height: 32px;
            margin-bottom: 20px;
            text-align: justify;
            word-wrap: break-word;
            word-break: normal;
        }
    </style>
</head>
<body >
<div class="main">
    <div class="title">${news.theme}</div>
    <div class="time"><fmt:formatDate value="${news.time}" pattern="yyyy年MM月dd日 HH:mm:ss"/></div>
    <div class="line"></div>
    <div class="content">${news.news}</div>

</div>
</body>
</html>
