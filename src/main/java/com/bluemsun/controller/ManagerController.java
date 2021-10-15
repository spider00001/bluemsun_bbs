package com.bluemsun.controller;

import com.bluemsun.entity.Manager;
import com.bluemsun.entity.User;
import com.bluemsun.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bsmanager")
public class ManagerController extends HttpServlet {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/login")
    public Map login(@RequestBody Manager manager, HttpServletRequest req) {
        Manager managerRes = managerService.managerLogin(manager);
        Map<String,Object> map = new HashMap<String,Object>();
        if (managerRes != null) {
            if (managerRes.getPassword().equals(manager.getPassword())) {
                map.put("msg","登陆成功");
                map.put("status",1);
                managerRes.setPassword(null);
                map.put("manager",managerRes);
                req.getSession().setAttribute("manager",managerRes);
            } else {
                map.put("msg","登陆失败,密码错误");
                map.put("status",2);
            }
        } else {
            map.put("msg","登录失败，请检查账号是否正确");
            map.put("status",3);
        }
        return map;
    }

    @PostMapping("/manageusers")
    public Map manageUsers() {
        List<User> userList = managerService.selectUsers();
        Map<String,Object> map = new HashMap<String,Object>();
        if (userList != null) {
            map.put("msg","查看成功");
            map.put("status",1);
            map.put("userList",userList);
        } else {
            map.put("msg","查看失败");
            map.put("status",2);
        }
        return map;
    }

    @PostMapping("/frozen")
    public Map frozenUser(@RequestBody User user) {
        int row = managerService.frozenUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","冻结成功");
            map.put("status",1);
        } else {
            map.put("msg","冻结失败");
            map.put("status",2);
        }
        return map;
    }

    @PostMapping("/unfreeze")
    public Map unfreezeUser(@RequestBody User user) {
        int row = managerService.unfreezeUser(user);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","解封成功");
            map.put("status",1);
        } else {
            map.put("msg","解封失败");
            map.put("status",2);
        }
        return map;
    }


}
