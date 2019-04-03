package com.qianfeng.oa.mapper;

import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysMenuParent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu> {
    int deleteByPrimaryKey(Long menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> queryMenuByCondition( SysMenu sysMenu);

    List<SysMenu> getList();

    int updateFlagByMenuId(Long menuId);

    SysMenuParent selectSysMenuParentByMenuId(Long menuId);

    int updateFlagByIdList(List<Long> idList);

    List<SysMenu> queryAuthMenuByRoleId(Long roleId);

    List<SysMenu> queryNoAuthMenuByRoleId(@Param("roleId") Long roleId, @Param("menuName") String menuName);
}