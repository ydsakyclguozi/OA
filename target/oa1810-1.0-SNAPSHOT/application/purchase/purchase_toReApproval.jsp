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
<head>
    <title>Title</title>
</head>
<body>
<div class="pd-20">
    <form action="purchase/toReApproval" method="post" enctype="multipart/form-data" class="form form-horizontal"
          id="form-purchase-toReApproval">
        <h4>采购申请信息</h4>
        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>标题：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="${purchase.title}" name="title" >
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>金额：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="${purchase.money}" name="money" >
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <label class="form-label col-3"><span class="c-red">*</span>备注：</label>
            <div class="formControls col-5">
                <input type="text" class="input-text" value="${purchase.content}" name="content">
            </div>
            <div class="col-4"> </div>
        </div>

        <div class="row cl">
            <div class="col-9 col-offset-3">
                <input type="hidden" name="id" value="${purchase.id}">
                <input type="hidden" name="taskId" value="${taskId}">
                <input class="btn btn-primary radius"  type="submit"
                       value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</div>


<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script>
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">

    $(function(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $("#form-purchase-toReApproval").Validform({
            tiptype:2,
            callback:function(form){

                return true;
            }
        });
    });

</script>
</body>
</html>
