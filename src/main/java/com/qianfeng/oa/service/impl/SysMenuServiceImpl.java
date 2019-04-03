package com.qianfeng.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysMenuParent;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.mapper.SysMenuMapper;
import com.qianfeng.oa.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public PageInfo<SysMenu> queryMenuByCondition(Page page, SysMenu sysMenu) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysMenu> list = sysMenuMapper.queryMenuByCondition(sysMenu);
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        return pageInfo;
    }

    public List<SysMenu> getList() {
        return sysMenuMapper.getList();
    }

    public int updateFlagByMenuId(Long menuId) {
        return sysMenuMapper.updateFlagByMenuId(menuId);
    }

    public SysMenuParent selectSysMenuParentByMenuId(Long menuId) {
        return sysMenuMapper.selectSysMenuParentByMenuId(menuId);
    }

    public int updateFlagByIdList(List<Long> idList) {
        return sysMenuMapper.updateFlagByIdList(idList);
    }

    public PageInfo<SysMenu> queryAuthMenuByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysMenu> list = sysMenuMapper.queryAuthMenuByRoleId(roleId);
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        return pageInfo;
    }

    public PageInfo<SysMenu> queryNoAuthMenuByRoleId(Long roleId, String menuName, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysMenu> list = sysMenuMapper.queryNoAuthMenuByRoleId(roleId,menuName);
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);

        return pageInfo;
    }

    public IBaseDao<SysMenu> getDao() {
        return sysMenuMapper;
    }
}
