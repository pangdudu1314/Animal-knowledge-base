<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/5 0005
  Time: 上午 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>图片查询</title>
    <style type="text/css">
        body{
            /*background: url("images/beijingtupian.jpg") no-repeat 0 0;*/
         }
        table.hovertable {
            font-family: verdana,arial,sans-serif;
            width: 100%;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #999999;
            border-collapse: collapse;
        }
        table.hovertable th {
            background-color:#c3dde0;
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
        table.hovertable tr {
            background-color:#d4e3e5;
        }
        table.hovertable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #a9c6c9;
        }
    </style>
    <script src="${ctx}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/ajaxfileupload.js" type="text/javascript"></script>
</head>
<body>
<div style="">
    <h1>
        <center>
            <font style="color:#0000FF">动物图像搜索</font>
        </center>
    </h1>
    <center>
        <div class="yanzRight">
            <input name="evidence" onchange="uploadImg(this,'preview')" id="file"  type="file"/>
            <span class="dui" id="imgOrder_dui" style="display: none;"></span>
            <input  type="button" onclick="upload();" value="提交">

        </div>
        <div id="preview">
            <img src="${ctx}/images/nopic.jpg" alt="" id="imghead5" height="200" width="200" />

        </div>
        <div id="animalInfoId" style="float: left;width: 100%;">
            <table class="hovertable">
                <tr>
                    <th width="100">动物名称</th><th width="100">相似度</th><th>图片</th><th>介绍</th>
                </tr>
                <tbody id="tbody">

                </tbody>


            </table>

        </div>

    </center>
</div>
<script>
    function uploadImg(file,imgNum){
        var widthImg = 200; //显示图片的width
        var heightImg = 200; //显示图片的height
        var div = document.getElementById(imgNum);
        if (file.files && file.files[0]){
            div.innerHTML ='<img id="upImg">'; //生成图片
            var img = document.getElementById('upImg'); //获得用户上传的图片节点
            img.onload = function(){
                img.width = widthImg;
                img.height = heightImg;
            }
            var reader = new FileReader(); //判断图片是否加载完毕
            reader.onload = function(evt){
                if(reader.readyState === 2){ //加载完毕后赋值
                    img.src = evt.target.result;
                }
            }
            reader.readAsDataURL(file.files[0]);
        }
    }
    function gotoAnimal(animalName){
      window.location.href="${pageContext.request.contextPath}/queryClass/selectAdmin?name="+animalName;
    }
    function upload(){
        $.ajaxFileUpload
        (
            {
                url: '${ctx}/fileUpload/singleUpload', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: ['file'], //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    console.log(data);
                  var html="";
                    for(var i=0;i<data.length;i++){
                      html=html+' <tr onmouseover="this.style.backgroundColor=\'#ffff66\';" onmouseout="this.style.backgroundColor=\'#d4e3e5\';">\n'
                          + '<td><a href="javascript:void(0);" onclick="gotoAnimal(\''+data[i].name+'\');">'+data[i].name+'</a></td>' +
                          '<td>'+data[i].score+'</td>' +
                          '<td><img src="${ctx}/'+data[i].image+'"  onclick="gotoAnimal(\''+data[i].name+'\');" height="100" width="100" /></td>' +
                          '<td>'+data[i].intro+'</td>\n'
                          + '                </tr>';
                    }
$("#tbody").html(html);

                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
    }
</script>
</body>
</html>