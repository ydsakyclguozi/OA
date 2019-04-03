package com.qianfeng.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysRole;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.mapper.SysRoleMapper;
import com.qianfeng.oa.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleIServicempl extends BaseServiceImpl<SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    public IBaseDao<SysRole> getDao() {
        return sysRoleMapper;
    }

    public PageInfo<SysRole> selectByCondition(Page page, SysRole sysRole) {

        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysRole> list = sysRoleMapper.selectByCondition(sysRole);
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);

        return pageInfo;
    }

    public int updateFlagByRoleId(Long roleId) {
        return sysRoleMapper.updateFlagByRoleId(roleId);
    }

    public int updateFlagByRoleIdList(List<Long> idList) {
        return sysRoleMapper.updateFlagByRoleIdList(idList);
    }

    public List<SysRole> queryAllRole() {
        return sysRoleMapper.queryAllRole();
    }

    public SysResult batchAddUserToRole(List<Long> idList, Long roleId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.batchAddUserToRole(idList,roleId);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("授权成功！");
        }else{
            sysResult.setResult(false);
            sysResult.setData("授权失败!");
        }
        return sysResult;
    }

    public SysResult delUserFormRole(Long roleId,Long userId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.delUserFormRole(roleId,userId);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("解除授权成功！");
        }else{
            sysResult.setResult(false);
            sysResult.setData("解除授权失败！");
        }
        return sysResult;
    }

    public SysResult batchAddMenuToRole(List<Long> idList, Long roleId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.batchAddMenuToRole(idList,roleId);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("授权成功！");
        }else{
            sysResult.setResult(false);
            sysResult.setData("授权失败!");
        }
        return sysResult;
    }

    public SysResult delMenuFormRole(Long roleId, Long menuId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.delMenuFormRole(roleId,menuId);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("解除授权成功！");
        }else{
            sysResult.setResult(false);
            sysResult.setData("解除授权失败！");
        }
        return sysResult;
    }

}
