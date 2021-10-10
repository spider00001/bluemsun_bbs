package com.bluemsun.controller;

import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User user, HttpServletResponse resp) {

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(@RequestBody User user) {

        User user1 = userService.queryUserById(user.getId());
        Map map = new HashMap();
        if (user1 != null) {
            map.put("msg","登录成功");
            map.put("status",1);
        }
        else {
            map.put("msg","登录失败");
            map.put("status",2);
        }

        return map;
    }

}
