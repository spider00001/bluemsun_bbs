package com.bluemsun.dao;

import com.bluemsun.dto.BlogUserDto;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Plate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    //全部博客数量
    int getBlogCount();

    //获取所有博客分页list
    List<Blog> getBlogsLimit(Map map);

    //板块博客数量
    int getBlogOfPlateCount(@Param("id") int id);

    //获取板块内博客分页list
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
    int releaseBlog(BlogUserDto blogUserDto);

    //查找用户刚发布的博客的id
    int selectUserJustReleaseBlogId(@Param("userId") int userId);

    //查看博客详情
    BlogUserDto checkBlog(Blog blog);

    //查看博客是否被自己赞过
    int isBlogLiked(Map map);

    //编辑博客
    int updateBlog(Blog blog);

    //点赞博客
    int likeBlog(Map map);

    //取消点赞博客
    int cancelLikeBolg(Map map);

    //搜索博客总数
    int getSelectBlogCount(@Param("title") String title);

    //搜索博客list
    List<Blog> selectBlogList(Map map);

    //redis内博客对应浏览量加一
    boolean addBlogViewsOfRedis(int blogId, int userId);

    //获取redis里博客的浏览量
    long getBlogViewsOfRedis(Blog blog);

    //更新数据库里面博客浏览量
    int updateBlogViews(Blog blog);

    //获取数据库里博客的浏览量
    long getBlogViews(@Param("blogId") int blogId);

    //点赞数加一
    int addLikes(@Param("blogId") int blogId);

    //点赞数减一
    int reduceLikes(@Param("blogId") int blogId);

    //更新博客热度
    int updateBlogHeat(Blog blog);

    //获取所有博客
    List<Blog> getAllBlogs();

    //更新MYSql里所有博客浏览量
    void updateAllBlogsViews();

    //更新博客热度
    void updateAllBlogsHeat();

    //获取热门博客5个
    List<Blog> getHeatTopBlogs();

}
