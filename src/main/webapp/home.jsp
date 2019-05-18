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

        iframe {
            border: 0;
        }
        .newscls{
            float: left;
            padding-right: 10px;
        }
        .newstitle{
            color: #FC9F05;
            font-size: 12px;
             background: -webkit-linear-gradient(left, #CDE7FC , #F9FCFF,#FFFFFF); /* Safari 5.1 - 6.0 */
            background: -o-linear-gradient(right, #CDE7FC , #F9FCFF,#FFFFFF);/* Opera 11.1 - 12.0 */
            background: -moz-linear-gradient(right, #CDE7FC , #F9FCFF,#FFFFFF); /* Firefox 3.6 - 15 */
            background: linear-gradient(to right,  #CDE7FC , #F9FCFF,#FFFFFF); /* 标准的语法 */
        }
        .newsContent{
            font-size: 12px;
        }
    </style>
    <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
</head>
<body>
<div style="width: 1200px;margin: 0 auto">
    <div id="news" style="width: 400px;height:100%;float: left; ">
        <div style="font-weight: bold;width: 100%;float: left; height: 25px;font-size: 14px;">国内新闻</div>

    </div>
    <div id="worldnews" style="width: 400px;height:100%;float: left">

        <span style="font-weight: bold;width: 100%;float: left;font-size: 14px; ">国际新闻</span>

    </div>
    <div style="width: 400px;height:100%;float: left">
        <span style="font-weight: bold;width: 100%;font-size: 14px;">访问量最高的8种动物</span>

        <div id="imageShow" style="width: 400px;height:100%;float: left">

        </div>
    </div>

</div>
<script>
  $(document).ready(function () {
    $.post("${ctx}/queryClass/getTpo8", {},
        function (data) {
          data = JSON.parse(data);//把json字符串转化成json对象
          var html = "";
          for (var i = 0; i < data.length; i++) {
            var eachData = data[i];
            var name = eachData.name;
            var image = eachData.image;
            html = html + '<img src="' + image
                + '" height="150px;" width="150px;" style="margin-left: 10px; margin-top: 10px;float: left;" onclick="queryAnimal(\''
                + name + '\')" alt="' + name + '">';
          }
          $("#imageShow").html(html);
        });
    //国内新闻
    $.post("${ctx}/news/getnews?v="+Math.random(), {type: 1},
        function (data) {
          data = JSON.parse(data);//把json字符串转化成json对象
           var html = "";
          for (var i = 0; i < data.length; i++) {
            var eachData = data[i];
            var theme = eachData.theme;
            var news = eachData.news;
            html = html + ' <div class="newscls"><div class="newstitle">'+theme+'</div><div class="newsContent">'+news+'</div></div>';
          }
          $("#news").append(html);

        });
    //世界新闻
    $.post("${ctx}/news/getnews?v="+Math.random(), {type: 2},
        function (data) {
          data = JSON.parse(data);//把json字符串转化成json对象
           var html = "";
          for (var i = 0; i < data.length; i++) {
            var eachData = data[i];
            var theme = eachData.theme;
            var news = eachData.news;
            html = html + ' <div class="newscls"><div class="newstitle">'+theme+'</div><div class="newsContent">'+news+'</div></div>';
          }
          $("#worldnews").append(html);
        });

  });

  function queryAnimal(name) {
    window.location.href = "${ctx}/queryClass/selectAdmin?name=" + encodeURI(name);
  }
</script>

</body>
</html>
