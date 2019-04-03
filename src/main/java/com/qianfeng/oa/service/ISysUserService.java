package com.qianfeng.oa.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysUser;

import java.util.List;

public interface ISysUserService extends IBaseService<SysUser> {
    PageInfo<SysUser> selectByCondition(Page page, SysUser sysUser);

    int addUser(SysUser sysUser);


    int updateFlagByIdList(List<Long> idList);

    PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page);

    PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName,Page page);

    SysUser checkLogin(SysUser sysUser);

    List<SysMenu> getMenuListByUserId(Long userId);

    SysUser getUserByName(String userName);
}
