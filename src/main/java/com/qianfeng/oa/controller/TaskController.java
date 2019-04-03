package com.qianfeng.oa.controller;

import com.qianfeng.oa.entity.SysUser;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 查看当前登录用户所有的任务，展示一个任务列表
     * @return
     */
    @RequestMapping("/tasklist")
    public String queryTask(ModelMap map){
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        List<Task> taskList =  taskService.createTaskQuery().taskAssignee(sysUser.getUserId().toString()).list();
        map.put("taskList",taskList);
        for (Task task: taskList) {
            System.out.println(task.getId());
            System.out.println(task.getName());
        }
        return "task/task_list";
    }
}
