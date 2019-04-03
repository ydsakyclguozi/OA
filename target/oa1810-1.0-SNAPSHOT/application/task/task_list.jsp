<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/3/18
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css"/>

</head>
<body id="orgBody">
<div class="pd-20">
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="6">任务列表信息</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" value="" name=""></th>
            <th width="40">任务ID</th>
            <th width="100">任务名称</th>
            <th width="100">流程实例ID</th>
            <th width="100">创建时间</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${taskList}" var="task">
            <tr class="text-c">
                <td><input type="checkbox" class="delBox" value="" name=""></td>
                <td>${task.id}</td>
                <td>${task.name}</td>
                <td>${task.processInstanceId}</td>
                <td>
                    <fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>

                <td class="f-14">
                    <a title="处理" href="javascript:;"
                       onclick="admin_purchase_edit('处理任务','purchase/toApproval?taskId=${task.id}','1')"
                       style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">

    function admin_purchase_edit(title, url, id, w, h) {
        layer_show(title, url, w, h);
    }


</script>
</body>
</html>
