package com.qianfeng.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysRole;
import com.qianfeng.oa.service.ISysRoleService;
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
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 根据条件查询角色分页信息，首次进入查询的条件为空
     */
    @RequestMapping("/selectByCondition")
    public String selectByCondition(Page page, SysRole sysRole, ModelMap map){
        System.out.println(sysRole.getRoleName());
        PageInfo<SysRole> pageInfo = sysRoleService.selectByCondition(page,sysRole);

        map.put("pageInfo",pageInfo);
        map.put("sysRole",sysRole);
        map.put("url","sysRole/selectByCondition");

        Gson gson = new Gson();
        Map<String , Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleName",sysRole.getRoleName());
        map.put("params",gson.toJson(paramMap));

        return "role/role_list";
    }


    /**
     * 编辑用户，通过roleId获取用户回显数据
     */
    @RequestMapping("/toUpdateRole/{roleId}")
    public String toUpdate(@PathVariable Long roleId, ModelMap map){
        SysRole sysRole = sysRoleService.selectByPrimaryKey(roleId);
        map.put("sysRole",sysRole);
        return "role/role_update";
    }

    /**
     * 修改角色信息
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public SysResult updateRole(SysRole sysRole){
        SysResult sysResult = new SysResult();
        int count = sysRoleService.updateByPrimaryKeySelective(sysRole);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("修改角色成功");
        }else {
            sysResult.setResult(false);
            sysResult.setData("修改角色失败");
        }
        return sysResult;
    }

    /**
     * 添加跳转
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "role/role_add";
    }

    /**
     * 添加角色
     */
    @RequestMapping("/addRole")
    @ResponseBody
    public SysResult addRole(SysRole sysRole){
        SysResult sysResult = new SysResult();
        int count = sysRoleService.insertSelective(sysRole);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("添加角色成功");
        }else {
            sysResult.setResult(false);
            sysResult.setData("添加角色失败");
        }
        return sysResult;
    }

    /**
     * 删除角色，假删，修改flag值
     */
    @RequestMapping("/delRole")
    @ResponseBody
    public SysResult delRole(Long roleId){
        SysResult sysResult = new SysResult();
        int count = sysRoleService.updateFlagByRoleId(roleId);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("删除角色成功");
        }else {
            sysResult.setResult(false);
            sysResult.setData("删除角色失败");
        }
        return sysResult;
    }

    /**
     * 批量删除 假删，修改flag值
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public SysResult batchDel(@RequestParam List<Long> idList){
        SysResult sysResult = new SysResult();
        int count = sysRoleService.updateFlagByRoleIdList(idList);
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("删除角色成功");
        }else {
            sysResult.setResult(false);
            sysResult.setData("删除角色失败");
        }
        return sysResult;
    }
}
