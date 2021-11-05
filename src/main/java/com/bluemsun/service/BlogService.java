package com.bluemsun.service;

import com.bluemsun.dto.BlogUserDto;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Plate;

import java.util.Map;

public interface BlogService {

    //博客分页(|全部类型)
    Map getBlogsPage(int pageNum, int pageSize);

    //板块内博客分页
    Map getBlogsOfPlatePage(int pageNum, int pageSize,int id);

    //获取用户博客分页
    Map getUserBlogsPage(int pageNum, int pageSize,int userId);

    //删除博客
    Map deleteBlog(Blog blog);

    //获取首页置顶博客
    Map getBlogsHomeTop();

    //新增首页置顶博客
    Map toppingBlog(Blog blog);

    //修改首页置顶位置
    Map modifyBlogTop(Blog blog);

    //取消首页置顶
    Map cancelToppingBlog(Blog blog);

    //获取板块置顶博客list
    Map getPlateBlogsHomeTop(Plate plate);

    //新增板块置顶博客
    Map toppingPlateBlog(Map map);

    //修改板块置顶博客块位置
    Map modifyPlateBlogTop(Map map);

    //取消板块置顶博客
    Map cancelToppingPlateBlog(Map map);

    //发布博客
    Map releaseBlog(BlogUserDto blogUserDto);

    //查看博客详情
    Map<String,Object> checkBlog(Blog blog, int userId);

    //编辑博客
    Map updateBlog(Blog blog);

    //点赞博客
    Map likeBlog(Map map);

    //取消点赞博客
    Map cancelLikeBlog(Map map);

    //搜索博客分页
     Map selectBlogPage(int pageNum,int pageSize, String title);

}
