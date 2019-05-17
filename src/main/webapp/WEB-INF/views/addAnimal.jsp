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
    <title>添加动物</title>
    <style type="text/css">
        body {
            background-color: #ccc;
            margin: 0;
            padding: 0;
        }
    </style>

    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script src="${ctx}/js/ajaxfileupload.js" type="text/javascript"></script>


</head>
<body>
<div>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="添加动物" style="width:800px">
        <div style="padding:10px 60px 20px 60px">
            <form id="ff" method="post">
                <table cellpadding="5">
                    <tr>
                        <td>动物类别:</td>
                        <td>
                            <input id="kemu" name="kemu" class="easyui-combobox" name="language" style="width:200px;"
                                   data-options="
				<%--url: 'combobox_data2.json',--%>
				url:'${ctx}/queryClass/queryClassLevel?name=',
				method: 'get',
				valueField:'id',
				textField:'text',
				groupField:'group',

				onSelect: function(rec){
					$('#cc2').combobox('clear');
                        var url = '${ctx}/queryClass/queryClassLevel?name='+rec.id;
                        $('#cc2').combobox('reload', url);
                    }
			">
                        </td>
                    </tr>
                    <tr>
                        <td>动物名称:</td>
                        <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input>
                        </td>
                    </tr>
                    <tr>
                        <td>动物图片:</td>
                        <td><input id="file" onchange="uploadImg(this,'preview')" type="file" name="file"></input></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <div id="preview">
                                <img src="" alt="" id="imghead5" height="200" width="200"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>动物介绍:</td>
                        <td><input class="easyui-textbox" name="message" data-options="multiline:true"
                                   style="height:60px"></input></td>
                    </tr>

                </table>
            </form>
            <div style="text-align:center;padding:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
            </div>
        </div>
    </div>
    <script>
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

        function submitForm() {
            //先上传文件图片到服务器，然后再提交表单。因为这个上传图片的js方法是异步ajax提交
            //在这里做判断，是否页面已经选择了文件

            //如果文件名成一样，应该是要给予提示比较好，因为会覆盖
            var uploadFile = $("#file").val();
            if (uploadFile == undefined || uploadFile == null || uploadFile == "") {
                alert("请选择文件");
                return;
            }
            $.ajaxFileUpload
            (
                {
                    url: '${ctx}/fileUpload/uploadImage2ImageDr', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: ['file'], //文件上传域的ID
                    dataType: 'json', //这里指定了json了，下面的data就不用再转化了，返回的data就是json对象
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.result == "文件已存在") {
                            alert("上传的文件已存在，请重新选择文件");
                            return;
                        }
                        $('#ff').form('submit', {
                            url: "${ctx}/queryClass/addAnimalMethod",
                            onSubmit: function (param) {

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

        function clearForm() {
            $('#ff').form('clear');
        }
    </script>
</div>
</body>
</html>
