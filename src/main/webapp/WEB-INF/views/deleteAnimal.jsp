<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/1 0001
  Time: 上午 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>删除动物</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
</head>
<body>
      <div style="padding:10px 60px 20px 60px;width: 800px; margin:0 auto">
        <form id="ff" method="post">
            <table cellpadding="5">
                <tr>
                    <td>动物类别:</td>
                    <td>
                        <input id="kemu"  class="easyui-combobox" name="language" style="width:200px;"
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

                    <td>动物名称:</td>
                    <td>
                        <input id="cc2" name="name" class="easyui-combobox"  data-options="method:'post',valueField:'id',textField:'text'"/>

                    </td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">删除</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
 </div>
<script>
    function submitForm(){
        $('#ff').form('submit', {
                url:"${ctx}/queryClass/deleteAnimalMethod",
                onSubmit: function(param){
                   var name= $('#cc2').combobox('getValue');
                  //  param.name =name;//传给后台参数
                    if(name==null||name==undefined||name==""){
                        alert("请选择动物名称！");
                        return false;
                    }
                    //提交前触发，返回 false 来阻止提交动作。 这里可以做一下判断之类的
        },
            //获取后台传来的json
        success:function(data){
            data=JSON.parse(data);//将字符串json转成对象json
            alert(data.result)
        }
    });
    }
    function clearForm(){
        $('#ff').form('clear');
    }
</script>
</body>
</html>
