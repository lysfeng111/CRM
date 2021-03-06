<%@page import="com.rlg.crm.domain.Consumer"%>
<%@page import="com.rlg.crm.domain.Customer"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>客户管理</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                        <% Consumer user=(Consumer)request.getAttribute("user"); %>
                            <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"><%=user.getConName() %></strong></span>
                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="profile.html">个人资料</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="login.html">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <!-- <div class="logo-element">H+
                        </div> -->
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">营销管理</span>
                            <span class="fa arrow"></span>
                        </a>
                         <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="SOM/SaleOpportunity.html" data-index="0">营销机会管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="LGP/LeadGeneration.html">客户开发计划</a>
                            </li>
                        </ul>

                    </li>
                    <!--<li>-->
                        <!--<a class="J_menuItem" href="layouts.html"><i class="fa fa-columns"></i> <span class="nav-label">客户管理</span></a>-->
                    <!--</li>-->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">客户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="customerlist?flag=query">客户信息管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="page/lost.jsp">客户流失管理</a>
                            </li>
                        </ul>
                    </li>
                   <li>
                    <a href="#"><i class="fa fa-table"></i> <span class="nav-label">服务报表</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="initNew.doServe">服务创建</a>
                        </li>
                        <li><a class="J_menuItem" href="queryAllocation.doServe">服务分配</a>
                        </li>
                        <li><a class="J_menuItem" href="queryProcessing.doServe">服务处理</a>
                        </li>
                        <li><a class="J_menuItem" href="queryFeedback.doServe">服务反馈</a>
                        </li>
                        <li><a class="J_menuItem" href="queryArchive.doServe">服务归档</a>
                        </li>
                    </ul>
                </li>
                    <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">统计报表</span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="queryCusContribution.doSta">客户贡献分析</a>
                        </li>
                        <li><a class="J_menuItem" href="statistical_table/cus_composition_analysis.jsp">客户构成分析</a>
                        </li>
                        <li><a class="J_menuItem" href="statistical_table/cus_serve_analysis.jsp">客户服务分析</a>
                        </li>
                        <li><a class="J_menuItem" href="statistical_table/cus_lost_analysis.jsp">客户流失分析</a>
                        </li>
                    </ul>
                </li>
                    <li>
                        <a href="#"><i class="fa fa-flask"></i> <span class="nav-label">基础数据</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                                    <li>
                                        <a class="J_menuItem" href="datamanage/datalist.html">数据字典管理</a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="datamanage/product.html">查询产品信息</a>
                                    </li>
                                    <li>
                                        <a class="J_menuItem" href="datamanage/inventory.html">查询库存</a>
                                    </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <h1 style="margin-left: 4%">客户关系管理系统</h1>
                </nav>
            </div>
         
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="customerlist?flag=query" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">





            </div>
        </div>
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
        <div class="small-chat-box fadeInRight animated">
        </div>
        <div id="small-chat">

        </div>
        <!--mini聊天窗口结束-->
    </div>

    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="js/contabs.js"></script>

    <!-- 第三方插件 -->
    <script src="js/plugins/pace/pace.min.js"></script>

</body>

</html>
