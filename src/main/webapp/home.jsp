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

      iframe{
        border:0;
      }
    </style>
    <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
   </head>
  <body>
  <div id="imageShow"  style="width: 1000px;margin: 0 auto" >

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
                          html=html+'<img src="'+image+'" height="280px;" width="220px;" style="margin-left: 10px; float: left;" onclick="queryAnimal(\''+name+'\')" alt="'+name+'">';
                      }
                      $("#imageShow").html(html);
                  });
          });

          function queryAnimal(name){
              window.location.href = "${ctx}/queryClass/selectAdmin?name=" + encodeURI(name);
          }
      </script>

  </body>
</html>
