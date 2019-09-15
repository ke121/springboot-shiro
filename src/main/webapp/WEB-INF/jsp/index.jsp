<%--
  Created by IntelliJ IDEA.
  User: tom
  Date: 2019/8/8
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="../../common/taglib/taglib.jsp" %>
<html>
<head>
    <title>hello</title>
    <script type="text/javascript" src="${baseURL}/easyui/jquery3.3.1.js"></script>
    <%--引入easyui--%>
    <script type="text/javascript" src="${baseURL}/easyui/jquery.easyui.min.js"></script>
    <%--支持中文--%>
    <script type="text/javascript" src="${baseURL}/easyui/locale/easyui-lang-zh_CN.js"></script>
    <%--引入easyui样式--%>
    <link type="text/css" rel="stylesheet" href="${baseURL}/easyui/themes/default/easyui.css"/>
    <%--图标样式--%>
    <link type="text/css" rel="stylesheet" href="${baseURL}/easyui/themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="${baseURL}/css/home.css" />
</head>
<body>
<script type="text/javascript">
    $(function () {
        $('.panel-title').click(function () {
            window.location.href="${baseURL}/index" ;
        })

        function addTab(tabsId, title, url) {
            if($('#tt').tabs('exists', title)) {
                $('#tt').tabs('close', title);
            }
            $('#tt').tabs('add', {
                title: title,
                href: url,
                closable: true
            })
        }
        // 监听右键事件，创建右键菜单
        $('#tt').tabs({
            onContextMenu : function(e, title, index) {
                e.preventDefault();
                //if (index > 0) {
                $('#mm').menu('show', {
                    left : e.pageX,
                    top : e.pageY
                }).data("tabTitle", title);
                //}
            }
        });
        // 右键菜单click
        $("#mm").menu({
            onClick : function(item) {
                closeTab(this, item.name);
            }
        });


// 删除Tabs
        function closeTab(menu, type) {
            var obj = $('#tt');
            ;
            var allTabs = obj.tabs('tabs');
            var allTabtitle = [];
            $.each(allTabs, function(i, n) {
                var opt = $(n).panel('options');
                if (opt.closable) {
                    allTabtitle.push(opt.title);
                }
            });
            var curTabTitle = $(menu).data('tabTitle');

//	console.log('curTabTitle:' + curTabTitle)

            var curTabIndex = obj.tabs('getTabIndex', obj.tabs('getTab', curTabTitle));
            /**
             * <pre>
             * 	    1:关闭标签页
             * 	    2:关闭全部标签页
             * 	    3:关闭其它标签页
             * 	    4:关闭右侧标签页
             * 	    5:关闭左侧标签页
             * </pre>
             */
            switch (type) {
                case '1':
                    obj.tabs('close', curTabTitle);
                    break;
                case '2':
                    for (var i = 0, j = allTabtitle.length; i < j; i++) {
                        obj.tabs('close', allTabtitle[i]);
                    }
                    break;
                case '3':
                    for (var i = 0, j = allTabtitle.length; i < j; i++) {
                        if (curTabTitle != allTabtitle[i]) {
                            obj.tabs('close', allTabtitle[i]);
                        }
                    }
                    obj.tabs('select', curTabTitle);
                    break;
                case '4':
                    for (var i = curTabIndex + 1 ,j = allTabtitle.length; i<j; i++) {
                        obj.tabs('close', allTabtitle[i]);
                    }
                    obj.tabs('select', curTabTitle);
                    break;
                case '5':
                    for (var i = 0; i < curTabIndex; i++) {
                        obj.tabs('close', allTabtitle[i]);
                    }
                    obj.tabs('select', curTabTitle);
                    break;
            }

        }
        for(var i =0;i<(${tree}).length;i++){
            var selected = false;
            if (i == 0) {
                selected = true;
            }
            var node = ${tree}[i] ;
            $('#aa').accordion('add',{
                title:node.text,
                content: "<ul id='menu_tree_" + node.id + "'></ul>",
                selected: selected
            });
            // 树形菜单
            $('#menu_tree_' + node.id).tree({
                data: node.children,
                onClick: function(node) {
                    // 添加选项卡
                    addTab('tt', node.text, node.url);
                }
            });
        }

    })
</script>
<div id="cc" class="easyui-layout" style="width: 1520px;height:730px;">
    <div data-options="region:'north',collapsible:false" style="height:53px;background: url('./images/header_bg.png') repeat-x">
            <div style="font-size: 20px;margin-top: 20px;color: #fff;float: left">
                easyui后台管理系统
            </div>
            <div style="color: #A6CBFD; font-size:14px;float: left; margin-top: 28px;margin-left: 10px">
                ---------欢迎登陆-${user.username}
            </div>
            <div style="float: right;margin-top: 20px;font-size: 12px">
                <a href="${baseURL }/editPwdUI" rel="xgmm" target="dialog" width="550" height="300" style="color: #fff;">修改密码</a>
            </div>
            <div style="float: right;margin-top: 20px;margin-right: 10px;font-size: 12px">
                <a href="${baseURL }/logout" title="退出登录确认" target="dialog" width="300" height="200" style="color:#fff;">退出系统</a>
            </div>
    </div>
    <div data-options="region:'south',collapsible:false" style="height:50px;">
        <div id="footer">Copyright &copy; 2015-2019</div>
    </div>
    <div data-options="region:'west',title:'系统菜单',split:true" style="width:200px;">
        <div id="aa" class="easyui-accordion" style="width:100%;height:100%;">
        </div>
    </div>
    <div id="et" data-options="region:'center',title:'回到首页'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
            <div title="首页">
                欢迎使用xxx系统
            </div>
        </div>
    </div>
    <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose" name="1">关闭标签页</div>
        <div id="mm-tabcloseall" name="2">关闭全部标签页</div>
        <div id="mm-tabcloseother" name="3">关闭其它标签页</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright" name="4">关闭右侧标签页</div>
        <div id="mm-tabcloseleft" name="5">关闭左侧标签页</div>
    </div>
</div>
</body>
</html>
