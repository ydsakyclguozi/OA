package com.qianfeng.oa.service.impl;

import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysPurchase;
import com.qianfeng.oa.mapper.SysPurchaseMapper;
import com.qianfeng.oa.service.ISysPurchaseService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysPurchaseServiceImpl extends BaseServiceImpl<SysPurchase> implements ISysPurchaseService {

    @Autowired
    private SysPurchaseMapper sysPurchaseMapper;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    public IBaseDao<SysPurchase> getDao() {
        return sysPurchaseMapper;
    }

    public void addPurchaseAndStartProcess(SysPurchase sysPurchase) {
        //添加采购信息 ,需要在PurchaseMapper.xml 添加里设置
        //   useGeneratedKeys="true" keyProperty="id"  实现主键回填
        sysPurchaseMapper.insertSelective(sysPurchase);
        //启动流程
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("money",sysPurchase.getMoney());
        map.put("currentId",sysPurchase.getUserId());
        //实际开发中需要先查询信息
        map.put("administrationId",2);
        map.put("managerId",3);
        map.put("financialId",4);
        //bussinessKey  让流程和业务进行关联
        String businessKey = sysPurchase.getId().toString();
        runtimeService.startProcessInstanceByKey("purchase",businessKey,map);
    }

    public SysPurchase getPurchaseByTaskId(Long taskId) {
        //taskID -Task表得到流程实例ID--processInstaticId --通过processInstaticId查询流程实例对象（act_ru_exection)
        // --bussinessKey(存的是采购但的id)
        Task task = taskService.createTaskQuery().taskId(taskId.toString()).singleResult();
        //得到流程实例ID
        String processInstanceId = task.getProcessInstanceId();
        //通过流程实例ID 查询流程实例对象（act_ru_exection)
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //通过流程实例对象得到businessKey
        String businessKey = processInstance.getBusinessKey();
        //得到一个采购单ID
        Long purchaseId = Long.parseLong(businessKey);
        //查询一个采购单对象 （businessKey 就是采购单ID)
        SysPurchase sysPurchase = sysPurchaseMapper.selectByPrimaryKey(purchaseId);
        return sysPurchase;
    }

    public void updateSysPurchaseAndCompleteTask(SysPurchase sysPurchase, Long taskId) {

        //重新提交采购申请   实际修改采购单 ID没变
        sysPurchaseMapper.updateByPrimaryKeySelective(sysPurchase);
        //完成任务
        Map<String , Object> map = new HashMap<String, Object>();
        map.put("flag",true);
        map.put("money",sysPurchase.getMoney());
        taskService.complete(taskId.toString(),map);
    }
}
