package com.qianfeng.oa.mapper;

import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysRole;
import com.qianfeng.oa.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole> {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectByCondition(SysRole sysRole);

    int updateFlagByRoleId(Long roleId);

    int updateFlagByRoleIdList(List<Long> idList);

    List<SysRole> queryAllRole();

    int batchAddUserToRole(@Param("idList") List<Long> idList, @Param("roleId") Long roleId);

    int delUserFormRole(@Param("roleId") Long roleId,@Param("userId") Long userId);

    int batchAddMenuToRole(@Param("idList") List<Long> idList,@Param("roleId") Long roleId);

    int delMenuFormRole(@Param("roleId") Long roleId,@Param("menuId") Long menuId);
}