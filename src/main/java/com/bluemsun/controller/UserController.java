package com.bluemsun.controller;

import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map register(@RequestBody User user, HttpServletRequest req) {
        int res = userService.addUser(user);
        Map map = new HashMap();
        if (res > 0) {
            //密码设为null，为安全性考虑
            user.setPassword(null);
            map.put("user",user);
            map.put("msg","注册成功");
            map.put("status",1);
            req.getSession().setAttribute("user",user);
        }
        else {
            map.put("msg","注册失败");
            map.put("status",2);
        }
        return map;
    }

    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpServletRequest req) {
        User userRes = userService.selectUserByStuNumber(user);
        Map map = new HashMap();
        if (userRes != null) {
            if (userRes.getPassword().equals(user.getPassword())) {
                //密码设为null，为安全性考虑
                userRes.setPassword(null);
                map.put("user",userRes);
                map.put("msg","登陆成功");
                map.put("status",1);
                req.getSession().setAttribute("user",userRes);
            }
            else {
                map.put("msg","登录失败，密码错误");
                map.put("status",2);
            }
        } else {
            map.put("msg","登录失败，该用户未注册");
            map.put("status",3);
        }
        return map;
    }

}
