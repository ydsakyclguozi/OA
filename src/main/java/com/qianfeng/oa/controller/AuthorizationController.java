package com.qianfeng.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysRole;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.service.ISysMenuService;
import com.qianfeng.oa.service.ISysRoleService;
import com.qianfeng.oa.service.ISysUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/showPage")
    public String toAuthorizationPage(ModelMap map){
        //查询所有的角色
        List<SysRole> sysRolesList = sysRoleService.queryAllRole();
        map.put("sysRoleList",sysRolesList);
        return "authorization/authorization";
    }

    /**
     * 该角色下所有授权了的用户
     */
    @RequestMapping("/queryAuthUserByRoleId")
    public String queryAuthUserByRoleId(Long roleId, Page page, ModelMap map){
        PageInfo<SysUser> pageInfo = sysUserService.queryAuthUserByRoleId(roleId,page);
        map.put("pageInfo",pageInfo);
        map.put("url","authorization/queryAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        map.put("params",gson.toJson(paramMap));
        return "authorization/authorization_authUser_list";
    }

    /**
     * 查询该角色下未授权的用户信息
     */
    @RequestMapping("/queryNoAuthUserByRoleId")
    public String queryNoAuthUserByRoleId( Long roleId, String userName,Page page, ModelMap map){
        PageInfo<SysUser> pageInfo = sysUserService.queryNoAuthUserByRoleId(roleId,userName,page);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("userName",userName);
        map.put("url","authorization/queryNoAuthUserByRoleId");

        Gson gson = new Gson();
        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        paramMap.put("userName",userName);
        map.put("params",gson.toJson(paramMap));

        return "authorization/authorization_noauthUser_list";
    }

    /**
     * 批量给某个角色授权用户,实际上往角色用户关系表中添加用户ID和角色ID
     */
    @RequestMapping("/batchAddUserToRole")
    @ResponseBody
    public SysResult batchAddUserToRole(@RequestParam List<Long> idList, Long roleId){
        return sysRoleService.batchAddUserToRole(idList,roleId);
    }

    /**
     * 接触某个角色用户授权，实际上删除角色用户关系表中对应用户Id的行
     */
    @RequestMapping("/delUserFormRole")
    @ResponseBody
    public SysResult delUserFormRole(Long roleId,Long userId ){
        return sysRoleService.delUserFormRole(roleId ,userId);
    }

    /*********************************************************************************************/

    /**
     * 该角色下所有授权了的菜单
     */
    @RequestMapping("/queryAuthMenuByRoleId")
    public String queryAuthMenuByRoleId(Long roleId, Page page, ModelMap map){
        PageInfo<SysMenu> pageInfo = sysMenuService.queryAuthMenuByRoleId(roleId,page);
        map.put("pageInfo",pageInfo);
        map.put("url","authorization/queryAuthMenuByRoleId");

        Gson gson = new Gson();
        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        map.put("params",gson.toJson(paramMap));
        return "authorization/authorization_authMenu_list";
    }

    /**
     * 根据查询条件查询该角色下未授权的菜单信息
     */
    @RequestMapping("/queryNoAuthMenuByRoleId")
    public String queryNoAuthMenuByRoleId( Long roleId, String menuName,Page page, ModelMap map){
        PageInfo<SysMenu> pageInfo = sysMenuService.queryNoAuthMenuByRoleId(roleId,menuName,page);
        map.put("pageInfo",pageInfo);
        map.put("roleId",roleId);
        map.put("menuName",menuName);
        map.put("url","authorization/queryNoAuthMenuByRoleId");

        Gson gson = new Gson();
        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId",roleId);
        paramMap.put("menuName",menuName);
        map.put("params",gson.toJson(paramMap));

        return "authorization/authorization_noauthMenu_list";
    }

    /**
     * 批量给某个角色授权用户,实际上往角色用户关系表中添加用户ID和角色ID
     */
    @RequestMapping("/batchAddMenuToRole")
    @ResponseBody
    public SysResult batchAddMenuToRole(@RequestParam List<Long> idList, Long roleId){
        return sysRoleService.batchAddMenuToRole(idList,roleId);
    }

    /**
     * 接触某个角色菜单授权，实际上删除角色菜单关系表中对应用户Id的行数据
     */
    @RequestMapping("/delMenuFormRole")
    @ResponseBody
    public SysResult delMenuFormRole(Long roleId,Long menuId ){
        return sysRoleService.delMenuFormRole(roleId ,menuId);
    }

}
