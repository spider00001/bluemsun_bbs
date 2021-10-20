package com.bluemsun.dao;

import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Plate;
import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    //全部博客数量
    int getBlogCount();
    //获取所有博客list
    List<Blog> getBlogsLimit(Map map);

    //板块博客数量
    int getBlogOfPlateCount(@Param("id") int id);

    //获取板块内博客list
    List<Blog> getBlogsOfPlateLimit(Map map);

    //获取用户博客数量
    int getUserBlogCount(@Param("id") int id);

    List<Blog> getUserBlogsLimit(Map map);

    //删除一篇博客
    int deleteBlog(Blog blog);

    //获取首页置顶博客list
    List<Blog> getBlogsHomeTop();

    //新增首页置顶博客
    int toppingBlog(Blog blog);

    //修改首页置顶博客块位置
    int modifyBlogTop(Blog blog);

    //取消首页置顶博客
    int cancelToppingBlog(Blog blog);

    //获取板块置顶博客list
    List<Blog> getPlateBlogsHomeTop(Plate plate);

    //新增板块置顶博客
    int toppingPlateBlog(Map map);

    //修改板块置顶博客块位置
    int modifyPlateBlogTop(Map map);

    //取消板块置顶博客
    int cancelToppingPlateBlog(Map map);

    //发布博客
    int releaseBlog(Map map);

    //查找用户刚发布的博客的id
    int selectUserJustReleaseBlogId(@Param("userId") int userId);

    //查看博客详情
    Blog checkBlog(Blog blog);

    //查看博客是否被自己赞过
    int isBlogLiked(Map map);

    //编辑博客
    int updateBlog(Blog blog);

    //点赞博客
    int likeBlog(Map map);

    //取消点赞博客
    int cancelLikeBolg(Map map);

}
