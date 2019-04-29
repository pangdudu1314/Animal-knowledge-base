<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/8 0008
  Time: 下午 4:18
  To change this template use File | Settings | File Templates.
--%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <base href="<%=basePath%>">
    <title>ECharts</title>
    <style type="text/css">
        body{
          /*  background: url("images/beijingtupian.jpg");*/
            background-color: #ccc;
        }
        #main{
            /*background: url("images/beijingtupian.jpg");*/
            width: 1300px;
            height:400px;
            float:left;
            background-color: #ccc;
        }
        *{
            margin:0;
            padding:0;
        }
        #submitView1{
            width:50px;
            height:37px;
            border: solid 1px #4791ff;
            background-color: #3385ff;
            color: #fff;
            font-size: 15px;
            cursor: pointer;
            margin-right: 40px;
        }
        #submitView1:HOVER {
            background-color: #317ef3;/*//鼠标移动过去时，背景颜色发生变化*/
        }
        input{
            float:left;
        }
    </style>

</head>
<body  style="width:1300px;">
<center>
    <h1 style="color:#0000FF">动物领域多模态知识库的查询系统</h1>
</center>

<div id="main" style="width: 100%;height:100%;xiangdu"></div>
<script src="js/echarts.js"></script>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
    // 路径配置
    require.config({
        paths: {
            echarts: 'dist'
        }
    });

    // 使用
    require(
        [
            'echarts',
            'echarts/chart/force' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));
            option = {
                
                title : {
                    text: '动物关系：${name}',
                    subtext: '数据来自百度百科',
                    x:'right',
                    y:'bottom'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: '{a} : {b}'
                },
                toolbox: {
                    show : true,
                    feature : {
                        restore : {show: true},
                        magicType: {show: true, type: ['force', 'chord']},
                        saveAsImage : {show: true}
                    }
                },
                legend: {
                    x: 'left',
                    data:['种类']
                },
                series : [
                    {
                        type:'force',
                        name : "动物关系",
                        ribbonType: false,
                        categories : [
                            {
                                name: '动物'
                            },
                            {
                                name: '种类',
                                symbol: 'diamond'
                            },


                        ],
                        shapeType:'ellipse',
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    textStyle: {
                                        color: '#333333',
                                        align: 'center',
                                        baseline: 'bottom'
                                    }

                                },
                                nodeStyle : {
                                    brushType : 'both',
                                    borderColor : 'rgba(255,215,0,0.4)',
                                    borderWidth : 1
                                },
                                linkStyle: {
                                    type: 'line',
                                    width: 3.5,
                                    color:"green"
                                }
                            },
                            emphasis: {
                                label: {
                                    show: false,
                                   // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                                },
                                nodeStyle : {
                                   // r: 30
                                },
                                linkStyle : {}
                            }
                        },
                        useWorker: false,
                        minRadius : 30,
                        maxRadius : 50,
                        gravity: 1.1,
                        scaling: 1.1,
                        roam: 'move',
                       /* roam:'scale',*/
                        nodes:${nodes},
                        links : ${links}
                    }
                ]
            };
            var ecConfig = require('echarts/config');

            function focus(param) {
                var data = param.data;//点击的图片信息
                var links = option.series[0].links;
                var nodes = option.series[0].nodes;




                if ( data.source !== undefined && data.target !== undefined) { //点击的是边
                    var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
                    var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
                    console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
                }

                else  {
                    // $.post("http://localhost:8888/tuxiangzhanshi/servlet", {
                    //     username: data.name, sub: "提交"
                    // } ).success(function (data) {
                    //     $(document).find("html").html(data);
                    // });
                    // console.log("选中了" + data.name + '(' + data.value + ')');
                    //跳转到后台指定方法，或者前端页面，都可以这么写
                    window.location.href="${pageContext.request.contextPath}/queryClass/selectAdmin?name="+data.name;//取出名称进行查询

                }

            }
          /*  myChart.on(ecConfig.EVENT.CLICK, focus);
            myChart.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChart.chart.force.getPosition());
            });*/
            //两个参数  ，第一个是监听的事件类别，第二个是实现方法名称
            myChart.on(ecConfig.EVENT.CLICK, focus)

            myChart.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
                console.log(myChart.chart.force.getPosition());
            });
            myChart.setOption(option);
        }
    );</script>


</body>
</html>
