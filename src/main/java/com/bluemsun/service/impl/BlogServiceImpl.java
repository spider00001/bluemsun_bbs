package com.bluemsun.service.impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.dao.PlateMapper;
import com.bluemsun.dto.BlogUserDto;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.Plate;
import com.bluemsun.service.BlogService;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.*;

public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;
    private final PlateMapper plateMapper;
    private final JedisUtil jedisUtil;
    private final Gson gson;

    public BlogServiceImpl(BlogMapper blogMapper, PlateMapper plateMapper, JedisUtil jedisUtil, Gson gson) {
        this.blogMapper = blogMapper;
        this.plateMapper = plateMapper;
        this.jedisUtil = jedisUtil;
        this.gson = gson;
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
            map.put("msg","帖子分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","帖子分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getBlogsOfPlatePage(int pageNum, int pageSize, int id) {
        int totalRecord = blogMapper.getBlogOfPlateCount(id);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("id",id);
        page.setList(blogMapper.getBlogsOfPlateLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","板块内帖子分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","板块内帖子分页失败");
            map.put("status",2);
        }
        return map;
    }

    //获取用户博客分页
    @Override
    public Map getUserBlogsPage(int pageNum, int pageSize,int userId) {
        int totalRecord = blogMapper.getUserBlogCount(userId);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("userId",userId);
        page.setList(blogMapper.getUserBlogsLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","用户帖子分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","用户帖子分页失败");
            map.put("status",2);
        }
        return map;
    }


    @Override
    public Map<String,Object> deleteBlog(Blog blog) {
        int row = blogMapper.deleteBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除帖子成功");
            map.put("status",1);
        } else {
            map.put("msg","删除帖子失败");
            map.put("status",2);
        }
        return map;
    }

    //获取首页置顶博客
    @Override
    public Map getBlogsHomeTop() {
        List<Blog> blogList = blogMapper.getBlogsHomeTop();
        Map<String,Object> map = new HashMap<String,Object>();
        if (blogList != null) {
            map.put("msg","查看置顶博客成功");
            map.put("status",1);
            map.put("list",blogList);
            map.put("size",blogList.size());
        } else {
            map.put("msg","查看置顶博客失败");
            map.put("status",2);
        }
        return map;
    }

    //新增首页置顶博客
    @Override
    public Map toppingBlog(Blog blog) {
        int row = blogMapper.toppingBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","置顶博客成功");
            map.put("status",1);
        } else {
            map.put("msg","置顶博客失败,该位置已有板块");
            map.put("status",2);
        }
        return map;
    }

    //修改首页博客置顶位置
    @Override
    public Map modifyBlogTop(Blog blog) {
        int row = blogMapper.modifyBlogTop(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","修改位置成功");
            map.put("status",1);
        } else {
            map.put("msg","修改位置失败,该位置已有博客");
            map.put("status",2);
        }
        return map;
    }

    //取消首页博客置顶
    @Override
    public Map cancelToppingBlog(Blog blog) {
        int row = blogMapper.cancelToppingBlog(blog);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","取消博客置顶成功");
            map.put("status",1);
        } else {
            map.put("msg","取消博客置顶失败");
            map.put("status",2);
        }
        return map;
    }

    //获取板块置顶博客list
    @Override
    public Map getPlateBlogsHomeTop(Plate plate) {
        List<Blog> blogList = blogMapper.getPlateBlogsHomeTop(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (blogList != null) {
            map.put("msg","查看板块置顶博客成功");
            map.put("status",1);
            map.put("list",blogList);
        } else {
            map.put("msg","查看板块置顶博客失败");
            map.put("status",2);
        }
        return map;
    }

    //新增板块置顶博客
    @Override
    public Map toppingPlateBlog(Map map) {
        int row = blogMapper.toppingPlateBlog(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","板块置顶博客成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","板块置顶博客失败,该位置已有板块");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //修改板块置顶博客块位置
    @Override
    public Map modifyPlateBlogTop(Map map) {
        int row = blogMapper.modifyPlateBlogTop(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","板块置顶博客修改位置成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","板块置顶博客修改位置失败,该位置已有博客");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //取消板块置顶博客
    @Override
    public Map cancelToppingPlateBlog(Map map) {
        int row = blogMapper.cancelToppingPlateBlog(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","取消博客博客置顶成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取消板块博客置顶失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //如果选择了板块，则可以直接发布到板块里面(目前只能选择一个板块)
    @Override
    public Map releaseBlog(BlogUserDto blogUserDto) {
        int row = blogMapper.releaseBlog(blogUserDto);
        if (blogUserDto.getPlateId() != 0) {
            int blogId = blogMapper.selectUserJustReleaseBlogId(blogUserDto.getUserId());
            blogUserDto.setId(blogId);
            Map map = new HashMap();
            map.put("blogId",blogUserDto.getId());
            map.put("plateId",blogUserDto.getPlateId());
            plateMapper.releaseBlogInPlate(map);
            plateMapper.addPlateBlogNum(blogUserDto.getPlateId());
        }
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row >0) {
            mapRes.put("msg","发布博客成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","发布博客失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map<String,Object> checkBlog(Blog blog, int userId) {
        Map<String,Object> mapRes = new HashMap<String,Object>();
        BlogUserDto blogRes = blogMapper.checkBlog(blog);
        if (blogRes != null) {
            //isLiked:  0: 未赞 ; 1:赞过
            Map<String,Integer> mapIsLiked = new HashMap<String,Integer>();
            if (userId != 0) {
                mapIsLiked.put("blogId", blogRes.getId());
                mapIsLiked.put("userId",userId);
                mapRes.put("isLiked",blogMapper.isBlogLiked(mapIsLiked));
                //如果isMyBlog 为1则为自己的博客;为0则不是自己的博客(管理员端调用方法这个则不用判断)
                mapRes.put("isMyBlog",userId == blogRes.getUserId() ? 1 : 0);
                blogMapper.addBlogViewsOfRedis(blog.getId(),userId);
                blogRes.setViews(blogRes.getViews()+blogMapper.getBlogViewsOfRedis(blog));
                mapRes.put("data",blogRes);
                mapRes.put("msg","查看博客成功");
                mapRes.put("status",1);
            }
        } else {
            mapRes.put("msg","查看博客失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map updateBlog(Blog blog) {
        blog.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int row = blogMapper.updateBlog(blog);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","修改博客成功");
            mapRes.put("status",1);
            //返回被修改的博客id
            mapRes.put("blogId",blog.getId());
        } else {
            mapRes.put("msg","修改博客失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }


    @Override
    public Map likeBlog(Map map) {
        int row = blogMapper.likeBlog(map);
        int row1 = blogMapper.addLikes((int)map.get("blogId"));
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0 && row1 >0) {
            mapRes.put("msg","点赞博客博客成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","点赞博客博客失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map cancelLikeBlog(Map map) {
        int row = blogMapper.cancelLikeBolg(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        int row1 = blogMapper.reduceLikes((int)map.get("blogId"));
        if (row > 0 && row1 >0) {
            mapRes.put("msg","取消点赞博客博客成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取消点赞博客博客失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map selectBlogPage(int pageNum, int pageSize, String title) {
        int totalRecord = blogMapper.getSelectBlogCount(title);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("title",title);
        page.setList(blogMapper.selectBlogList(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","搜索博客分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("msg","未搜索到博客");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public void updateAllBlogsViews() {
        blogMapper.updateAllBlogsViews();
    }

    @Override
    public void updateAllBlogsHeat() {
        blogMapper.updateAllBlogsHeat();
    }

    @Override
    public Map getHeatTopBlogs() {
        List<Blog> blogList = blogMapper.getHeatTopBlogs();
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (blogList != null) {
            mapRes.put("list",blogList);
            mapRes.put("msg","获取热门博客5个成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","获取热门博客5个失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

}

