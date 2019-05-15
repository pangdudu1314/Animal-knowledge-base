<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/12 0012
  Time: 下午 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui-1.7.0/demo/demo.css">
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="动物列表" style="width:100%;height:500px"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${ctx}/animalCheck/getAminalCheck',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'animalName',width:100">动物名称</th>
        <th data-options="field:'dataFrom',width:80,align:'right'">数据来源</th>
        <th data-options="field:'status',width:80,align:'right'">状态</th>
        <th data-options="field:'photo',width:80,height:80,formatter:_showPhoto">动物图片</th>
        <th data-options="field:'animalIntro',width:600">动物介绍</th>
        <th data-options="field:'id',width:240,formatter:opAnimal">操作</th>
    </tr>
    </thead>
</table>

<div id="dlg" class="easyui-dialog" data-options="closed:true" title="动物信息"  style="width:800px;height:600px;padding:10px">
    <form id="ff" method="post">
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="animalName" style="width:100%" data-options="label:'动物名称:',required:true,editable:false">
        </div>
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="animalIntro" style="width:100%" data-options="label:'描述:',required:true,editable:false">
        </div>
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="dataFrom" style="width:100%" data-options="label:'数据来源:',required:true,editable:false">
        </div>
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="status" style="width:100%" data-options="label:'状态:',required:true,editable:false">
        </div>
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="kemu" style="width:100%" data-options="label:'所属科目:',required:true,prompt:'请从动物开始，用中划线分割动物层级。例如:动物-鸟类-稚科'">
        </div>
        <div style="margin-bottom:10px">
            <img id="imageshow" height="200" width="200">
        </div>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">保存</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">取消</a>
        </div>
    </form>
</div>

<script>
    var clickId="";
function opAnimal(val,row){
  if(row.status=="未处理"){
    if(row.dataFrom=="图片识图"){
      return "<a href=\"javascript:void(0);\" onclick=\"editAnimal('"+val+"');\">编辑</a>\n";
    }else{

    }
  }

}
function editAnimal(id){
  clickId=id;
  $('#dlg').dialog('open')

var rows=$("#dg").datagrid("getRows");
  for(var i=0;i<rows.length;i++){
    if(id==rows[i].id){
      $('#ff').form('load',rows[i]);
      $("#imageshow").attr("src","${ctx}/"+rows[i].animalImage);
    }
  }

}
    function submitForm(){
      $('#ff').form('submit',{
            url:"${ctx}/animalCheck/updateCheckAnimal?id="+clickId,

          onSubmit: function(){

          },
      success:function(data){
        alert("保存成功")
        //关闭对话框
        $('#dlg').dialog('close')
        //重新加载数据
        $('#dg').datagrid('reload');
      }
    });
    }

    function _showPhoto(value,rows, index) {
        if(value){
            for(var i=0;i<rows.length;i++){
                if(id==rows[i].id){

                    return "<img src='"+rows[i].animalImage+"'style='width:80px;height:80px'";
                }
            }

        }else {
            return null
        }
    }
    function clearForm(){
      $('#ff').form('clear');
    }
</script>
</body>
</html>
