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
    <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
  </head>
  <body>
    <iframe src="${ctx}/queryClass/gotoFrame" width="100%" height="400px"></iframe>
    <div style="background-color: #cccccc">
      <div id="imageShow"  style="width: 100%;height: 200px;" align="center">

      <%--  <c:forEach items="${animalInfo.siblings}" var="item">
          <div style="float: left;width: 82px;height: 100px;cursor: pointer;"  onclick="queryAnimal('${item.name}')">
            <img src="${item.image}"  style="width: 80px;height: 80px;float: left">
            <span style="height: 20px;width: 80px;float: left">${item.name}</span>
          </div>
        </c:forEach>--%>
      </div>
      <script>
          $(document).ready(function(){
              $.post("${ctx}/queryClass/getTpo8", {},
                  function(data){
                      data=JSON.parse(data);//把json字符串转化成json对象
                      var html="";
                      for(var i=0;i<data.length;i++){
                          var  eachData=data[i];
                          var name=eachData.name;
                          var image=eachData.image;
                          html=html+'<img src="'+image+'" onclick="queryAnimal(\''+name+'\')" alt="'+name+'">';
                      }
                      $("#imageShow").html(html);
                  });
          });

          function queryAnimal(name){
              window.location.href = "${ctx}/queryClass/selectAdmin?name=" + encodeURI(name);
          }
      </script>
    </div>
  </body>
</html>
