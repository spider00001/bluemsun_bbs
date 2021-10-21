package com.bluemsun.dao.impl;

import com.bluemsun.dao.InsideCommentMapper;
import com.bluemsun.entity.InsideComment;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class InsideCommentMapperImpl extends SqlSessionDaoSupport implements InsideCommentMapper {

    //回复评论
    @Override
    public int addInsideComment(InsideComment insideComment) {
        int row = 0;
        try {
            insideComment.setCreateTime(new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(InsideCommentMapper.class).addInsideComment(insideComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteMyInsideComment(InsideComment insideComment) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(InsideCommentMapper.class).deleteMyInsideComment(insideComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int likeInsideComment(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(InsideCommentMapper.class).likeInsideComment(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int cancelLikeInsideComment(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(InsideCommentMapper.class).cancelLikeInsideComment(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int getInsideCommentCount(int commentMainId) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(InsideCommentMapper.class).getInsideCommentCount(commentMainId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<InsideComment> getInsideCommentLimit(Map map) {
        List<InsideComment> insideCommentList = null;
        try {
            insideCommentList = getSqlSession().getMapper(InsideCommentMapper.class).getInsideCommentLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insideCommentList;
    }

    @Override
    public int addLikes(int commentMainId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(InsideCommentMapper.class).addLikes(commentMainId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reduceLikes(int commentMainId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(InsideCommentMapper.class).reduceLikes(commentMainId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


}
