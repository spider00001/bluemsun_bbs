package com.bluemsun.controller;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;
import com.bluemsun.service.manager.ManageUserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("/bsmanager")
public class ManagerController extends HttpServlet {

    private final ManageUserService managerService;

    public ManagerController(ManageUserService managerService) {
        this.managerService = managerService;
    }

    //登录
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Manager manager, HttpServletRequest req) {
        Map<String,Object> map = managerService.managerLogin(manager);
        if (map.get("manager")!= null) {
            req.getSession().setAttribute("manager",map.get("manager"));
        }
        return map;
    }

    //用户管理首页
    @PostMapping("/manageUsers")
    public Map<String,Object> manageUsers() {
        return managerService.selectUsers();
    }

    //查看单个用户信息
    @PostMapping("/checkUser")
    public Map<String,Object> checkUser(@RequestBody User user) {
        return managerService.checkUser(user);
    }

    //用户冻结
    @PostMapping("/frozenUser")
    public Map<String,Object> frozenUser(@RequestBody User user) {
        return managerService.frozenUser(user);
    }

    //用户解封
    @PostMapping("/unfreezeUser")
    public Map<String,Object> unfreezeUser(@RequestBody User user) {
        return managerService.unfreezeUser(user);
    }

    //用户删除
    @PostMapping("/deleteUser")
    public Map<String,Object> deleteUser(@RequestBody User user) {
        return managerService.deleteUser(user);
    }


}
