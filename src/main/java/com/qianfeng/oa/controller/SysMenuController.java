package com.qianfeng.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysMenu;
import com.qianfeng.oa.entity.SysMenuParent;
import com.qianfeng.oa.service.ISysMenuService;
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
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 根据条件查询菜单分页数据
     */
    @RequestMapping("/queryMenuByCondition")
    public String queryMenuByCondition(Page page, SysMenu sysMenu, ModelMap map){
        PageInfo<SysMenu> pageInfo = sysMenuService.queryMenuByCondition(page,sysMenu);
        map.put("pageInfo",pageInfo);
        map.put("url","sysMenu/queryMenuByCondition");
        map.put("sysMenu",sysMenu);

        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("menuName",sysMenu.getMenuName());
        paramMap.put("isPublish",sysMenu.getIsPublish());
        map.put("params",gson.toJson(paramMap));

        return "menu/menu_list";
    }

    /**
     * 添加菜单跳转
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "menu/menu_add";
    }

    /**
     * 查询菜单集合信息（展示树要用的数据）
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenu> getList(){
        List<SysMenu> menuList = sysMenuService.getList();
        return menuList;
    }

    /**
     * 添加菜单
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public SysResult addMenu(SysMenu sysMenu){
        SysResult sysResult = new SysResult();
        int count = sysMenuService.insertSelective(sysMenu);
        if(count > 0 ){
            sysResult.setResult(true);
            sysResult.setData("添加菜单成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("添加菜单失败");
        }
        return sysResult;
    }

    /**
     * 通过menuId查询菜单对象，回显修改页面
     */
    @RequestMapping("/toUpdate/{menuId}")
    public String selectById(@PathVariable Long menuId,ModelMap map){
        SysMenuParent sysMenu = sysMenuService.selectSysMenuParentByMenuId(menuId);
        System.out.println(sysMenu.getMenuName());
        System.out.println(sysMenu.getIsPublish());
        map.put("sysMenu",sysMenu);
        return "menu/menu_update";
    }

    /**
     * 修改菜单
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public SysResult updateMenu(SysMenu sysMenu){
        SysResult sysResult = new SysResult();
        int count = sysMenuService.updateByPrimaryKeySelective(sysMenu);
        if(count > 0 ){
            sysResult.setResult(true);
            sysResult.setData("修改菜单成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("修改菜单失败");
        }
        return sysResult;
    }

    /**
     * 假删菜单，修改flag值
     */
    @RequestMapping("/delMenu")
    @ResponseBody
    public SysResult delMenu(Long menuId){
        SysResult sysResult = new SysResult();
        int count = sysMenuService.updateFlagByMenuId(menuId);
        if(count > 0 ){
            sysResult.setResult(true);
            sysResult.setData("删除菜单成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("删除菜单失败");
        }
        return sysResult;
    }

    /**
     * 批量删除菜单，修改flag值实现假删
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public SysResult batchDel(@RequestParam List<Long> idList){
        SysResult sysResult = new SysResult();
        int count = sysMenuService.updateFlagByIdList(idList);
        if(count > 0 ){
            sysResult.setResult(true);
            sysResult.setData("删除菜单成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("删除菜单失败");
        }
        return sysResult;
    }
}
