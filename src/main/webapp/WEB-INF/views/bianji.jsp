<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/22 0022
  Time: 下午 7:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-color: #cccccc;
        }
        .shitu-btn{
            display: inline-block;
            font-size: 18px;
            line-height: 16px;
            color: #fff;
            border: 2px solid #3385ff;
            background-color:#3385de ;
            border-radius: 2px;
            box-shadow: 0 0 8px rgba(0,0,0,.5);
            padding: 18px 30px;
            text-decoration: none;
        }
        #submitView1{
            width:100px;
            height:56px;
            border: solid 1px #3385ff;
            border-radius: 2px;
            background-color: #3385de;
            box-shadow: 0 0 8px rgba(0,0,0,.5);
            color: #fff;
            font-size: 18px;
            line-height: 16px;
            c8ursor: pointer;
            margin-right: 40px;
        }
       #submitView1:HOVER {
            background-color: #317ef3;/*//鼠标移动过去时，背景颜色发生变化*/
        }
    </style>
    <script src="${ctx}/js/jquery.min.js"></script>

</head>
<body >
<div align="center">
    <h1 style="color:#0000FF">动物领域多模态查询系统</h1>
    <table cellpadding="0" cellspacing="10" border="0">
        <tr>
            <td>动物名称:</td>
            <td>
                <input id="name" type="text" value="${animalInfo.name}" disabled="disabled">
            </td>
        </tr>
        <tr>
            <td>动物图片:</td>
            <td><img src="${pageContext.request.contextPath}/${animalInfo.image}" style="width: 80px;height: 80px;float: left"></td>
        </tr>
        <tr>
            <td>动物介绍：</td>
            <td>
                <textarea  id="intro" style="width: 300px;height:200px;" >
                <%-- <% out.println(request.getAttribute("person_info"));%>--%>
                ${animalInfo.intro}
                </textarea>
            </td>
        </tr>
        <tr>
            <td> <input id="submitView1" onclick="update();" type="button" name="sub" value="提交" /></td>
            <td align="center"><a href="${pageContext.request.contextPath}/index.jsp" class="shitu-btn">返回</a></td>
        </tr>
    </table>
  <%-- <div style="margin-top: 20px"> 动物名称:<input id="name" type="text" value="${animalInfo.name}" disabled="disabled"></div>
    <br>
    <div style="margin-right: 156px">动物图片:</div>
    <br>
    <img src="${pageContext.request.contextPath}/${animalInfo.image}" style="width: 80px;height: 80px;float: left">

    <div style="margin-right: 156px">动物介绍：</div>
    <br>
    <textarea  id="intro" style="width: 300px;height:200px;" >
        &lt;%&ndash; <% out.println(request.getAttribute("person_info"));%>&ndash;%&gt;
        ${animalInfo.intro}
    </textarea>
    <br>
    <input id="submitView1" onclick="update();" type="button" name="sub" value="提交" />
   &lt;%&ndash; <a href="" onclick="search();" class="shitu-btn">提交</a>&ndash;%&gt;
    <a href="${pageContext.request.contextPath}/queryClass/wenzi" class="shitu-btn">返回</a>--%>
</div>


</body>
<script type="text/javascript">
    function update(){
        var name=$("#name").val();
        var intro=$("#intro").val();
        $.post("${ctx}/queryClass/updateAnimalInfo", { name: name, intro: intro },
            function(data){
                alert("Data Loaded: " + data);
            });
           alert("提交成功")
    }

</script>
</html>
