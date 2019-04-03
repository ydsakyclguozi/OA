package com.qianfeng.oa.mapper;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseDao<SysUser> {


    List<SysUser> selectByCondition(SysUser sysUser);

    int insertSelective(SysUser sysUser);

    int updateFlagByIdList(List<Long> idList);


    List<SysUser> queryAuthUserByRoleId(Long roleId);

    List<SysUser> queryNoAuthUserByRoleId(@Param("roleId") Long roleId,@Param("userName") String userName);

    SysUser getUserByUserName(SysUser sysUser);

    List<SysMenu> getMenuListByUserId(Long userId);

    SysUser getUserByName(String userName);
}