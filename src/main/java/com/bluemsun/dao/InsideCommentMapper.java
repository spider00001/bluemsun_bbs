package com.bluemsun.dao;

import com.bluemsun.entity.InsideComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InsideCommentMapper {

    //回复评论
    int addInsideComment(InsideComment insideComment);

    //删除自己的评论回复
    int deleteMyInsideComment(InsideComment insideComment);

    //点赞评论回复
    int likeInsideComment(Map map);

    //取消点赞评论回复
    int cancelLikeInsideComment(Map map);

    //获取评论回复总数
    int getInsideCommentCount(@Param("commentMainId") int commentMainId);

    //获取评论分页回复list
    List<InsideComment> getInsideCommentLimit(Map map);

}
