package com.qianfeng.oa.service.impl;

import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysAudit;
import com.qianfeng.oa.mapper.SysAuditMapper;
import com.qianfeng.oa.service.ISysAuditService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysAuditServiceImpl extends BaseServiceImpl<SysAudit> implements ISysAuditService {

    @Autowired
    private SysAuditMapper sysAuditMapper;
    @Autowired
    private TaskService taskService;

    public IBaseDao<SysAudit> getDao() {
        return sysAuditMapper;
    }

    public void addAuditAndCompleteTask(SysAudit sysAudit, Long taskId) {
        //添加审批信息
        sysAuditMapper.insertSelective(sysAudit);
        //完成任务
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("flag",sysAudit.getState());
        taskService.complete(taskId.toString(),map);
    }
}
