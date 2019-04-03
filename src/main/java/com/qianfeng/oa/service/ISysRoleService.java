package com.qianfeng.oa.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysRole;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.service.IBaseService;

import java.util.List;

public interface ISysRoleService extends IBaseService<SysRole> {

    PageInfo<SysRole> selectByCondition(Page page, SysRole sysRole);

    int updateFlagByRoleId(Long roleId);

    int updateFlagByRoleIdList(List<Long> idList);

    List<SysRole> queryAllRole();

    SysResult batchAddUserToRole(List<Long> idList, Long roleId);

    SysResult delUserFormRole(Long roleId,Long userId);

    SysResult batchAddMenuToRole(List<Long> idList, Long roleId);

    SysResult delMenuFormRole(Long roleId, Long menuId);
}
