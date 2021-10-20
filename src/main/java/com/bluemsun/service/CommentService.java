package com.bluemsun.service;

import com.bluemsun.entity.InsideComment;
import com.bluemsun.entity.MainComment;

import java.util.Map;

public interface CommentService {

    //评论博客
    Map addMainComment(MainComment mainComment);

    //删除自己的评论
    Map deleteMyMainComment(MainComment mainComment);

    //回复评论
    Map addInsideComment(InsideComment insideComment);

    //删除自己的评论回复
    Map deleteMyInsideComment(InsideComment insideComment);

    //点赞博客评论
    Map likesMainComment(Map map);

    //取消点赞博客
    Map cancelLikesMainComment(Map map);

    //点赞评论回复
    Map likeInsideComment(Map map);

    //取消点赞评论回复
    Map cancelLikesInsideComment(Map map);

    //获取博客的博客评论分页
    Map getMainCommentPage(int pageNum, int pageSize,int blogId);

    //获取评论回复的分页
    Map getInsideCommentPage(int pageNum, int pageSize,int commentMainId);

}
