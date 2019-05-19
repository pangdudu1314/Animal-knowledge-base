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
    <title>新闻列表</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="${typeCn}列表" style="width:100%;height:500px"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${ctx}/news/getnewsPage?type=${typeEn}',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'theme',width:400">新闻标题</th>
        <th data-options="field:'time',width:170,formatter:DateTimeFormatter">发布时间</th>
         <th data-options="field:'news',width:600">新闻内容</th>
         <th data-options="field:'id',width:240,formatter:opAnimal">操作</th>
    </tr>
    </thead>
</table>

<script>
  function DateTimeFormatter(value) {
    var unixTimestamp = new Date(value);
    var year = unixTimestamp.getFullYear();
    var month= unixTimestamp.getMonth()+1;
    var day = unixTimestamp.getDate();
    var hours = unixTimestamp.getHours() ;
    var minutes = unixTimestamp.getMinutes() ;
    var seconde = unixTimestamp.getSeconds();
    var result = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconde;
    return result;

  }
  function opAnimal(val, row) {
        return "<a href=\"javascript:void(0);\" onclick=\"gotoNewDetail('" + val + "');\">查看详情</a>\n";
  }
  function gotoNewDetail(id){
    window.location.href = "${ctx}/news/gotoNewDetail?id=" + id;
  }

</script>
</body>
</html>
