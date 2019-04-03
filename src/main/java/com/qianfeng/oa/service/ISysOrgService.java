package com.qianfeng.oa.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysOrg;

import java.util.List;

public interface ISysOrgService extends IBaseService<SysOrg> {
    PageInfo<SysOrg> getPage(Page page);

    List<SysOrg> getOrgList();

    SysResult updateFlagByOrgId(Long orgId);

    SysResult updateFlagByIdList(List<Long> idList);

    PageInfo<SysOrg> selectByCondition(SysOrg sysOrg, Page page);
}
