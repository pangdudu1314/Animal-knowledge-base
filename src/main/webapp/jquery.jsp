<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/26 0026
  Time: 下午 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <script src="${ctx}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <title>Title</title>
</head>
<body>
<input type="text" id="aa"> <input type="button" onclick="getTextInfo();" value="获取文本框数据">
<script type="text/javascript">
    function getTextInfo(){
        alert(${"#aa"}.val());
    }
</script>
<br>

</body>
</html>

































































