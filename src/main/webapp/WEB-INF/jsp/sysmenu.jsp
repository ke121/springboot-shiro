<%--jquery
  Created by IntelliJ IDEA.
  User: tom
  Date: 2019/8/19
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/taglib/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
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
<script type="text/javascript">

    $(function () {
        $("#win").dialog({
            onClose:function () {
                $('#parentMenu').textbox('clear') ;
                $('#menuRemarker').textbox('clear') ;
                $('#requestUrl').textbox('clear') ;
                $('#menuName').textbox('clear') ;
                $('#parentId').textbox('clear') ;
            }
        })
        $("#addmenu").click(function () {

            $('#win').window('open')
            var node = ($("#menu-tree").tree('getSelected'));
            $('#parentMenu').textbox('setText',node.text) ;
            $('#parentMenu').textbox('setValue',node.text) ;
            $('#parentId').textbox('setText',node.id) ;
            $('#parentId').textbox('setValue',node.id) ;
        })

        $("#win").dialog(
            {
                buttons:[{
                    size:12,
                    text:'保存',
                    handler:function(){
                        $('#ff').submit();
                    }
                },{
                    text:'取消',
                    handler:function(){
                       $("#win").window('close')
                    }
                }]
            }
        )
        $("#updatewin").dialog(
            {
                buttons:[{
                    size:12,
                    text:'保存',
                    handler:function(){
                        $('#updateff').submit();
                    }
                },{
                    text:'取消',
                    handler:function(){
                        $("#updatewin").window('close')
                    }
                }]
            }
        )

        //加载树
        $('#menu-tree').tree({
            data:${systree},
            onSelect: function (node) {
                if (node.state !== "open") {
                    $('#menu-tree').tree('expand', node.target);
                    var tab = $('#tt').tabs('getSelected');  // get selected panel
                    $('#tt').tabs('update', {
                        tab: tab,
                        options: {
                            title: tab.title,
                            href: '${baseURL}/sys/menu'  // the new content URL
                        }
                    });
                    //加载表格
                    $('#dg').datagrid({
                        singleSelect:true,
                        url: '${baseURL}/pms/menu/list?parentId=' + node.id,
                        onLoadSuccess:function(data){
                            $('#dg').datagrid('selectRow', 0);
                        },
                        columns: [[
                            {field: 'id', title: '菜单编号', width: 150},
                            {field: 'text', title: '菜单名称', width: 250},
                            {field: 'url', title: '请求url', width: 250},
                            {field: 'state', title: '菜单状态', width: 200},
                            {
                                field: 'Operation', title: '操作', width: 120,
                                formatter: function (value, row, index) {
                                    var e = '<a href="javascript:void(0)" id="deitcls" onclick="PorEdit(' + index + ')"><img src="${baseURL}/easyui/themes/icons/pencil.png" border="0"></a> ';
                                    var d = '<a href="javascript:void(0)" onclick="Delete(' + index + ')"><img src="${baseURL}/easyui/themes/icons/cancel.png"></a>';
                                    return e + d;
                                }
                            }

                        ]],
                    });

// call 'refresh' method for tab panel to update its content
//                     var tab = $('#tt').tabs('getSelected');  // get selected panel
//                     tab.panel('refresh', 'get_content.php');
                } else {
                    $('#menu-tree').tree('collapse', node.target);
                    //加载表格
                    $('#dg').datagrid({
                        url: '${baseURL}/pms/menu/list?parentId=' + node.id,
                        singleSelect:true,
                        onLoadSuccess:function(data){
                            $('#dg').datagrid('selectRow', 0);
                        },
                        columns: [[
                            {field: 'id', title: '菜单编号', width: 150},
                            {field: 'text', title: '菜单名称', width: 250},
                            {field: 'url', title: '请求url', width: 250},
                            {field: 'state', title: '菜单状态', width: 200},
                            {
                                field: 'Operation', title: '操作', width: 120,
                                formatter: function (value, row, index) {
                                    var e = '<a href="javascript:void(0)" id="deitcls" onclick="PorEdit(' + index + ')"><img src="${baseURL}/easyui/themes/icons/pencil.png" border="0"></a> ';
                                    var d = '<a href="javascript:void(0)" onclick="Delete(' + index + ')"><img src="${baseURL}/easyui/themes/icons/cancel.png"></a>';
                                    return e + d;
                                }
                            }

                        ]],
                    });
                }

            }
        })

        function PorEdit(id){
            $("#updatewin").window('open') ;
            var node = ($("#menu-tree").tree('getSelected'));
            $('#uparentMenu').textbox('setText',node.text) ;
            $('#uparentMenu').textbox('setValue',node.text) ;
            $('#uparentId').textbox('setText',node.id) ;
            $('#uparentId').textbox('setValue',node.id) ;
            $('#dg').datagrid('selectRow', id);
            var dg = $("#dg").datagrid('getSelected') ;
            $('#uid').textbox('setText',dg.id) ;
            $('#uid').textbox('setValue',dg.id) ;
            $('#umenuRemarker').textbox('setText',dg.remarker) ;
            $('#umenuRemarker').textbox('setValue',dg.remarker) ;
            $('#umenuName').textbox('setText',dg.text) ;
            $('#umenuName').textbox('setValue',dg.text) ;
            $('#urequestUrl').textbox('setText',dg.url) ;
            $('#urequestUrl').textbox('setValue',dg.url) ;
            console.log($("#dg").datagrid('getSelected')) ;
        }

        function Delete(id){
            $.messager.confirm('确认删除', '确定要删除该菜单吗', function(r){
                if (r){
                    $('#dg').datagrid('selectRow', id);
                    var dg = $("#dg").datagrid('getSelected') ;

                }
            });


        }

        window.PorEdit = PorEdit;
        window.Delete = Delete;


        $("#refresh").click(function () {
            //获得当前选中的tab
            var tab = $('#tt').tabs('getSelected');
            //获得当前选中的tab 的href
            var url = $(tab.panel('options')).attr('href');
            tab.panel('refresh', url);
            <%--var node = $('#menu-tree').tree('getRoots');--%>
            <%--$('#menu-tree').tree('collapseAll', node.target);--%>
            <%--var tab = $('#tt').tabs('getSelected');  // get selected panel--%>
            <%--$('#tt').tabs('update', {--%>
                <%--tab: tab,--%>
                <%--options: {--%>
                    <%--title: tab.title,--%>
                    <%--href: '${baseURL}/sys/menu'  // the new content URL--%>
                <%--}--%>
            <%--});--%>

            <%--//加载表格--%>
            <%--$('#dg').datagrid({--%>
                <%--url: '${baseURL}/pms/menu/list?parentId=0',--%>
                <%--columns: [[--%>
                    <%--{field: 'id', title: '菜单编号', width: 150},--%>
                    <%--{field: 'text', title: '菜单名称', width: 250},--%>
                    <%--{field: 'url', title: '请求url', width: 250},--%>
                    <%--{field: 'state', title: '菜单状态', width: 200},--%>
                    <%--{--%>
                        <%--field: 'Operation', title: '操作', width: 120,--%>
                        <%--formatter: function (value, row, index) {--%>
                            <%--var e = '<a href="#" id="deitcls" οnclick="PorEdit(' + index + ')"><img src="${baseURL}/easyui/themes/icons/pencil.png" border="0"></a> ';--%>
                            <%--var d = '<a href="#" οnclick="Delete(' + index + ')"><img src="${baseURL}/easyui/themes/icons/cancel.png"></a>';--%>
                            <%--return e + d;--%>
                        <%--}--%>
                    <%--}--%>

                <%--]],--%>
            <%--});--%>
        });
        $('#ff').form({
            url:"${baseURL}/pms/menu/addmenu",
            onSubmit: function(){
                // do some check
                // return false to prevent submit;
                return $(this).form('validate');
            },
            success:function(data){
                var node = ($("#menu-tree").tree('getSelected'));
                //加载表格
                $('#dg').datagrid({
                    url: '${baseURL}/pms/menu/list?parentId=' + node.id,
                    singleSelect:true,
                    onLoadSuccess:function(data){
                        $('#dg').datagrid('selectRow', 0);
                    },
                    columns: [[
                        {field: 'id', title: '菜单编号', width: 150},
                        {field: 'text', title: '菜单名称', width: 250},
                        {field: 'url', title: '请求url', width: 250},
                        {field: 'state', title: '菜单状态', width: 200},
                        {
                            field: 'Operation', title: '操作', width: 120,
                            formatter: function (value, row, index) {
                                var e = '<a href="#" id="deitcls" onclick="PorEdit(' + index + ')"><img src="${baseURL}/easyui/themes/icons/pencil.png" border="0"></a> ';
                                var d = '<a href="#" onclick="Delete(' + index + ')"><img src="${baseURL}/easyui/themes/icons/cancel.png"></a>';
                                return e + d;
                            }
                        }

                    ]],
                });
                $('#win').window('close');
                $.messager.show({
                    title:'成功',
                    height:100,
                    width:250,
                    msg:'操作成功',
                    showType:'show',
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
            }
        });

        $('#updateff').form({
            url:"${baseURL}/pms/menu/updatemenu",
            onSubmit: function(){
                // do some check
                // return false to prevent submit;
                return $(this).form('validate');
            },
            success:function(data){
                var node = ($("#menu-tree").tree('getSelected'));
                //加载表格
                $('#dg').datagrid({
                    url: '${baseURL}/pms/menu/list?parentId=' + node.id,
                    singleSelect:true,
                    onLoadSuccess:function(data){
                        $('#dg').datagrid('selectRow', 0);
                    },
                    columns: [[
                        {field: 'id', title: '菜单编号', width: 150},
                        {field: 'text', title: '菜单名称', width: 250},
                        {field: 'url', title: '请求url', width: 250},
                        {field: 'state', title: '菜单状态', width: 200},
                        {
                            field: 'Operation', title: '操作', width: 120,
                            formatter: function (value, row, index) {
                                var e = '<a href="#" id="deitcls" onclick="PorEdit(' + index + ')"><img src="${baseURL}/easyui/themes/icons/pencil.png" border="0"></a> ';
                                var d = '<a href="#" onclick="Delete(' + index + ')"><img src="${baseURL}/easyui/themes/icons/cancel.png"></a>';
                                return e + d;
                            }
                        }

                    ]],
                });

                $('#updatewin').window('close');
                $.messager.show({
                    title:'成功',
                    height:100,
                    width:250,
                    msg:'操作成功',
                    showType:'show',
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
            }
        });
    })
</script>
<div style="float: left;width: 300px;">
    <div class="panel-header" style="width:100%;height: 5%;">
        <a id="refresh" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" style="height: 100%">刷新</a>
    </div>
    <div style="">
        <div style="width: 100%;">
            <ul id="menu-tree"></ul>
        </div>
    </div>
</div>

<div id="p" class="easyui-panel"
     title='<a id="addmenu"  href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" style="height: 100%">添加</a>'
     style="height:100%;background:#fafafa;">
    <table id="dg"></table>
    <div id="win" closed="true" class="easyui-dialog" style="width:600px;height:480px;" title="添加菜单"
         data-options="buttons:'#bb',">
        <form style="padding:10px 20px 10px 40px;" id="ff">
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">上级菜单</label>
                <input class="easyui-textbox" style="width:200px;height: 22px;font-size: 12px" name="parentMenu" readonly id="parentMenu" /><br/>
            </div>
            <input class="easyui-textbox" type="hidden" name="parentId" id="parentId">
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">菜单描述</label>
                <input class="easyui-textbox" style="width:200px;height: 22px;;font-size: 12px" name="menuRemarker" id="menuRemarker"><br/>
            </div>
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">菜单名称</label>
                <input class="easyui-textbox easyui-validatebox" style="width:200px;height: 22px;;font-size: 12px" name="menuName" id="menuName" data-options="required:true" ><br/>
            </div>
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 15px; text-align: right;font-size: 12px">请求url&nbsp;</label><input
                    class="easyui-textbox easyui-validatebox" style="width:200px;height: 22px;;font-size: 12px" name="requestUrl" id="requestUrl" data-options="required:true"><br/>
            </div>
        </form>
    </div>

    <div id="updatewin" closed="true" class="easyui-dialog" style="width:600px;height:480px;" title="编辑菜单"
         data-options="buttons:'#bb',">
        <form style="padding:10px 20px 10px 40px;" id="updateff">
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">上级菜单</label>
                <input class="easyui-textbox" style="width:200px;height: 22px;font-size: 12px" name="parentMenu" readonly id="uparentMenu" /><br/>
            </div>
            <input class="easyui-textbox" type="hidden" name="parentId" id="uparentId">
            <input class="easyui-textbox" type="hidden" name="id" id="uid">
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">菜单描述</label>
                <input class="easyui-textbox" style="width:200px;height: 22px;;font-size: 12px" name="menuRemarker" id="umenuRemarker"><br/>
            </div>
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 1em; text-align: right;font-size: 12px">菜单名称</label>
                <input class="easyui-textbox easyui-validatebox" style="width:200px;height: 22px;;font-size: 12px" name="menuName" id="umenuName" data-options="required:true" ><br/>
            </div>
            <div style="margin-top: 20px">
                <label style="display:inline-block; width: 100px; margin-right: 15px; text-align: right;font-size: 12px">请求url&nbsp;</label><input
                    class="easyui-textbox easyui-validatebox" style="width:200px;height: 22px;;font-size: 12px" name="requestUrl" id="urequestUrl" data-options="required:true"><br/>
            </div>
        </form>
    </div>

    <%--<div id="bb">--%>
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" size="small"><span style="font-size: 12px">保存</span></a>--%>
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" size="small"><span style="font-size: 12px">取消</span></a>--%>
    <%--</div>--%>
</div>


</body>
</html>
