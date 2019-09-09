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
    <script type="text/javascript" src="${baseURL}/easyui/jquery3.3.1.js"></script>
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
    <%--<form action="${baseURL}/tologin" method="post">--%>
        <%--<label>账号: <input name="username" type="text" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px"></label>--%>
        <%--<label>密码: <input name="password" type="password" class="easyui-textbox" data-options="iconCls:'icon-search'" style="width:300px"></label>--%>
        <%--<input type="submit" name="提交">登陆--%>
    <%--</form>--%>
    <div style="margin-left: 500px;margin-top: 200px">
    <div class="easyui-panel" title="登陆" style="width:100%;max-width:400px;padding:30px 60px;">
        <form id="loginform" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="username" style="width:100%" data-options="label:'username:',required:true">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" type="password" name="password" style="width:100%" data-options="label:'password:',required:true,validType:'password'">
            </div>
            <div style="text-align:center;padding:5px 0">
                <input  class="easyui-linkbutton" type="submit" style="width:80px"></input>
            </div>
        </form>
    </div>

    </div>

</body>
</html>
