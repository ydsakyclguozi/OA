package com.qianfeng.oa.service;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysMenuParent;
import com.qianfeng.oa.entity.SysUser;

import java.util.List;

public interface ISysMenuService extends IBaseService<SysMenu> {
    PageInfo<SysMenu> queryMenuByCondition(Page page, SysMenu sysMenu);

    List<SysMenu> getList();

    int updateFlagByMenuId(Long menuId);

    SysMenuParent selectSysMenuParentByMenuId(Long menuId);

    int updateFlagByIdList(List<Long> idList);

    PageInfo<SysMenu> queryAuthMenuByRoleId(Long roleId, Page page);

    PageInfo<SysMenu> queryNoAuthMenuByRoleId(Long roleId, String menuName, Page page);
}
