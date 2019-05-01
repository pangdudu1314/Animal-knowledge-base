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
        body {
            background-color: #cccccc;
        }

        .shitu-btn {
            display: inline-block;
            font-size: 18px;
            line-height: 16px;
            color: #fff;
            border: 2px solid #3385ff;
            background-color: #3385de;
            border-radius: 2px;
            box-shadow: 0 0 8px rgba(0, 0, 0, .5);
            padding: 18px 30px;
            text-decoration: none;
        }

        #submitView1 {
            width: 100px;
            height: 56px;
            border: solid 1px #3385ff;
            border-radius: 2px;
            background-color: #3385de;
            box-shadow: 0 0 8px rgba(0, 0, 0, .5);
            color: #fff;
            font-size: 18px;
            line-height: 16px;
            c8ursor: pointer;
            margin-right: 40px;
        }

        #submitView1:HOVER {
            background-color: #317ef3; /*//鼠标移动过去时，背景颜色发生变化*/
        }
    </style>

    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script src="${ctx}/js/ajaxfileupload.js" type="text/javascript"></script>
</head>
<body>
<div align="center">
    <h1 style="color:#0000FF">动物领域多模态查询系统</h1>
    <form id="ff" method="post">
        <table cellpadding="0" cellspacing="30" border="0">
            <tr>
                <td>动物名称:</td>
                <td>
                    <input id="name" name="name" type="text" value="${animalInfo.name}" disabled="disabled">
                    <!-- disabled="disabled" 设置了这个了，这个属性就不会提交到后台了-->
                </td>
            </tr>
            <tr>
                <td>动物图片:</td>
                <td>
                    <div id="preview">
                        <img src="${pageContext.request.contextPath}/${animalInfo.image}" alt="" id="imghead5"
                             style="width: 200px;height: 200px;float: left"/>
                    </div>
                </td>
            </tr>

            <tr>
                <td></td>
                <td>
                    <input id="file" onchange="uploadImg(this,'preview')" type="file" name="file">
                </td>
            </tr>
            <tr>
                <td>动物介绍：</td>
                <td>
                <textarea id="intro" name="intro" style="width: 300px;height:200px;">

                    ${animalInfo.intro}
                </textarea>
                </td>
            </tr>
            <tr>
                <td><input id="submitView1" onclick="update();" type="button" name="sub" value="提交"/></td>
                <td align="center"><a href="${pageContext.request.contextPath}/index.jsp" class="shitu-btn">返回</a></td>
            </tr>
        </table>
    </form>
</div>


</body>
<script type="text/javascript">
    function uploadImg(file, imgNum) {
        var widthImg = 200; //显示图片的width
        var heightImg = 200; //显示图片的height
        var div = document.getElementById(imgNum);
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id="upImg">'; //生成图片
            var img = document.getElementById('upImg'); //获得用户上传的图片节点
            img.onload = function () {
                img.width = widthImg;
                img.height = heightImg;
            }
            var reader = new FileReader(); //判断图片是否加载完毕
            reader.onload = function (evt) {
                if (reader.readyState === 2) { //加载完毕后赋值
                    img.src = evt.target.result;
                }
            }
            reader.readAsDataURL(file.files[0]);
        }

    }

    function update() {
        var name = $("#name").val();
        var file = $("#file").val();
        var intro = $("#intro").val();
        $.ajaxFileUpload
        (
            {
                url: '${ctx}/fileUpload/uploadImage2ImageDr', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: ['file'], //文件上传域的ID
                dataType: 'json', //这里指定了json了，下面的data就不用再转化了，返回的data就是json对象
                success: function (data, status)  //服务器成功响应处理函数
                {//当图片上传成功以后，再提交数据

                    $('#ff').form('submit', {
                        url: "${ctx}/queryClass/updateAnimalMethod",
                        onSubmit: function (param) {
                            param.name=name;//在这里补充上此参数
                        },
                        //获取后台传来的json
                        success: function (data) {
                            data = JSON.parse(data);//将字符串json转成对象json
                            alert(data.result)
                        }
                    });

                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
    }

</script>
</html>
