package com.qianfeng.oa.service;

import com.qianfeng.oa.entity.SysAudit;

public interface ISysAuditService extends IBaseService<SysAudit> {
    void addAuditAndCompleteTask(SysAudit sysAudit, Long taskId);
}
