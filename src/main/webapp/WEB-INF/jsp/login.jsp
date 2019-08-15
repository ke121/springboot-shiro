<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 2019/8/8
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib/taglib.jsp"%>
<html>
<head>
    <title>login</title>
    <script type="text/javascript" src="${baseURL}/easyui/jquery.min.js"></script>
    <%--引入easyui--%>
    <script type="text/javascript" src="${baseURL}/easyui/jquery.easyui.min.js"></script>
    <%--支持中文--%>
    <script type="text/javascript" src="${baseURL}/easyui/locale/easyui-lang-zh_CN.js"></script>
    <%--引入easyui样式--%>
    <link type="text/css" rel="stylesheet" href="${baseURL}/easyui/themes/default/easyui.css"/>
    <%--图标样式--%>
    <link type="text/css" rel="stylesheet" href="${baseURL}/easyui/themes/icon.css"/>
</head>
<body>
    <h1>${msg}</h1>
    <form action="${baseURL}/tologin" method="post">
        <label>账号: <input name="username" class="textbox" data-options="iconCls:'icon-search'" style="width:300px"></label>
        <label>密码: <input name="password" class="textbox" data-options="iconCls:'icon-search'" style="width:300px"></label>
        <input type="submit" name="提交">登陆
    </form>
    <h2>Basic Form</h2>
    <p>Fill the form and submit it.</p>
    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="登陆" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="loginform" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="name" style="width:100%" data-options="label:'Name:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" type="password" name="password" style="width:100%" data-options="label:'password:',required:true,validType:'password'">
            </div>
        </form>
        <div style="text-align:center;padding:5px 0">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">Clear</a>
        </div>
    </div>
    <script>
        function submitForm(){
            $('#tologin').form('submit');
        }
        function clearForm(){
            $('#ff').form('clear');
        }
    </script>
</body>
</html>
