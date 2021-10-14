package com.bluemsun.controller;

import com.bluemsun.entity.Manager;
import com.bluemsun.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
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
        Map map = new HashMap();
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
}
