package com.qianfeng.oa.mapper;

import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysOrg;

import java.util.List;

public interface SysOrgMapper extends IBaseDao<SysOrg> {

    List<SysOrg> getOrgList();

    int queryCountByOrgParentId(Long orgId);

    int queryCountByIdList(List<Long> idList);

    int batchUpdateFlagByIdList(List<Long> idList);

    List<SysOrg> selectCondition(SysOrg sysOrg);
}