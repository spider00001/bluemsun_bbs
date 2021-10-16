package com.bluemsun.controller;

import com.bluemsun.entity.*;
import com.bluemsun.service.manager.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
@RequestMapping("/bsmanager")
public class ManagerController extends HttpServlet {

    private final ManageUserService managerService;
    private final ManageBlogService manageBlogService;
    private final ManagePlateService managePlateService;
    private final ManagePlateApplicationService managePlateApplicationService;
    private final ManageManagerNoticeService manageManagerNoticeService;

    public ManagerController(ManageUserService managerService, ManageBlogService manageBlogService, ManagePlateService managePlateService, ManagePlateApplicationService managePlateApplicationService, ManageManagerNoticeService manageManagerNoticeService) {
        this.managerService = managerService;
        this.manageBlogService = manageBlogService;
        this.managePlateService = managePlateService;
        this.managePlateApplicationService = managePlateApplicationService;
        this.manageManagerNoticeService = manageManagerNoticeService;
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


    /**
     *用户管理模块
     *
    */
    //获取所有用户
    @GetMapping("/getUsers")
    public Map<String,Object> getUsers(int pageNum, int pageSize) {
        return managerService.getUsersPage(pageNum,pageSize);
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


    /**
     * 帖子管理模块
     *
     */
    //博客分页
    @GetMapping("/getBlogs")
    public Map getBlogs(int pageNum, int pageSize) {
        return manageBlogService.getBlogsPage(pageNum,pageSize);
    }

    //查看博客详情

    //删除博客(帖子详情页内)
    @PostMapping("/deleteBlog")
    public Map deleteBlog(@RequestBody Blog blog) {
        return manageBlogService.deleteBlog(blog);
    }

    //新增置顶博客
    @PostMapping("/toppingBlog")
    public Map ToppingBlog(@RequestBody Blog blog) {
        return manageBlogService.toppingBlog(blog);
    }

    //修改置顶博客位置
    @PostMapping("/modifyBlogTop")
    public Map modifyBlogTop(@RequestBody Blog blog) {
        return manageBlogService.modifyBlogTop(blog);
    }

    //取消置顶博客
    @PostMapping("/cancelToppingBlog")
    public Map cancelToppingBlog(@RequestBody Blog blog) {
        return manageBlogService.cancelToppingBlog(blog);
    }


    /**
     * 板块管理模块
     *
     */
    //板块分页
    @GetMapping("/getPlates")
    public Map getPlates(int pageNum, int pageSize) {
        return managePlateService.getPlatePage(pageNum,pageSize);
    }

    //删除板块
    @PostMapping("/deletePlate")
    public Map deletePlate(@RequestBody Plate plate) {
        return managePlateService.deletePlate(plate);
    }

    //新增置顶板块
    @PostMapping("/toppingPlate")
    public Map toppingPlate(@RequestBody Plate plate) {
        return managePlateService.toppingPlate(plate);
    }

    //修改置顶板块位置
    @PostMapping("/modifyPlateTop")
    public Map modifyPlateTop(@RequestBody Plate plate) {
        return managePlateService.modifyPlateTop(plate);
    }

    //取消置顶板块
    @PostMapping("/cancelToppingPlate")
    public Map cancelToppingPlate(@RequestBody Plate plate) {
        return managePlateService.cancelToppingPlate(plate);
    }

    //冻结板块
    @PostMapping("/frozenPlate")
    public Map frozenPlate(@RequestBody Plate plate) {
        return managePlateService.frozenPlate(plate);
    }

    //解冻板块
    @PostMapping("/unfreezePlate")
    public Map unfreezePlate(@RequestBody Plate plate) {
        return managePlateService.unfreezePlate(plate);
    }


    /**
     * 板块申请管理模块
     *
     */
    //申请分页(分类查看:全部)
    @GetMapping("/getPlateApplication")
    public Map getPlateApplication(int pageNum, int pageSize) {
        return managePlateApplicationService.getPlateApplicationPage(pageNum,pageSize);
    }

    //申请分页(分类查看:未审核/通过/不通过)
    @GetMapping("/getPlateApplicationClassified")
    public Map getPlateApplication(int pageNum, int pageSize,int status) {
        return managePlateApplicationService.getPlateApplicationClassifiedPage(pageNum,pageSize,status);
    }

    //查看申请详情
    @PostMapping("/checkPlateApplication")
    public Map checkPlateApplication(@RequestBody PlateApplication plateApplication) {
        return managePlateApplicationService.checkPlateApplication(plateApplication);
    }

    //申请通过(通过并为该用户创建一个板块)
    @PostMapping("/passPlateApplication")
    public Map passPlateApplication(@RequestBody PlateApplication plateApplication) {
        return managePlateApplicationService.passPlateApplication(plateApplication);
    }

    //申请不通过
    @PostMapping("/overrulePlateApplication")
    public Map overrulePlateApplication(@RequestBody PlateApplication plateApplication) {
        return managePlateApplicationService.overrulePlateApplication(plateApplication);
    }

    /**
     * 公告管理模块
     *
     */
    //公告分页
    @GetMapping("/getManagerNotice")
    public Map getManagerNotice(int pageNum,int pageSize) {
        return manageManagerNoticeService.getManagerNoticePage(pageNum,pageSize);
    }

    //发布公告
    @PostMapping("/addManagerNotice")
    public Map addManagerNotice(@RequestBody ManagerNotice managerNotice) {
        return manageManagerNoticeService.addManagerNotice(managerNotice);
    }

    //删除公告
    @PostMapping("/deleteManagerNotice")
    public Map deleteManagerNotice(@RequestBody ManagerNotice managerNotice) {
        return manageManagerNoticeService.deleteManagerNotice(managerNotice);
    }

}
