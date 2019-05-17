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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <base href="<%=basePath%>">
    <title>动物体系图</title>
    <style type="text/css">
        body {
            /*  background: url("images/beijingtupian.jpg");*/
            background-color: #ccc;
        }

        #main {
            /*background: url("images/beijingtupian.jpg");*/
            width: 1300px;
            height: 400px;
            float: left;
            background-color: #ccc;
        }

        * {
            margin: 0;
            padding: 0;
        }

        #submitView1 {
            width: 50px;
            height: 37px;
            border: solid 1px #4791ff;
            background-color: #3385ff;
            color: #fff;
            font-size: 15px;
            cursor: pointer;
            margin-right: 40px;
        }

        #submitView1:HOVER {
            background-color: #317ef3; /*//鼠标移动过去时，背景颜色发生变化*/
        }

        input {
            float: left;
        }
    </style>

</head>
<body style="width:1300px;">
<div id="main" style="width: 100%;height:100%;"></div>
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
            'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main'));

            option = {
                title: {
                    text: '动物体系图'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '树图',
                        type: 'tree',
                        orient: 'horizontal',  // vertical horizontal
                        rootLocation: {x: 200, y: 200}, // 根节点位置  {x: 100, y: 'center'}
                        nodePadding: 12,
                        layerPadding: 200,
                        hoverable: false,
                        roam: true,
                        symbolSize: 10,
                        itemStyle: {
                            normal: {
                                color: '#4883b4',
                                label: {
                                    show: true,
                                    position: 'right',
                                    formatter: "{b}",
                                    textStyle: {
                                        color: '#000',
                                        fontSize: 10
                                    }
                                },
                                lineStyle: {
                                  // color: '#ccc',
                                    type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                                }
                            },
                            emphasis: {
                                color: '#4883b4',
                                label: {
                                    show: false
                                },
                                borderWidth: 0
                            }
                        },

                        data: [
                            ${animalTree},
                        ]
                    }
                ]
            };
            var ecConfig = require('echarts/config');
            myChart.on(ecConfig.EVENT.CLICK, focus)
            function focus(param) {
               // console.log(param);
                if(param.data.level==2||param.data.level==3){
                    window.location.href="${pageContext.request.contextPath}/queryClass/selectAdmin?name="+param.name;//取出名称进行查询
                }

            }
            myChart.setOption(option);
        }
    );</script>
</body>
</html>
