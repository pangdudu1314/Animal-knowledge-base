<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/5 0005
  Time: 上午 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
  <head>
    <title>首页</title>
    <style type="text/css">
      img{
        width: 270px;
        height: 400px;
      }
      iframe{
        border:0;
      }
    </style>
  </head>
  <body>
    <iframe src="${ctx}/queryClass/gotoFrame" width="100%" height="400px"></iframe>
    <div style="background-color: #cccccc">
      <center>
        <div align="center">
          <img src="images/xuebao.jpg"  alt="雪豹">
          <img src="images/banbeiqianya.jpg" alt="斑背潜鸭">
          <img src="images/beizhuque.jpg" alt="北朱雀">
          <img src="images/chihou.jpg" alt="赤猴">
        </div>
        <div align="center">
          <img src="images/xinxilanbaitu.jpg" alt="新西兰白兔">
          <img src="images/aijimao.jpg" alt="埃及猫">
          <img src="images/dongbeitu.jpg" alt="东北兔">
          <img src="images/duxi.jpg" alt="墨西哥毒蜥">
        </div>
      </center>
    </div>
  </body>
</html>
