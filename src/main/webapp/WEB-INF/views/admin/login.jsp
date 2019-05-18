<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/12 0012
  Time: 下午 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
<div style="margin:0 auto;width: 400px;height: 500px;padding-top: 100px;">
    <div class="easyui-panel" title="用户登录" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="ff" method="post" action="${ctx}/admin/login">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="username" prompt="用户名" iconWidth="28" style="width:100%;height:34px;padding:10px;">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-passwordbox" name="password" prompt="密码" iconWidth="28" style="width:100%;height:34px;padding:10px">
            </div>
            <div style="margin-bottom:20px;color: red;">
                ${errorInfo}
             </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登录</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">取消</a>
        </div>
    </div>
</div>
<script>
  function submitForm(){
    $('#ff').submit()
  }
  function clearForm(){
    $('#ff').form('clear');
  }
</script>
</body>
</html>
