package com.bluemsun.dao.impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.dao.PlateMapper;
import com.bluemsun.dto.BlogUserDto;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Plate;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BlogMapperImpl extends SqlSessionDaoSupport implements BlogMapper {

    private final JedisUtil jedisUtil;
    private final Gson gson;
    private final PlateMapper plateMapper;

    public BlogMapperImpl(JedisUtil jedisUtil, Gson gson, PlateMapper plateMapper) {
        this.jedisUtil = jedisUtil;
        this.gson = gson;
        this.plateMapper = plateMapper;
    }

    @Override
    public int getBlogCount() {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).getBlogCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Blog> getBlogsLimit(Map map) {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getBlogsLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int getBlogOfPlateCount(int id) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(BlogMapper.class).getBlogOfPlateCount(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Blog> getBlogsOfPlateLimit(Map map) {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getBlogsOfPlateLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int getUserBlogCount(int id) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(BlogMapper.class).getUserBlogCount(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Blog> getUserBlogsLimit(Map map) {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getUserBlogsLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

    @Override
    public int deleteBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).deleteBlog(blog);
            jedisUtil.del("blog:"+blog.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Blog> getBlogsHomeTop() {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getBlogsHomeTop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }


    //新增首页置顶博客
    @Override
    public int toppingBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).toppingBlog(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改首页置顶位置
    @Override
    public int modifyBlogTop(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).modifyBlogTop(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取消首页置顶
    @Override
    public int cancelToppingBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).cancelToppingBlog(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //获取板块置顶博客list
    @Override
    public List<Blog> getPlateBlogsHomeTop(Plate plate) {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getPlateBlogsHomeTop(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

    //新增板块置顶博客
    @Override
    public int toppingPlateBlog(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).toppingPlateBlog(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改板块置顶位置
    @Override
    public int modifyPlateBlogTop(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).modifyPlateBlogTop(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取消板块置顶博客
    @Override
    public int cancelToppingPlateBlog(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).cancelToppingPlateBlog(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //发布博客
    @Override
    public int releaseBlog(BlogUserDto blogUserDto) {
        int row = 0;
        try {
            blogUserDto.setCreateTime(new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(BlogMapper.class).releaseBlog(blogUserDto);
            jedisUtil.set("blog:"+blogUserDto.getId(),gson.toJson(blogUserDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //查找用户刚发布的博客的id
    @Override
    public int selectUserJustReleaseBlogId(int userId) {
        int id = 0;
        try {
            id = getSqlSession().getMapper(BlogMapper.class).selectUserJustReleaseBlogId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    //查看博客详情
    @Override
    public BlogUserDto checkBlog(Blog blog) {
        BlogUserDto blogUserDto = null;
        try {
            blogUserDto = gson.fromJson(jedisUtil.get("blog:"+blog.getId()),BlogUserDto.class);
            if (blogUserDto == null) {
                blogUserDto = getSqlSession().getMapper(BlogMapper.class).checkBlog(blog);
                Plate plate = plateMapper.selectPlateBlogBelongs(blog.getId());
                if (plate != null) {
                    blogUserDto.setPlateId(plate.getId());
                    blogUserDto.setPlateName(plate.getPlateName());
                }
                jedisUtil.set("blog:"+blogUserDto.getId(),gson.toJson(blogUserDto));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogUserDto;
    }


    //博客是否被我赞过
    @Override
    public int isBlogLiked(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).isBlogLiked(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //编辑博客
    @Override
    public int updateBlog(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).updateBlog(blog);
            BlogUserDto blogUserDto = gson.fromJson(jedisUtil.get("blog:"+blog.getId()),BlogUserDto.class);
            blogUserDto.setCreateTime(blog.getCreateTime());
            blogUserDto.setContent(blog.getContent());
            blogUserDto.setTitle(blog.getTitle());
            jedisUtil.set("blog:"+blog.getId(),gson.toJson(blogUserDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //点赞博客
    @Override
    public int likeBlog(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).likeBlog(map);
            BlogUserDto blogUserDto = gson.fromJson(jedisUtil.get("blog:"+(int)map.get("blogId")),BlogUserDto.class);
            blogUserDto.setLikesNum(blogUserDto.getLikesNum()+1);
            jedisUtil.set("blog:"+blogUserDto.getId(),gson.toJson(blogUserDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取消点赞博客
    @Override
    public int cancelLikeBolg(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).cancelLikeBolg(map);
            BlogUserDto blogUserDto = gson.fromJson(jedisUtil.get("blog:"+(int)map.get("blogId")),BlogUserDto.class);
            blogUserDto.setLikesNum(blogUserDto.getLikesNum()-1);
            jedisUtil.set("blog:"+blogUserDto.getId(),gson.toJson(blogUserDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int getSelectBlogCount(String title) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(BlogMapper.class).getSelectBlogCount(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Blog> selectBlogList(Map map) {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).selectBlogList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

    @Override
    public boolean addViews(int blogId, int userId) {
        try {
            return jedisUtil.pfadd("blog_view:"+blogId,((Integer)userId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int addLikes(int blogId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).addLikes(blogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reduceLikes(int blogId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).reduceLikes(blogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateBlogHeat(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).updateBlogHeat(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getAllBlogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }


}
