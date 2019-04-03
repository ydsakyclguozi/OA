<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2019/3/19
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<base href="<%=request.getContextPath()+"/"%>">
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<%--引入zTree的css--%>
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<head>
    <title>Title</title>
</head>
<body>
<div class="pd-20">
    <form action="" method="post" class="form form-horizontal" id="form-org-update">
        <input type="hidden" name="orgId" value="${sysOrg.orgId}" />
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>组织名称：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="${sysOrg.orgName}" placeholder="" id="orgName" name="orgName"
                       datatype="*2-16"  nullmsg="组织名称不能为空">
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>父组织名称：</label>
            <div class="formControls col-5">
                <input type="text" id="orgParentName" name="orgParentName" value="${sysOrg.orgParentName}" class="input-text"
                       datatype="*2-16" nullmsg="父组织名称不能为空">
                <input type="hidden" id="orgParentId" name="orgParentId" value="${sysOrg.orgParentId}">
                <input class="btn btn-primary radius" type="button" value="选择父组织" onclick="selectOrgParent()">
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3">是否有效：</label>
            <div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="flag" id="flag" size="1" >
                    <c:choose>
                        <c:when test="${sysOrg.flag}">
                            <option value="0">否</option>
                            <option value="1" selected="selected">是</option>
                        </c:when>
                        <c:otherwise>
                            <option value="0" selected="selected">否</option>
                            <option value="1" >是</option>
                        </c:otherwise>
                    </c:choose>
				</select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-3">备注：</label>
            <div class="formControls col-5">
                <textarea name="orgDesc" id="orgDesc" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"
                          onKeyUp="textarealength(this,100)">${sysOrg.orgDesc}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
            </div>
            <div class="col-4"> </div>
        </div>
        <div class="row cl">
            <div class="col-9 col-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>

<%--装树的一个容器--%>
<div id="orgParentTree" style="display: none">
    <div id="zTree" class="ztree"></div>
</div>

<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<%--引入zTree的js--%>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-org-update").Validform({
            tiptype:2,
            callback:function(form){
                //表单验证通过之后，点击添加才会进入这个方法
                //通过ajax请求提交表单数据
                $.ajax({
                    url:"sysOrg/updateOrg",
                    type:"POST",
                    data:$("#form-org-update").serialize(),
                    success:function (data) {
                        var icon;
                        if(data.result){
                            icon = 6;
                        }else{
                            icon = 5;
                        }
                        layer.alert(data.data, {icon: icon},function () {
                            //关闭弹出框
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.$('.btn-refresh').click();
                            parent.layer.close(index);
                            //刷新页面
                            parent.location.reload();
                        });

                    }
                })
                //关闭默认的表单提交
                return false;
            }
        });
    });

    function selectOrgParent() {
        //弹出一个提示框（有一个树）
        var index = layer.open({
            title:"选择父组织",
            type:1,
            content: $("#orgParentTree"),
            area: ['500px', '300px'],
            btn:"确定"
        });
        //渲染
        //通过ajax请求去查询组织的信息(List)
        $.ajax({
            url:"sysOrg/list",
            type:"POST",
            success:function (data) {
                console.log(data);
                var zTreeObj;
                // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
                var setting = {
                    data: {
                        key: {
                            name: "orgName"
                        },
                        simpleData: {
                            enable: true,
                            idKey: "orgId",
                            pIdKey: "orgParentId",
                        }
                    },
                    callback: {
                        onClick: function (event, treeId, treeNode) {
                            $("#orgParentName").val(treeNode.orgName);
                            $("#orgParentId").val(treeNode.orgId);
                        }
                    }

                };
                // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
                /*var zNodes = [
                    {id:1, pId:0, name: "父节点1"},
                    {id:11, pId:1, name: "子节点1"},
                    {id:12, pId:1, name: "子节点2"}
                ];*/
                var zNodes = data;
                $(document).ready(function(){
                    zTreeObj = $.fn.zTree.init($("#zTree"), setting, zNodes);
                });
            }
        })

    }
</script>
</body>
</html>
