package com.qianfeng.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysUser;
import com.qianfeng.oa.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 作跳转登录页面使用
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 用户登录
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser,ModelMap map){
       //登录认证
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()){ //如没有登录认证
            //登录认证
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getUserPassword());
            try {
                currentUser.login(token);
            }catch (AuthenticationException e){
                System.out.println("认证失败");
                return "login";
            }
        }
        SysUser user = (SysUser) currentUser.getPrincipal();
        List<SysMenu> menuList = sysUserService.getMenuListByUserId(user.getUserId());
        map.put("menuList",menuList);
        return "index";
    }
    /*@RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser,ModelMap map){
        SysUser currentUser = sysUserService.checkLogin(sysUser);
        if(currentUser == null){
            //登录失败，返回登录页面
            return "login";
        }
        List<SysMenu> menuList = sysUserService.getMenuListByUserId(currentUser.getUserId());
        map.put("menuList",menuList);
        return "index";
    }*/

    @RequestMapping("/selectByCondition")
    public String selectByCondition(Page page, SysUser sysUser, ModelMap map){
        //得到一个pageInfo对象
        PageInfo<SysUser> pageInfo = sysUserService.selectByCondition(page,sysUser);
        //装起来
        map.put("pageInfo",pageInfo);
        map.put("sysUser",sysUser);
        map.put("url","sysUser/selectByCondition");

        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userName",sysUser.getUserName());
        paramMap.put("flag",sysUser.getFlag());
        map.put("params",gson.toJson(paramMap));
        return "user/user_list";

    }

    /**
     *跳转到用户的添加页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/user_add";
    }

    /**
     * 添加用户
     */
    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysUser sysUser){
        SysResult sysResult = new SysResult();
        int count = sysUserService.insertSelective(sysUser);
        if (count > 0) {
            sysResult.setResult(true);
            sysResult.setData("添加成功!");
        } else {
            sysResult.setResult(false);
            sysResult.setData("添加失败!");
        }
        return sysResult;
    }

    /**
     * 修改、数据回显
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Long userId,ModelMap map){
        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);
        map.put("sysUser",sysUser);
        return "user/user_update";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    @ResponseBody
    public SysResult updateUser(SysUser sysUser){
        int count = sysUserService.updateByPrimaryKeySelective(sysUser);
        SysResult sysResult = new SysResult();
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("修改成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("修改失败");
        }
        return sysResult;
    }

    /**
     * 删除用户，假删，修改flag状态
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult deleteUser(SysUser sysUser){
        sysUser.setFlag(false);
        int count = sysUserService.updateByPrimaryKeySelective(sysUser);
        SysResult sysResult = new SysResult();
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("删除成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("删除失败");
        }
        return sysResult;
    }

    /**
     * 批量删除，接收前台id集合，修改对应id的flag值
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public SysResult batchDel(@RequestParam List<Long> idList){
        int count = sysUserService.updateFlagByIdList(idList);
        SysResult sysResult = new SysResult();
        if(count > 0){
            sysResult.setResult(true);
            sysResult.setData("删除成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("删除失败");
        }
        return sysResult;
    }
}
