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
        body{
            padding: 0;
            margin: 0;
        }
        iframe {
            border: 0;
        }
    </style>
    <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctx}/js/layui-v2.4.5/layui/css/layui.css" media="all">
</head>
<body>
<div style="width: 100%;height:60px;background-color: #2F4056;">
    <h1>
        <img src="images/logo.gif" style="width: 60px;float: left;height: 60px">
        <p style="color:white;width: 400px;float: left;padding-top: 10px;padding-left: 10px;">
        动物领域多模态知识库</p></h1>
    <ul class="layui-nav layui-bg-cyan" style="float: left;width: 800px;">
        <li class="layui-nav-item layui-this"><a href="${ctx}/home.jsp" target="frameName">首页</a></li>
        <li class="layui-nav-item"><a href="${ctx}/queryClass/systemDiagram"  target="frameName">动物图谱</a></li>
        <li class="layui-nav-item">
            <a href="javascript:;">查询</a>
            <dl class="layui-nav-child">
                <dd><a href="${ctx}/queryClass/wenzi" target="frameName">文字查询</a></dd>
                <dd><a href="${ctx}/queryClass/tupian" target="frameName">图片查询</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">修改</a>
            <dl class="layui-nav-child">
                <dd><a href="${ctx}/queryClass/addAnimal" target="frameName">添加</a></dd>
                <dd><a href="${ctx}/queryClass/deleteAnimal" target="frameName">删除</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:;">管理员</a>
            <dl class="layui-nav-child">
                <dd><a href="${ctx}/animalCheck/gotoIndex"  target="frameName">审核数据</a></dd>
                <dd><a href="${ctx}/news/gotoAddNews" target="frameName">添加新闻</a></dd>
            </dl>
        </li>
    </ul>
</div>
<iframe id="iframeid" name="frameName" src="${ctx}/home.jsp" width="100%" style="background-color: #FFFFFF"></iframe>
<div style="width: 500px;margin: 0 auto;color: #999;font-size: 12px"> 版权所有 李林杰       Copyright©毕业设计 (2019-2019)</div>
<script src="${ctx}/js/layui-v2.4.5/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
  $(document).ready(function(){
    $("#iframeid").attr("height",$(window).height()-80)
  });


  layui.use('element', function(){
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

    //监听导航点击
    element.on('nav(demo)', function(elem){
      //console.log(elem)
      layer.msg(elem.text());
    });
  });
</script>
</body>
</html>
