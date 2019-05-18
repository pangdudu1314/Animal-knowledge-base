<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/30 0030
  Time: 下午 6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加新闻</title>
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
        }
    </style>

    <link rel="stylesheet" type="text/css"
          href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${ctx}/js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script src="${ctx}/js/ajaxfileupload.js" type="text/javascript"></script>


</head>
<body>
<div>
    <div class="easyui-panel" title="添加新闻" style="width:100%">
        <form id="ff" method="post">
            <table cellpadding="5">

                <tr>
                    <td>新闻标题</td>
                    <td><input class="easyui-textbox" type="text" name="theme"
                               data-options="required:true"></input>
                    </td>
                </tr>
                <tr>
                    <td>新闻类别</td>
                    <td><select class="easyui-combobox" name="type" style="width:100%;">
                        <option value="1">国内新闻</option>
                        <option value="2">国际新闻</option>

                    </select></td>
                </tr>

                <tr>
                    <td>新闻内容</td>
                    <td><input class="easyui-textbox" name="news"
                               data-options="multiline:true,required:true"
                               style="height:200px"></input></td>
                </tr>

            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
        </div>
    </div>
    <script>


      function submitForm() {

        $('#ff').form('submit', {
          url: "${ctx}/news/addNews",
          onSubmit: function (param) {

          },
          //获取后台传来的json
          success: function (data) {
            data = JSON.parse(data);//将字符串json转成对象json
            alert("添加成功")
          }
        });

      }

      function clearForm() {
        $('#ff').form('clear');
      }
    </script>
</div>
</body>
</html>
