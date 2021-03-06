package com.bluemsun.controller;

import com.bluemsun.dto.BlogUserDto;
import com.bluemsun.entity.*;
import com.bluemsun.service.*;
import com.bluemsun.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {

    private final BlogService blogService;
    private final PlateService plateService;
    private final UserService userService;
    private final CommentService commentService;
    private final PlateApplicationService plateApplicationService;
    private final PlateNoticeService plateNoticeService;
    private final ManagerNoticeService managerNoticeService;

    public UserController(BlogService blogService, PlateService plateService, UserService userService, CommentService commentService, PlateApplicationService plateApplicationService, PlateNoticeService plateNoticeService, ManagerNoticeService managerNoticeService) {
        this.blogService = blogService;
        this.plateService = plateService;
        this.userService = userService;
        this.commentService = commentService;
        this.plateApplicationService = plateApplicationService;
        this.plateNoticeService = plateNoticeService;
        this.managerNoticeService = managerNoticeService;
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

    //最新管理员公告
    @GetMapping("/getManagerNotice")
    public Map getManagerNotice(int pageNum,int pageSize) {
        return managerNoticeService.getManagerNoticePage(pageNum,pageSize);
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

    /**
     * 用户信息模块
     *
     */
    //注册
    @PostMapping("/register")
    public Map register(@RequestBody User user, HttpServletResponse response) {
        Map map = userService.addUser(user);
        if (map.containsKey("user")) {
            String token = JWTUtil.generateToken(((Integer)user.getId()).toString(),"Bob","BBSUser");
            response.setHeader("token", token);
        }
        return map;
    }

    //登录
    @PostMapping("/login")
    public Map login(@RequestBody User user, HttpServletResponse response) {
        Map map = userService.userLogin(user);
        if (map.containsKey("user")) {
            String token = JWTUtil.generateToken(((Integer)((User)map.get("user")).getId()).toString(),"Bob","BBSUser");
            response.setHeader("token", token);
        }
        return map;
    }

    //退出登录
    @PostMapping("/userLogOut")
    public Map userLogOut(HttpServletRequest req) {
        userService.userLogOut(req.getHeader("token"));
        Map map = new HashMap();
        map.put("msg","退出登录成功");
        map.put("status",1);
        return map;
    }

    //用户头像上传
    @PostMapping(value="/uploadHeadPortrait")
    public Map uploadHeadPortrait(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletRequest req) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if (file.getSize() <= 2097152) {
                String disposition = file.getOriginalFilename();
                String suffix = disposition.substring(disposition.lastIndexOf("."));
                if (suffix.contains("png")||suffix.contains("jpg")) {
                    //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
                    String filename = UUID.randomUUID()+suffix;
                    String serverPath = request.getSession().getServletContext().getRealPath("headPortrait");
                    File fileDisk = new File(serverPath);
                    if (!fileDisk.exists()) {
                        fileDisk.mkdir();
                    }
                    String fileParts = serverPath + "/" + filename;
                    file.transferTo(new File(fileParts));
                    String projectServerPath = request.getScheme() + "://" + request.getServerName() +
                            ":" + request.getServerPort() + request.getContextPath() + "/headPortrait/" + filename;
                    User user = new User();
                    Claims token = JWTUtil.verifyToken(req.getHeader("token"));
                    user.setId(Integer.parseInt(token.getId()));
                    user.setHeadPortrait(projectServerPath);
                    userService.updateHeadPortrait(user);
                    map.put("msg","上传成功");
                    map.put("status",1);
                } else {
                    map.put("msg","上传失败,只支持.png或.jpg格式的图片！");
                    map.put("status",2);
                }
            } else {
                map.put("msg","上传失败,上传图片最大2M！");
                map.put("status",2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    //个人信息修改
    @PostMapping("/updateUser")
    public Map updateUser(@RequestBody User user, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        user.setId(Integer.parseInt(token.getId()));
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
        Map map = new HashMap();
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("id",Integer.parseInt(token.getId()));
        return userService.checkUser(map);
    }

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
    public Map checkUser(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return userService.checkUser(map);
    }

    //关注
    @PostMapping("/followUser")
    public Map followUser(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("id",Integer.parseInt(token.getId()));
        return userService.followUser(map);
    }

    //取关
    @PostMapping("/cancelFollowUser")
    public Map cancelFollowUser(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("id",Integer.parseInt(token.getId()));
        return userService.cancelFollowUser(map);
    }

    /**
     * 我的创作模块
     *
     */
    //资源博客上传文件
    @PostMapping("/uploadBlogResourceFile")
    public Map uploadBlogResourceFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if (file.getSize() <= 104857600) {
                String disposition = file.getOriginalFilename();
                String suffix = disposition.substring(disposition.lastIndexOf("."));
                //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
                String filename = UUID.randomUUID()+suffix;
                String serverPath = request.getSession().getServletContext().getRealPath("blogResourceFile");
                File fileDisk = new File(serverPath);
                if (!fileDisk.exists()) {
                    fileDisk.mkdir();
                }
                String fileParts = serverPath + File.separator + filename;
                file.transferTo(new File(fileParts));
                map.put("msg","上传成功");
                map.put("status",1);
                map.put("fileURL",fileParts);
            } else {
                map.put("msg","上传失败,文件太大");
                map.put("status",2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //资源博客下载文件
    @PostMapping("/downloadBlogResourceFile")
    public void downloadBlogResourceFile(@RequestBody Map map, HttpServletResponse resp, HttpServletRequest request) {
        try {
            String blogResourceFile = (String) map.get("fileURL");
            String path = request.getSession().getServletContext().getRealPath("fileURL")+ File.separator + blogResourceFile;
            File file = new File(path);
            //设置响应头信息
            resp.addHeader("Content-Disposition","attachemt;filename="+file.getName());
            //设置文件ContentType类型，这样设置，会自动判断下载文件类型
            resp.setContentType("multipart/form-data");
            InputStream bis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
            int len;
            while((len = bis.read()) != -1){
                out.write(len);
                out.flush();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发布博客前 或者 编辑后重新发布博客前。 要选择自己要发布到的板块(分页)
    @GetMapping("/getAvailablePlatePage")
    public Map getAvailablePlatePage(int pageNum, int pageSize) {
        return plateService.getAvailablePlatePage(pageNum,pageSize);
    }

    //发布博客(要选择自己的博客在哪个板块)
    @PostMapping("/releaseBlog")
    public Map releaseBlog(@RequestBody BlogUserDto blogUserDto, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        blogUserDto.setUserId(Integer.parseInt(token.getId()));
        return blogService.releaseBlog(blogUserDto);
    }

    //查看博客详情
    @PostMapping("/checkBlog")
    public Map checkBlog(@RequestBody Blog blog, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        return blogService.checkBlog(blog,Integer.parseInt(token.getId()));
    }

    //删除自己的博客(只能在“我的创作”里面的博客分页时删除)
    @PostMapping("/deleteBlog")
    public Map deleteBlog(@RequestBody Blog blog) {
        return blogService.deleteBlog(blog);
    }

    //重新发布前要选择自己博客所在板块
    //1.选择博客所在板块 ; 移动博客到其他自己的板块(先查看我的板块)
    @PostMapping("/releaseBlogInPlate")
    public Map updateBlogPlate(@RequestBody Map map) {
        return plateService.releaseBlogInPlate(map);
    }
    //2.取消选择博客所在板块
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
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return blogService.likeBlog(map);
    }

    //取消点赞博客
    @PostMapping("/cancelLikeBlog")
    public Map cancelLikeBlog(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
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
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        mainComment.setUserId(Integer.parseInt(token.getId()));
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
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        insideComment.setUserId(Integer.parseInt(token.getId()));
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
    public Map deleteInsideComment(@RequestBody InsideComment insideComment) {
        return commentService.deleteMyInsideComment(insideComment);
    }

    //点赞博客评论
    @PostMapping("/likesMainComment")
    public Map likesMainComment(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return commentService.likesMainComment(map);
    }

    //取消点赞博客评论
    @PostMapping("/cancelLikesMainComment")
    public Map cancelLikesMainComment(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return commentService.cancelLikesMainComment(map);
    }

    //点赞评论回复
    @PostMapping("/likeInsideComment")
    public Map likeInsideComment(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return commentService.likeInsideComment(map);
    }

    //取消点赞评论回复
    @PostMapping("/cancelLikesInsideComment")
    public Map cancelLikesInsideComment(@RequestBody Map map, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        map.put("userId",Integer.parseInt(token.getId()));
        return commentService.cancelLikesInsideComment(map);
    }


    /**
     * 板块模块
     *
     */
    //查重板块名称
    @GetMapping("/isPlateNameExist")
    public Map isPlateNameExist(String plateName) {
        return plateService.isPlateNameExist(plateName);
    }

    //申请板块
    @PostMapping("/addPlateApplication")
    public Map addPlateApplication(@RequestBody PlateApplication plateApplication, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        plateApplication.setUserId(Integer.parseInt(token.getId()));
        return plateApplicationService.addPlateApplication(plateApplication);
    }

    //查看我的板块申请list(分页)
    @GetMapping("/getMyPlateApplicationPage")
    public Map getMyPlateApplicationPage(int pageNum, int pageSize, HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        return plateApplicationService.getMyPlateApplicationPage(pageNum,pageSize,Integer.parseInt(token.getId()));
    }

    //查看板块申请详情
    @PostMapping("/checkPlateApplication")
    public Map checkPlateApplication(@RequestBody PlateApplication plateApplication) {
        return plateApplicationService.checkPlateApplication(plateApplication);
    }

    //我的板块
    @PostMapping("/checkUserPlate")
    public Map checkUserPlate(HttpServletRequest req) {
        Claims token = JWTUtil.verifyToken(req.getHeader("token"));
        User user = new User();
        user.setId(Integer.parseInt(token.getId()));
        return plateService.checkUserPlate(user);
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

    //定时(每天0点)写入博客浏览量到MYSql数据库,及更新博客热度
//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 */10 * * * ?")//10分钟更新一次
    public void updateAllBlogsViews() {
        blogService.updateAllBlogsViews();
        blogService.updateAllBlogsHeat();
    }

    //获取热门博客
    @GetMapping("/getHeatTopBlogs")
    public Map getHeatTopBlogs() {
        return blogService.getHeatTopBlogs();
    }

}
