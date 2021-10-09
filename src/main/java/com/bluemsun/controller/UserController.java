package com.bluemsun.controller;

import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/allUser")
    public String list(Model model) {
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);
        return "allUser";
    }
}
