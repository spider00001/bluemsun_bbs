package com.bluemsun.controller;

import com.bluemsun.entity.*;
import com.bluemsun.service.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

    private final BlogService blogService;
    private final PlateService plateService;
    private final UserService userService;
    private final CommentService commentService;
    private final PlateApplicationService plateApplicationService;
    private final PlateNoticeService plateNoticeService;

    public UserController(BlogService blogService, PlateService plateService, UserService userService, CommentService commentService, PlateApplicationService plateApplicationService, PlateNoticeService plateNoticeService) {
        this.blogService = blogService;
        this.plateService = plateService;
        this.userService = userService;
        this.commentService = commentService;
        this.plateApplicationService = plateApplicationService;
        this.plateNoticeService = plateNoticeService;
    }

    /**
     * 首页 (置顶博客，置顶板块)
     *
     */
    //查看置顶博客list
    @GetMapping("/getBlogsHomeTop")
    public Map getBlogsHomeTop() {
        return blogService.getBlogsHomeTop();
    }

    //查看置顶板块list
    @GetMapping("/getPlatesOfHome")
    public Map getPlatesOfHome() {
        return plateService.getPlatesOfHome();
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
     * 用户信息模块
     *
     */
    //注册
    @PostMapping("/register")
    public Map register(@RequestBody User user, HttpServletRequest req) {
        Map map = userService.addUser(user);
        if (map.containsKey("user")) {
            req.getSession().setAttribute("user",map.get("user"));
        }
        return map;
    }

    //登录
    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpServletRequest req) {
        Map map = userService.userLogin(user);
        if (map.containsKey("user")) {
            req.getSession().setAttribute("user",map.get("user"));
        }
        return map;
    }

    //个人信息修改
    @PostMapping("/updateUser")
    public Map updateUser(@RequestBody User user, HttpServletRequest req) {
        user.setId(((User) req.getSession().getAttribute("user")).getId());
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
        //用于更新session中的用户信息
        User user = (User) req.getSession().getAttribute("user");
        return userService.checkUser(user);
    }

    //头像上传

    //查看关注列表
    @GetMapping("/getFollowUsers")
    public Map getFollowUsers(int pageNum, int pageSize, int id) {
        return userService.getFollowUsers(pageNum,pageSize,id);
    }

    //查看粉丝列表
    @GetMapping("/getFans")
    public Map getFans(int pageNum, int pageSize, int id) {
        return userService.getFans(pageNum,pageSize,id);
    }

    //查看用户详情
    @PostMapping("/checkUser")
    public Map checkUser(@RequestBody User user) {
        return userService.checkUser(user);
    }

    //关注
    @PostMapping("/followUser")
    public Map followUser(@RequestBody Map map, HttpServletRequest req) {
        map.put("id",((User)req.getSession().getAttribute("user")).getId());
        return userService.followUser(map);
    }

    //取关
    @PostMapping("/cancelFollowUser")
    public Map cancelFollowUser(@RequestBody Map map, HttpServletRequest req) {
        map.put("id",((User)req.getSession().getAttribute("user")).getId());
        return userService.cancelFollowUser(map);
    }

    /**
     * 我的创作模块
     *
     */

    //发布博客前 或者 编辑后重新发布博客前。 要选择自己要发布到的板块(分页)
    @GetMapping("/getAvailablePlatePage")
    public Map getAvailablePlatePage(int pageNum, int pageSize) {
        return plateService.getAvailablePlatePage(pageNum,pageSize);
    }

    //发布文件下载的博客

    //发布博客(要选择自己的博客在哪个板块)
    @PostMapping("/releaseBlog")
    public Map releaseBlog(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",(((User)req.getSession().getAttribute("user"))).getId());
        return blogService.releaseBlog(map);
    }

    //查看博客详情
    @PostMapping("/checkBlog")
    public Map checkBlog(@RequestBody Blog blog, HttpServletRequest req) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("user",req.getSession().getAttribute("user"));
        map.put("blog",blog);
        return blogService.checkBlog(map);
    }

    //删除自己的博客(只能在“我的创作”里面的博客分页时删除)
    @PostMapping("/deleteBlog")
    public Map deleteBlog(@RequestBody Blog blog) {
        return blogService.deleteBlog(blog);
    }

    //重新发布前要选择自己博客所在板块
    //1.重新选择
    @PostMapping("/updateBlogPlate")
    public Map updateBlogPlate(@RequestBody Map map) {
        return plateService.updateBlogPlate(map);
    }
    //2.取消选择
    @PostMapping("/deselectPlate")
    public Map deselectPlate(@RequestBody Map map) {
        return plateService.deselectPlate(map);
    }

    //编辑后重新发布博客
    @PostMapping("/updateBlog")
    public Map updateBlog(@RequestBody Blog blog) {
        return blogService.updateBlog(blog);
    }


    //查看用户(别人或者自己的)博客分页
    @GetMapping("/getUserBlogsPage")
    public Map getUserBlogsPage(int pageNum, int pageSize,int id) {
        return blogService.getUserBlogsPage(pageNum,pageSize,id);
    }

    //点赞博客
    @PostMapping("/likeBlog")
    public Map likeBlog(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return blogService.likeBlog(map);
    }

    //取消点赞博客
    @PostMapping("/cancelLikeBlog")
    public Map cancelLikeBlog(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return blogService.cancelLikeBlog(map);
    }

    //博客主评论区分页(时间戳没解决)
    @GetMapping("/getMainCommentPage")
    public Map getMainCommentPage(int pageNum, int pageSize,int blogId) {
        return commentService.getMainCommentPage(pageNum,pageSize,blogId);
    }

    //评论回复评论区分页(时间戳没解决)(加入了判断是否为自己的评论,未成功)
    @GetMapping("/getInsideCommentPage")
    public Map getInsideCommentPage(int pageNum, int pageSize,int commentMainId) {
        return commentService.getInsideCommentPage(pageNum,pageSize,commentMainId);
    }

    //评论博客
    @PostMapping("/addMainComment")
    public Map addMainComment(@RequestBody MainComment mainComment, HttpServletRequest req) {
        mainComment.setUserId(((User) req.getSession().getAttribute("user")).getId());
        return commentService.addMainComment(mainComment);
    }

//    //删除自己的博客评论(可能会让前端判断是否未自己的评论，如果可以的话，这个接口就没意义了)
//    @PostMapping("/deleteMyMainComment")
//    public Map deleteMyMainComment(@RequestBody MainComment mainComment, HttpServletRequest req) {
//        mainComment.setUserId(((User) req.getSession().getAttribute("user")).getId());
//        return commentService.deleteMyMainComment(mainComment);
//    }

    //删除博客评论 (如果是在自己的博客内; 如果是自己的博客评论. <打算让前端判断是否为自己的博客或者自己的评论....>  )
    @PostMapping("/deleteMainComment")
    public Map deleteMainComment(@RequestBody MainComment mainComment) {
        return commentService.deleteMyMainComment(mainComment);
    }

    //回复评论
    @PostMapping("/addInsideComment")
    public Map addInsideComment(@RequestBody InsideComment insideComment, HttpServletRequest req) {
        insideComment.setUserId(((User) req.getSession().getAttribute("user")).getId());
        return commentService.addInsideComment(insideComment);
    }

//    //删除自己的评论回复
//    @PostMapping("/deleteMyInsideComment")
//    public Map deleteMyInsideComment(@RequestBody InsideComment insideComment, HttpServletRequest req) {
//        insideComment.setUserId(((User) req.getSession().getAttribute("user")).getId());
//        return commentService.deleteMyInsideComment(insideComment);
//    }

    //删除评论回复 (如果是在自己的博客内,如果是自己的回复)
    @PostMapping("/deleteInsideComment")
    public Map deleteInsideComment(@RequestBody InsideComment insideComment, HttpServletRequest req) {
        return commentService.deleteMyInsideComment(insideComment);
    }

    //点赞博客评论
    @PostMapping("/likesMainComment")
    public Map likesMainComment(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return commentService.likesMainComment(map);
    }

    //取消点赞博客评论
    @PostMapping("/cancelLikesMainComment")
    public Map cancelLikesMainComment(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return commentService.cancelLikesMainComment(map);
    }

    //点赞评论回复
    @PostMapping("/likeInsideComment")
    public Map likeInsideComment(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return commentService.likeInsideComment(map);
    }

    //取消点赞评论回复
    @PostMapping("/cancelLikesInsideComment")
    public Map cancelLikesInsideComment(@RequestBody Map map, HttpServletRequest req) {
        map.put("userId",((User) req.getSession().getAttribute("user")).getId());
        return commentService.cancelLikesInsideComment(map);
    }


    /**
     * 板块模块
     *
     */
    //申请板块
    @PostMapping("/addPlateApplication")
    public Map addPlateApplication(@RequestBody PlateApplication plateApplication, HttpServletRequest req) {
        plateApplication.setUserId(((User)req.getSession().getAttribute("user")).getId());
        return plateApplicationService.addPlateApplication(plateApplication);
    }

    //查看我的板块申请list(分页)
    @GetMapping("/getMyPlateApplicationPage")
    public Map getMyPlateApplicationPage(int pageNum, int pageSize, HttpServletRequest req) {
        int userId = ((User)req.getSession().getAttribute("user")).getId();
        return plateApplicationService.getMyPlateApplicationPage(pageNum,pageSize,userId);
    }

    //查看板块申请详情
    @PostMapping("/checkPlateApplication")
    public Map checkPlateApplication(@RequestBody PlateApplication plateApplication) {
        return plateApplicationService.checkPlateApplication(plateApplication);
    }

    //查看板块详情
    @PostMapping("/checkPlate")
    public Map checkPlate(@RequestBody Plate plate) {
        return plateService.checkPlate(plate);
    }

    //修改简介
    @PostMapping("/updatePlateDescription")
    public Map updatePlateDescription(@RequestBody Plate plate) {
        return plateService.updatePlateDescription(plate);
    }

    //删除自己的板块
    @PostMapping("/deletePlate")
    public Map deletePlate(@RequestBody Plate plate) {
        return plateService.deletePlate(plate);
    }

    //板块内博客分页(查看板块详情后)
    @GetMapping("/getBlogsOfPlate")
    public Map getBlogsOfPlate(int pageNum,int pageSize,int id) {
        return blogService.getBlogsOfPlatePage(pageNum,pageSize,id);
    }

   //删除板块内博客(仅从板块内移除)
    @PostMapping("/deleteBlogFromPlate")
    public Map deleteBlogFromPlate(@RequestBody Map map) {
        return blogService.deleteBlogFromPlate(map);
    }

    //板块公告分页
    @GetMapping("/getPlateNoticePage")
    public Map getPlateNoticePage(int pageNum, int pageSize, int plateId) {
        return plateNoticeService.getPlateNoticePage(pageNum,pageSize, plateId);
    }

    //查看板块公告详情
    @PostMapping("/checkPlateNotice")
    public Map checkPlateNotice(@RequestBody PlateNotice plateNotice) {
        return plateNoticeService.checkPlateNotice(plateNotice);
    }

    //发布板块公告(板块公告分页后)
    @PostMapping("/addPlateNotice")
    public Map addPlateNotice(@RequestBody PlateNotice plateNotice) {
        return plateNoticeService.addPlateNotice(plateNotice);
    }

    //删除板块公告(查看板块公告详情后)
    @PostMapping("/deletePlateNotice")
    public Map deletePlateNotice(@RequestBody PlateNotice plateNotice) {
        return plateNoticeService.deletePlateNotice(plateNotice);
    }

    //查看板块置顶博客list(进入板块详情后调；置顶板块博客时先调这个，前端判断是否可以置顶)
    @PostMapping("/getPlateBlogsHomeTop")
    public Map getPlateBlogsHomeTop(@RequestBody Plate plate) {
        return blogService.getPlateBlogsHomeTop(plate);
    }

    //置顶博客:新增板块置顶博客
    @PostMapping("/toppingPlateBlog")
    public Map toppingPlateBlog(@RequestBody Map map) {
        return blogService.toppingPlateBlog(map);
    }

    //置顶博客:修改板块置顶博客位置
    @PostMapping("/modifyPlateBlogTop")
    public Map modifyPlateBlogTop(@RequestBody Map map) {
        return blogService.modifyPlateBlogTop(map);
    }

    //置顶博客:取消板块置顶博客
    @PostMapping("/cancelToppingPlateBlog")
    public Map cancelToppingPlateBlog(@RequestBody Map map) {
        return blogService.cancelToppingPlateBlog(map);
    }

    //博客迁移(如果自己有两个板块的话) (博客详情内)

    //删除板块内博客



    /**
     * 板块分类模块
     *
     */

    //首页(只展示各种板块)


}
