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
    <link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

</head>
<body id="userBody">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

    <div class="text-c">
        <div class="row cl ">
            <div class="formControls col-4">
                用户名称: <input type="text" class="input-text" style="width: 250px" id="userName"
                             value="${sysUser.userName}">
            </div>
            是否有效:
            <span class="select-box" style="width:150px">
              <select name="brandclass" class="select" size="1" id="flag">
                     <c:choose>
                         <c:when test="${sysUser.flag}">
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

    <div class="cl pd-5 bg-1 bk-gray">
        <span class="l">
            <a href="javascript:;" onclick="batchDel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;
            </i>
                批量删除</a>
            <a class="btn btn-primary radius" href="javascript:;"
               onclick="admin_user_add('添加用户','sysUser/toAdd','800')"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a>
        </span> <span class="r">共有数据：<strong>54</strong> 条</span>
    </div>
    <table class="table table-border table-bordered table-hover table-bg">
        <thead>
        <tr>
            <th scope="col" colspan="8">用户管理</th>
        </tr>
        <tr class="text-c">
            <th width="25"><input type="checkbox" class="delBox" value="" name=""></th>
            <th width="40">用户ID</th>
            <th width="100">用户名称</th>
            <th width="100">电话</th>
            <th width="100">邮箱</th>
            <th width="100">生日</th>
            <th width="200">个人简介</th>
            <th width="70">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="sysUser">
            <tr class="text-c">
                <td><input type="checkbox" class="delBox" value="${sysUser.userId}" name=""></td>
                <td>${sysUser.userId}</td>
                <td>${sysUser.userName}</td>
                <td>${sysUser.phone}</td>
                <td>${sysUser.email}</td>
                <td>
                    <fmt:formatDate value="${sysUser.birthday}" pattern="yyyy-MM-dd"></fmt:formatDate>
                </td>
                <td>${sysUser.introduce}</td>
                <td class="f-14">
                    <a title="编辑" href="javascript:;"
                       onclick="admin_user_edit('用户编辑','sysUser/toUpdate?userId=${sysUser.userId}','1')"
                       style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                    <a title="删除" href="javascript:;" onclick="admin_user_del(this,${sysUser.userId})"
                       class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--装展示分页导航的容器--%>
    <jsp:include page="/application/common/page.jsp">
        <jsp:param name="bodyId" value="userBody"/>
    </jsp:include>

</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>

<script type="text/javascript">
    /*管理员-用户-添加*/
    function admin_user_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-用户-编辑*/
    function admin_user_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*管理员-用户-删除*/
    function admin_user_del(obj,id){
        layer.confirm('用户删除须谨慎，确认要删除吗？',function(index){
            //此处请求后台程序，下方是成功后的前台处理……
            //发送ajax请求去删除该条数据
          $.ajax({
              url:"sysUser/delete",
              type:"POST",
              data:"userId="+id,
              success:function (data) {
                  if(data.result){
                      $(obj).parents("tr").remove();
                      layer.msg("已删除",{icon:1,time:2000});
                  }else{
                      layer.msg("删除失败",{icon:2,time:2000});
                  }
              }
          })
        });
    }

    /* 批量删除 */
    function batchDel() {
        layer.confirm("用户删除需谨慎，确认要删除吗？",function (index) {
            var checkes = $(".delBox:checked");
            if(checkes.length == 0){
                layer.msg("您没有选择删除的数据！",{icon:2,time:2000});
                return;
            }
            //得到一组Id，把它们装起来
            var ids = [];
            for (var i = 0; i< checkes.length; i++){
                ids.push(checkes[i].value);
            }
            //发送Ajax请求去后端做一个批量修改的操作
            $.ajax({
                url:"sysUser/batchDel",
                type:"POST",
                data:"idList="+ids,
                success:function (data) {
                    //根据返回标识给出相应的提示
                    if(data.result){
                        layer.msg("已删除",{icon:1,time:2000},function () {
                            location.reload();
                        })
                    }else {
                        layer.msg("删除失败!",{icon:2,time:2000});
                    }
                }
            })
        })
    }

    /*
    通过条件查询分页信息
     */
    function selectByCondition() {
        var userName = $("#userName").val();
        var flag = $("#flag").val();

        //加载分页的集合数据，在body中展示
        $("#userBody").load("sysUser/selectByCondition",{"userName":userName,"flag":flag})
    }
</script>
</body>
</html>
