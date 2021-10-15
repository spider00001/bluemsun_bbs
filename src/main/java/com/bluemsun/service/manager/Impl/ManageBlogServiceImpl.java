package com.bluemsun.service.manager.Impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.entity.Page;
import com.bluemsun.service.manager.ManageBlogService;

import java.util.HashMap;
import java.util.Map;

public class ManageBlogServiceImpl implements ManageBlogService {


    private final BlogMapper blogMapper;

    public ManageBlogServiceImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public Map<String,Object> getBlogsPage(int pageNum, int pageSize) {
        int totalRecord = blogMapper.getBlogCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(blogMapper.getBlogsLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","帖子分页成功");
            map.put("status",1);
            map.put("blogList",page.getList());
        } else {
            map.put("mag","帖子分页失败");
            map.put("status",2);
        }
        return map;
    }
}
