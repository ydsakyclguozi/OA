package com.qianfeng.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qianfeng.oa.commons.Page;
import com.qianfeng.oa.commons.SysResult;
import com.qianfeng.oa.entity.SysOrg;
import com.qianfeng.oa.service.ISysOrgService;
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
@RequestMapping("/sysOrg")
public class SysOrgController {


    @Autowired
    private ISysOrgService sysOrgService;

    @RequestMapping("/findById")
    @ResponseBody
    public SysOrg queryById(Long orgId){
        SysOrg org = sysOrgService.selectByPrimaryKey(orgId);
        System.out.println(org);
        return org;
    }

    /**
     * 查询组织的分页数据
     */
    @RequestMapping("/page")
    public String page(Page page, ModelMap map){
        //查询分页数据 pageInfo
        PageInfo<SysOrg> pageInfo = sysOrgService.getPage(page);
        //把数据封装起来
        map.put("pageInfo",pageInfo);
        //跳转到页面进行展示
        return "org/org_list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "org/org_add";
    }

    /**
     * 添加组织
     */
    @RequestMapping("/addOrg")
    @ResponseBody
    public SysResult addOrg(SysOrg sysOrg){
        SysResult sysResult = new SysResult();
        int count = sysOrgService.insertSelective(sysOrg);
        if(count>0){
            sysResult.setResult(true);
            sysResult.setData("添加成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("添加失败");
        }
        return sysResult;
    }

    /**
     * 查询组织集合信息（展示树要用的数据）
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SysOrg> list(){
        List<SysOrg> orgList = sysOrgService.getOrgList();
        return orgList;
    }

    /**
     * 根据组织id查询组织对象
     */
    @RequestMapping("/toUpdate")
    public String getById(Long orgId, ModelMap map){
        SysOrg sysOrg = sysOrgService.selectByPrimaryKey(orgId);
        map.put("sysOrg",sysOrg);
        return "org/org_update";
    }

    /**
     * 修改组织
     */
    @RequestMapping("/updateOrg")
    @ResponseBody
    public SysResult updateOrg(SysOrg sysOrg){
        SysResult sysResult = new SysResult();
        int count = sysOrgService.updateByPrimaryKeySelective(sysOrg);
        if(count>0){
            sysResult.setResult(true);
            sysResult.setData("修改成功！");
        }else{
            sysResult.setResult(false);
            sysResult.setData("修改失败！");
        }
        return sysResult;
    }

    /**
     * 删除单个组织,实际修改flag状态，假删
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long orgId){
        return sysOrgService.updateFlagByOrgId(orgId);
    }

    /**
     * 批量删除，假删，批量修改
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public SysResult batchDel(@RequestParam List<Long> idList){
        return sysOrgService.updateFlagByIdList(idList);
    }

    /**
     * 按条件查询分页数据
     */
    @RequestMapping("/selectByCondition")
    public  String selectByCondition(SysOrg sysOrg,Page page,ModelMap map){
        //返回pageInfo对象
        PageInfo<SysOrg> pageInfo = sysOrgService.selectByCondition(sysOrg,page);
        map.put("pageInfo",pageInfo);
        map.put("sysOrg",sysOrg);
        map.put("url","sysOrg/selectByCondition");

        //传递json格式的条件给page使用
        Gson gson = new Gson();
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgName",sysOrg.getOrgName());
        paramMap.put("orgParentName",sysOrg.getOrgParentName());
        paramMap.put("flag",sysOrg.getFlag());
        map.put("params",gson.toJson(paramMap));
        return "org/org_list";
    }
}
