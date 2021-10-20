package com.bluemsun.dao;

import com.bluemsun.entity.Blog;
import com.bluemsun.entity.MainComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MainCommentMapper {

    //评论博客
    int addMainComment(MainComment mainComment);

    //删除自己的评论
    int deleteMyMainComment(MainComment mainComment);

    //点赞博客评论
    int likesMainComment(Map map);

    //取消点赞博客评论
    int cancelLikesMainComment(Map map);

    //获取博客的所有博客评论数量
    int getMainCommentCount(@Param("blogId") int blogId);

    List<MainComment> getMainCommentLimit(Map map);


}
