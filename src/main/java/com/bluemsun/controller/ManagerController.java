package com.bluemsun.controller;

import com.bluemsun.entity.*;
import com.bluemsun.service.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bsmanager")
public class ManagerController extends HttpServlet {

    private final UserService userService;
    private final BlogService blogService;
    private final PlateService plateService;
    private final PlateApplicationService plateApplicationService;
    private final ManagerNoticeService managerNoticeService;


    public ManagerController(UserService userService, BlogService blogService, PlateService plateService, PlateApplicationService plateApplicationService, ManagerNoticeService managerNoticeService) {
        this.userService = userService;
        this.blogService = blogService;
        this.plateService = plateService;
        this.plateApplicationService = plateApplicationService;
        this.managerNoticeService = managerNoticeService;
    }


    //登录
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Manager manager, HttpServletRequest req) {
        Map<String,Object> map = userService.managerLogin(manager);
        if (map.get("manager")!= null) {
            req.getSession().setAttribute("manager",map.get("manager"));
        }
        return map;
    }

    /**
     * 搜索模块(分页):博客,用户,板块
     *
     */
    //搜索全站用户(搜用户名)
    @GetMapping("/selectUserPage")
    public Map selectUserPage(int pageNum, int pageSize,String username) {
        return userService.selectUserPage(pageNum,pageSize,username);
    }

    //搜索全站板块(搜板块名)
    @GetMapping("/selectPlatePage")
    public Map selectPlatePage(int pageNum, int pageSize,String plateName) {
        return plateService.selectPlatePage(pageNum,pageSize,plateName);
    }

    //搜索全站博客(搜博客题目)
    @GetMapping("selectBlogPage")
    public Map selectBlogPage(int pageNum, int pageSize,String title) {
        return blogService.selectBlogPage(pageNum,pageSize,title);
    }

    //搜索全站资源下载的博客


    /**
     *用户管理模块
     *
    */
    //获取所有用户
    @GetMapping("/getUsers")
    public Map<String,Object> getUsers(int pageNum, int pageSize) {
        return userService.getUsersPage(pageNum,pageSize);
    }

    //查看单个用户信息
    @PostMapping("/checkUser")
    public Map<String,Object> checkUser(@RequestBody User user, HttpServletRequest req) {
        Map<String,Object> map = userService.checkUser(user);
        if (map.get("user") != null) {
            req.getSession().setAttribute("user",map.get("user"));
        }
        return map;
    }

    //用户冻结
    @PostMapping("/frozenUser")
    public Map<String,Object> frozenUser(@RequestBody User user) {
        return userService.frozenUser(user);
    }

    //用户解封
    @PostMapping("/unfreezeUser")
    public Map<String,Object> unfreezeUser(@RequestBody User user) {
        return userService.unfreezeUser(user);
    }

    //用户删除
    @PostMapping("/deleteUser")
    public Map<String,Object> deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }


    /**
     * 帖子管理模块
     *
     */
    //博客分页
    @GetMapping("/getBlogs")
    public Map getBlogs(int pageNum, int pageSize) {
        return blogService.getBlogsPage(pageNum,pageSize);
    }

    //查看博客详情
    @PostMapping("/checkBlog")
    public Map checkBlog(@RequestBody Blog blog, HttpServletRequest req) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("blog",blog);
        return blogService.checkBlog(map);
    }
    

    //删除博客
    @PostMapping("/deleteBlog")
    public Map deleteBlog(@RequestBody Blog blog) {
        return blogService.deleteBlog(blog);
    }

    //新增置顶博客
    @PostMapping("/toppingBlog")
    public Map ToppingBlog(@RequestBody Blog blog) {
        return blogService.toppingBlog(blog);
    }

    //修改置顶博客位置
    @PostMapping("/modifyBlogTop")
    public Map modifyBlogTop(@RequestBody Blog blog) {
        return blogService.modifyBlogTop(blog);
    }

    //取消置顶博客
    @PostMapping("/cancelToppingBlog")
    public Map cancelToppingBlog(@RequestBody Blog blog) {
        return blogService.cancelToppingBlog(blog);
    }


    /**
     * 板块管理模块
     *
     */
    //板块分页
    @GetMapping("/getPlates")
    public Map getPlates(int pageNum, int pageSize) {
        return plateService.getPlatePage(pageNum,pageSize);
    }

    //查看板块详情
    @PostMapping("/checkPlate")
    public Map checkPlate(@RequestBody Plate plate) {
        return plateService.checkPlate(plate);
    }

    //板块内博客分页
    @GetMapping("/getBlogsOfPlate")
    public Map getBlogsOfPlate(int pageNum,int pageSize,int id) {
        return blogService.getBlogsOfPlatePage(pageNum,pageSize,id);
    }

    //删除板块
    @PostMapping("/deletePlate")
    public Map deletePlate(@RequestBody Plate plate) {
        return plateService.deletePlate(plate);
    }

    //新增置顶板块
    @PostMapping("/toppingPlate")
    public Map toppingPlate(@RequestBody Plate plate) {
        return plateService.toppingPlate(plate);
    }

    //修改置顶板块位置
    @PostMapping("/modifyPlateTop")
    public Map modifyPlateTop(@RequestBody Plate plate) {
        return plateService.modifyPlateTop(plate);
    }

    //取消置顶板块
    @PostMapping("/cancelToppingPlate")
    public Map cancelToppingPlate(@RequestBody Plate plate) {
        return plateService.cancelToppingPlate(plate);
    }

    //冻结板块
    @PostMapping("/frozenPlate")
    public Map frozenPlate(@RequestBody Plate plate) {
        return plateService.frozenPlate(plate);
    }

    //解冻板块
    @PostMapping("/unfreezePlate")
    public Map unfreezePlate(@RequestBody Plate plate) {
        return plateService.unfreezePlate(plate);
    }


    /**
     * 板块申请管理模块
     *
     */
    //申请分页(分类查看:全部)
    @GetMapping("/getPlateApplication")
    public Map getPlateApplication(int pageNum, int pageSize) {
        return plateApplicationService.getPlateApplicationPage(pageNum,pageSize);
    }

    //申请分页(分类查看:未审核/通过/不通过)
    @GetMapping("/getPlateApplicationClassified")
    public Map getPlateApplication(int pageNum, int pageSize,int status) {
        return plateApplicationService.getPlateApplicationClassifiedPage(pageNum,pageSize,status);
    }

    //查看申请详情
    @PostMapping("/checkPlateApplication")
    public Map checkPlateApplication(@RequestBody PlateApplication plateApplication) {
        return plateApplicationService.checkPlateApplication(plateApplication);
    }

    //申请通过(通过并为该用户创建一个板块)
    @PostMapping("/passPlateApplication")
    public Map passPlateApplication(@RequestBody PlateApplication plateApplication) {
        return plateApplicationService.passPlateApplication(plateApplication);
    }

    //申请不通过
    @PostMapping("/overrulePlateApplication")
    public Map overrulePlateApplication(@RequestBody PlateApplication plateApplication) {
        return plateApplicationService.overrulePlateApplication(plateApplication);
    }

    /**
     * 公告管理模块
     *
     */
    //公告分页
    @GetMapping("/getManagerNotice")
    public Map getManagerNotice(int pageNum,int pageSize) {
        return managerNoticeService.getManagerNoticePage(pageNum,pageSize);
    }

    //发布公告
    @PostMapping("/addManagerNotice")
    public Map addManagerNotice(@RequestBody ManagerNotice managerNotice) {
        return managerNoticeService.addManagerNotice(managerNotice);
    }

    //删除公告
    @PostMapping("/deleteManagerNotice")
    public Map deleteManagerNotice(@RequestBody ManagerNotice managerNotice) {
        return managerNoticeService.deleteManagerNotice(managerNotice);
    }

    //查看公告详情
    @RequestMapping("/checkManageNotice")
    public Map checkManageNotice(@RequestBody ManagerNotice managerNotice) {
        return managerNoticeService.checkManageNotice(managerNotice);
    }

}
