<#assign ctx="${request.getContextPath()}">
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>palmer项目管理系统PMS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="palmer.com pms">
    <meta name="author" content="itwarcraft@gmail.com">

    <!-- Le styles -->
    <link href="${ctx}/assets/js/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/assets/js/bootstrap/css/theme-google.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 50px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
        /**text-align:center;居中显示，只在IE下起作用*/
      }
      /**保存成功还是失败的提醒窗口*/
	  .msg-p{
      	width:200px;
      	margin:0 auto;
      	margin-top:200px;
      }
    </style>
    <link href="${ctx}/assets/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${ctx}/assets/css/theme.css" rel="stylesheet">
	
	<link href="${ctx}/assets/js/fullcalendar/fullcalendar.css" rel="stylesheet">
	<link href="${ctx}/assets/js/fullcalendar/fullcalendar.print.css" rel="stylesheet">
	
	<script src="${ctx}/assets/js/jquery-1.8.3.min.js"></script>	

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="${ctx}/assets/js/bootstrap/js/html5shiv.js"></script>
    <![endif]-->
	

    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="${ctx}/assets/ico/favicon.png">
  </head>

  <body>