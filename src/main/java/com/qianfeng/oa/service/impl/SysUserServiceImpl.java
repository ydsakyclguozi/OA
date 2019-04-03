package com.qianfeng.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.dao.IBaseDao;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.mapper.SysUserMapper;
import com.qianfeng.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    public IBaseDao<SysUser> getDao() {
        return sysUserMapper;
    }


    public PageInfo<SysUser> selectByCondition(Page page, SysUser sysUser) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList = sysUserMapper.selectByCondition(sysUser);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);

        return pageInfo;
    }


    public int addUser(SysUser sysUser) {
        return sysUserMapper.insertSelective(sysUser);
    }

    public int updateFlagByIdList(List<Long> idList) {
        return sysUserMapper.updateFlagByIdList(idList);
    }

    public PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.queryAuthUserByRoleId(roleId);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);

        return pageInfo;
    }

    public PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId,String userName, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);

        return pageInfo;
    }

    public SysUser checkLogin(SysUser sysUser) {
        SysUser currentUser = sysUserMapper.getUserByUserName(sysUser);
        //用户名存在，再比较密码是否正确
        if(currentUser != null){
            if(sysUser.getUserPassword().equals(currentUser.getUserPassword())){
                return currentUser;
            }
        }
        return null;
    }

    public List<SysMenu> getMenuListByUserId(Long userId) {
        return sysUserMapper.getMenuListByUserId(userId);
    }

    public SysUser getUserByName(String userName) {
        return sysUserMapper.getUserByName(userName);
    }


}
