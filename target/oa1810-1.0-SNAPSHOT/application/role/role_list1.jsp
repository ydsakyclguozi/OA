<%--
  Created by IntelliJ IDEA.
  User: Misrer Wei
  Date: 2019/3/22
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <title>角色管理</title>
</head>
<body id="roleBody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 授权管理 <span
        class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-4">
                角色名称: <input type="text" class="input-text" style="width: 250px" id="roleName"
                             value="${sysRole.roleName}">
            </div>
            <div class="formControls col-4">
                角色描述： <input type="text" class="input-text" style="width: 250px" id="roleDesc"
                             value="${sysRole.roleDesc}">
            </div>
            是否有效:
            <span class="select-box" style="width:150px">
              <select name="brandclass" class="select" size="1" id="flag">
                    <%--<option value="1" selected="selected">是</option>
                    <option value="0">否</option>--%>
                <c:choose>
                    <c:when test="${sysRole.flag}">
                        <option value="1" selected="selected">是</option>
                        <option value="0">否</option>
                    </c:when>
                    <c:otherwise>
                        <option value="1" >是</option>
                        <option value="0" selected="selected">否</option>
                    </c:otherwise>
                </c:choose>
              </select>
			</span>
        </div>
        <div class="row cl">
            <div class="cl pd-5">
                <button type="button"
                        class="btn btn-success radius" id="" name="" onclick="selectByCondition()">
                    <i class="Hui-iconfont">&#xe665;</i> 搜索
                </button>
            </div>
        </div>
    </div>

    <div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加角色','admin-role-add.html','800')"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="6">角色管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">ID</th>
            <th width="150">角色名</th>
            <th width="300">角色描述</th>
            <th width="100">创建时间</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysRole">
            <tr class="text-c">
                <td><input type="checkbox" value="" name=""></td>
                <td>${sysRole.roleId}</td>
                <td>${sysRole.roleName}</td>
                <td>${sysRole.roleDesc}</td>
                <td>
                    <fmt:formatDate value="${sysRole.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td class="f-14"><a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','admin-role-add.html','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_role_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
            </tr>
        </c:forEach>
        <
        </tbody>
    </table>

    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="roleBody"></jsp:param>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">
    /*管理员-角色-添加*/
    function admin_role_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-角色-编辑*/
    function admin_role_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-角色-删除*/
    function admin_role_del(obj,id){
        layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……


            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }
</script>
</body>
</html>
