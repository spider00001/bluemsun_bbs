package com.bluemsun.service.impl;

import com.bluemsun.dao.InsideCommentMapper;
import com.bluemsun.dao.MainCommentMapper;
import com.bluemsun.entity.InsideComment;
import com.bluemsun.entity.MainComment;
import com.bluemsun.entity.Page;
import com.bluemsun.service.CommentService;

import java.util.HashMap;
import java.util.Map;

public class CommentServiceImpl implements CommentService {

    private final MainCommentMapper mainCommentMapper;
    private final InsideCommentMapper insideCommentMapper;

    public CommentServiceImpl(MainCommentMapper mainCommentMapper, InsideCommentMapper insideCommentMapper) {
        this.mainCommentMapper = mainCommentMapper;
        this.insideCommentMapper = insideCommentMapper;
    }

    @Override
    public Map addMainComment(MainComment mainComment) {
        int row = mainCommentMapper.addMainComment(mainComment);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","评论博客成功");
            map.put("status",1);
        } else {
            map.put("msg","评论博客失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map deleteMyMainComment(MainComment mainComment) {
        int row = mainCommentMapper.deleteMyMainComment(mainComment);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除博客评论成功");
            map.put("status",1);
        } else {
            map.put("msg","删除博客评论失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map addInsideComment(InsideComment insideComment) {
        int row = insideCommentMapper.addInsideComment(insideComment);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","回复评论成功");
            map.put("status",1);
        } else {
            map.put("msg","回复评论失败");
            map.put("status",2);
        }
        return map;
    }

    //删除自己的评论回复
    @Override
    public Map deleteMyInsideComment(InsideComment insideComment) {
        int row = insideCommentMapper.deleteMyInsideComment(insideComment);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除评论回复成功");
            map.put("status",1);
        } else {
            map.put("msg","删除评论回复失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map likesMainComment(Map map) {
        int row = mainCommentMapper.likesMainComment(map);
        int row1 = mainCommentMapper.addLikes((int)map.get("commentMainId"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0 && row1 > 0) {
            mapRes.put("msg","点赞博客评论成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","点赞博客评论失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map cancelLikesMainComment(Map map) {
        int row = mainCommentMapper.cancelLikesMainComment(map);
        int row1 = mainCommentMapper.reduceLikes((int)map.get("commentMainId"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0 && row1 >0) {
            mapRes.put("msg","取消点赞博客评论成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取消点赞博客评论失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map likeInsideComment(Map map) {
        int row = insideCommentMapper.likeInsideComment(map);
        int row1 = insideCommentMapper.addLikes((int)map.get("commentInsideId"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0 && row1 > 0) {
            mapRes.put("msg","点赞评论回复成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","点赞评论回复失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map cancelLikesInsideComment(Map map) {
        int row = insideCommentMapper.cancelLikeInsideComment(map);
        int row1 = insideCommentMapper.reduceLikes((int)map.get("commentInsideId"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0 && row1 > 0) {
            mapRes.put("msg","取消点赞评论回复成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取消点赞评论回复失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map getMainCommentPage(int pageNum, int pageSize, int blogId) {
        int totalRecord = mainCommentMapper.getMainCommentCount(blogId);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("blogId",blogId);
        page.setList(mainCommentMapper.getMainCommentLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","博客内博客评论分页成功");
            map.put("status",1);
            map.put("list",page.getList());
        } else {
            map.put("mag","博客内博客评论分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getInsideCommentPage(int pageNum, int pageSize, int commentMainId) {
        int totalRecord = insideCommentMapper.getInsideCommentCount(commentMainId);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("commentMainId",commentMainId);
        page.setList(insideCommentMapper.getInsideCommentLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","评论回复分页成功");
            map.put("status",1);
            map.put("list",page.getList());
        } else {
            map.put("mag","评论回复分页失败");
            map.put("status",2);
        }
        return map;
    }


}
