package com.qianfeng.oa.controller;

import com.qianfeng.oa.entity.SysAudit;
import com.qianfeng.oa.entity.SysPurchase;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.service.ISysAuditService;
import com.qianfeng.oa.service.ISysPurchaseService;
import org.activiti.engine.FormService;
import org.activiti.engine.form.TaskFormData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController  {

    @Autowired
    private ISysPurchaseService sysPurchaseService;
    @Autowired
    private ISysAuditService sysAuditService;
    @Autowired
    private FormService formService;

    //跳转到采购申请的页面
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "purchase/purchase_add";
    }

    /**
     * 添加采购申请
     */
    @RequestMapping("/add")
    public String add(SysPurchase sysPurchase){
        //标题，内容，金额，（申请人的id，名称）
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        sysPurchase.setUserId(sysUser.getUserId());
        sysPurchase.setUserName(sysUser.getUserName());
        //调用方法，添加采购单的操作
        sysPurchaseService.addPurchaseAndStartProcess(sysPurchase);

        return "purchase/purchase_add";
    }

    /**
     * 跳转到审批任务的页面
     */
    @RequestMapping("/toApproval")
    public String toApproval(Long taskId, ModelMap map){
        //得到formKey 的值
        TaskFormData taskFormData = formService.getTaskFormData(taskId.toString());
        String key = taskFormData.getFormKey();
        System.out.println(key);
        //通过任务ID得到purchase对象
        SysPurchase sysPurchase = sysPurchaseService.getPurchaseByTaskId(taskId);
        String url = "";
        if("process/approval".equals(key)){
            //跳转到审批页面
            map.put("purchase",sysPurchase);
            map.put("taskId",taskId);
            url =  "purchase/purchase_approval";
        }else if("process/toAudit".equals(key)){
            //跳转到重新申请页面
            map.put("purchase",sysPurchase);
            map.put("taskId",taskId);
            url = "purchase/purchase_toReApproval";
        }
        return url;
    }

    /**
     * 重新提交申请
     */
    @RequestMapping("/toReApproval")
    public String toReApproval(Long taskId,SysPurchase sysPurchase){
        System.err.println(sysPurchase.getId());
        sysPurchaseService.updateSysPurchaseAndCompleteTask(sysPurchase,taskId);
        return "purchase/purchase_toReApproval";
    }


    /**
     * 审批
     */
    @RequestMapping("/approval")
    public String approval(Long taskId,SysAudit sysAudit){
         //需要信息：state，purchaseid,content, (user_id,user_name)
        //拿到当前用户的方法（两句代码）
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();

        sysAudit.setUserId(sysUser.getUserId());
        sysAudit.setUserName(sysUser.getUserName());

        //添加审批信息 到数据库
         sysAuditService.addAuditAndCompleteTask(sysAudit,taskId);

        return "purchase/purchase_approval";
    }

}
