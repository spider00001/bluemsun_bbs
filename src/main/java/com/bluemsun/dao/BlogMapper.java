package com.bluemsun.dao;

import com.bluemsun.entity.Blog;
import com.bluemsun.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper {

    //全部
    int getBlogCount();
    //获取所有用户博客list
    List<Blog> getBlogsLimit(Map map);
    //删除一篇博客
    int deleteBlog(Blog blog);

}
