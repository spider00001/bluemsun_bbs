package com.bluemsun.dao.impl;

import com.bluemsun.dao.MainCommentMapper;
import com.bluemsun.entity.MainComment;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MainCommentMapperImpl extends SqlSessionDaoSupport implements MainCommentMapper {

    //评论博客
    @Override
    public int addMainComment(MainComment mainComment) {
        int row = 0;
        try {
            mainComment.setCreateTime(new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(MainCommentMapper.class).addMainComment(mainComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //删除自己的评论
    @Override
    public int deleteMyMainComment(MainComment mainComment) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(MainCommentMapper.class).deleteMyMainComment(mainComment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int likesMainComment(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(MainCommentMapper.class).likesMainComment(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int cancelLikesMainComment(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(MainCommentMapper.class).cancelLikesMainComment(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int getMainCommentCount(int blogId) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(MainCommentMapper.class).getMainCommentCount(blogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<MainComment> getMainCommentLimit(Map map) {
        List<MainComment> mainCommentList = null;
        try {
            mainCommentList = getSqlSession().getMapper(MainCommentMapper.class).getMainCommentLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainCommentList;
    }

    @Override
    public int addLikes(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(MainCommentMapper.class).addLikes(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reduceLikes(int id) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(MainCommentMapper.class).reduceLikes(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


}
