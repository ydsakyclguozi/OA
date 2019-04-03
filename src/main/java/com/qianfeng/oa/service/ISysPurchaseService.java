package com.qianfeng.oa.service;


import com.qianfeng.oa.entity.SysPurchase;

public interface ISysPurchaseService extends IBaseService<SysPurchase> {
    void addPurchaseAndStartProcess(SysPurchase sysPurchase);

    SysPurchase getPurchaseByTaskId(Long taskId);

    void updateSysPurchaseAndCompleteTask(SysPurchase sysPurchase, Long taskId);
}
