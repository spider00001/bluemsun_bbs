package com.bluemsun.controller;

import com.bluemsun.entity.User;
import com.bluemsun.service.BlogService;
import com.bluemsun.service.ManagePlateService;
import com.bluemsun.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

    private final BlogService manageBlogService;
    private final ManagePlateService managePlateService;
    private final UserService userService;

    public UserController(BlogService manageBlogService, ManagePlateService managePlateService, UserService userService) {
        this.manageBlogService = manageBlogService;
        this.managePlateService = managePlateService;
        this.userService = userService;
    }


    /**
     * 首页 (置顶博客，置顶板块)
     *
     */
    //查看置顶博客list
    @GetMapping("/getBlogsHomeTop")
    public Map getBlogsHomeTop() {
        return manageBlogService.getBlogsHomeTop();
    }

    //查看置顶板块list
    @GetMapping("/getPlatesOfHome")
    public Map getPlatesOfHome() {
        return managePlateService.getPlatesOfHome();
    }


    /**
     * 搜索模块(分页):博客,用户,板块
     *
     */




    /**
     * 用户信息模块
     *
     */
    //注册
    @PostMapping("/register")
    public Map register(@RequestBody User user, HttpServletRequest req) {
        Map map = userService.addUser(user);
        if (map.get("user") != null) {
            req.getSession().setAttribute("user",map.get("user"));
        }
        return userService.addUser(user);
    }

    //登录
    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpServletRequest req) {
        Map map = userService.userLogin(user);
        if (map.get("user") != null) {
            req.getSession().setAttribute("user",map.get("user"));
        }
        return map;
    }

    //个人信息修改
    @PostMapping("/updateUser")
    public Map updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    //注销账号
    @PostMapping("/deleteUser")
    public Map deleteUser(User user) {
        return userService.deleteUser(user);
    }

    //进入个人中心 (未测试........)
    @PostMapping("/enterPersonalCenter")
    public Map enterPersonalCenter(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        return userService.checkUser(user);
    }


    //头像上传

    //查看关注列表
    @PostMapping("/getFollowUsers")
    public Map getFollowUsers(@RequestBody Map map) {
        return userService.getFollowUsers(map);
    }

    //查看粉丝列表
    @PostMapping("/getFans")
    public Map getFans(@RequestBody Map map) {
        return userService.getFans(map);
    }

    //查看用户详情
    @PostMapping("/checkUser")
    public Map checkUser(@RequestBody User user) {
        return userService.checkUser(user);
    }

    //关注
    @PostMapping("/followUser")
    public Map followUser(@RequestBody Map map) {
        return userService.followUser(map);
    }

    //取关
    @PostMapping("/cancelFollowUser")
    public Map cancelFollowUser(@RequestBody Map map) {
        return userService.cancelFollowUser(map);
    }


    /**
     * 我的创作模块
     *
     */
    //发布博客(要选择自己的博客在哪个板块)


    //编辑博客

    //查看博客分页

    //查看博客详情

    //删除自己的博客

    //点赞博客

    //取消点赞博客

    //评论博客

    //删除自己的评论

    //删除别人的评论(如果是自己的博客内)

    //回复评论

    //点赞评论

    //取消点赞评论


    /**
     * 板块管理模块
     *
     */
    //申请板块

    //查看板块申请list

    //删除板块

    //修改简介

    //发布公告

    //删除公告

    //删除板块内博客

    //置顶博客:新增置顶博客

    //置顶博客:修改置顶博客位置

    //置顶博客:取消置顶博客

    //博客迁移(如果自己有两个板块的话) (博客详情内)


    /**
     * 板块分类模块
     *
     */
    //首页(只展示各种板块)

    //板块首页(展示板块信息及博客分页)

    //搜索板块内博客

}
