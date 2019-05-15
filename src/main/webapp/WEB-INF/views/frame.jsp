<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/24 0024
  Time: 下午 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>查询与修改</title>
    <style type="text/css">
        .shitu-btn{
            display: inline-block;
            font-size: 18px;
            line-height: 16px;
            color: #fff;
            border: 2px solid #3385ff;
            background-color:#3385de ;
            border-radius: 2px;
            box-shadow: 0 0 8px rgba(0,0,0,.5);
            padding: 18px 50px;
            text-decoration: none;
        }
        body{
            /* background: url("images/beijingtupian.jpg") no-repeat 0 0;*/
            background-color: #ccc;
        }

    </style>
</head>
<body >
<div style="margin:100px">
    <h1 >
        <center>
            <p  style="color:#0000FF">动物领域多模态知识库查询系统</p>
        </center>
    </h1>
    <center>
        <a href="${ctx}/queryClass/systemDiagram" class="shitu-btn" target="_parent">动物体系图</a>
        <a href="${ctx}/queryClass/queryAnimal" class="shitu-btn">查询</a>
        <a href="${ctx}/queryClass/updateAnimal"  class="shitu-btn">修改</a>
        <a href="${ctx}/animalCheck/gotoIndex" target="_parent" class="shitu-btn">管理员</a>
    </center>
</div>
</body>
</html>

