<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>Title</title>
    <style type="text/css">
        thml body {
            margin: 0;
            padding: 0;
        }
    </style>
    <script src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $("#kemu").combobox({});
        function search() {
            var s1 = $("#kemu").val();
            var s2 = $("#cc2").val();
            if(s1==""||s1==undefined){
                alert("请选中动物类别");
                return;
            }

            if(s2==""||s2==undefined){

              /* window.parent.location.href = "${ctx}/queryClass/queryClassLevel?queryClassLevel=1&name=" + s1;*/
                window.parent.location.href = "${ctx}/queryClass/selectAdmin?name=" + encodeURI(s1);
            }else {
              /*  window.parent.location.href = "/tuxiangzhanshi/selectAdmin?name=" + s2;*/
               /* window.parent.location.href = "${ctx}/queryClass/queryClassLevel?queryClassLevel=2&name=" + s2;*/
                window.parent.location.href = "${ctx}/queryClass/selectAdmin?name=" + encodeURI(s2);
            }
        }
        /*function kemu() {
            if($("#kemu").val()!=''){
                //$("#cc2").removeAttr("disabled");
                $('#cc2').attr('disabled',false);
            }
        }
*/
    </script>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
</head>
<body style="margin-top: 100px; background-color: #cccccc">
<div>
    <h1 style="color:#0000FF;text-align:center; width: 100%;">动物名称和所属科目搜索</h1>
    <div style="width: 100%;height:50px;">
    <%----%>
        <div style="width: 800px; height: 50px;float: left;margin-left: 330px;">
            动物类别：<input id="kemu" type="text" class="easyui-combobox"
                        data-options="
					url:'${ctx}/queryClass/queryClassLevel?level=1&name=',
					method:'post',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',

					onSelect: function(rec){
					$('#cc2').combobox('clear');
                        var url = '${ctx}/queryClass/queryClassLevel?level=2&name='+rec.id;
                        $('#cc2').combobox('reload', url);
                    }"
            />

            动物名称：<input id="cc2" class="easyui-combobox"  data-options="method:'post',valueField:'id',textField:'text'"/>
           <%-- <a href="javascript:;" class="easyui-linkbutton" style="width:80px" onclick="search()">查询</a>--%>
            <%--<a href="#" class="easyui-linkbutton" style="width:80px" onclick="search()">查询</a>--%>
            <input type="button" onclick="search()" value="查询">
        </div>


    </div>
</div>
</body>

</html>