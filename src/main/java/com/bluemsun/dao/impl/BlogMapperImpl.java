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


    //????????????????????????
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

    //????????????????????????
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

    //??????????????????
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

    //????????????????????????list
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

    //????????????????????????
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

    //????????????????????????
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

    //????????????????????????
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

    //????????????
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

    //?????????????????????????????????id
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

    //??????????????????
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


    //????????????????????????
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

    //????????????
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

    //????????????
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

    //??????????????????
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
    public boolean addBlogViewsOfRedis(int blogId, int userId) {
        try {
            return jedisUtil.pfadd("blog_view:"+blogId,((Integer)userId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long getBlogViewsOfRedis(Blog blog) {
        long views = 0;
        try {
            views = jedisUtil.pfcount("blog_view:"+blog.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
    }

    @Override
    public int updateBlogViews(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).updateBlogViews(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public long getBlogViews(int blogId) {
        long views = 0;
        try {
            views = getSqlSession().getMapper(BlogMapper.class).getBlogViews(blogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
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

    @Override
    public void updateAllBlogsViews() {
        List<Blog> allBlogsList =  getSqlSession().getMapper(BlogMapper.class).getAllBlogs();
        for (Blog blog: allBlogsList) {
            blog.setViews(blog.getViews() + getBlogViewsOfRedis(blog));
            getSqlSession().getMapper(BlogMapper.class).updateBlogViews(blog);
        }
        //flush???????????????DB1
        jedisUtil.flushDB(1);
    }

    @Override
    public void updateAllBlogsHeat() {
        List<Blog> allBlogsList =  getSqlSession().getMapper(BlogMapper.class).getAllBlogs();
        for (Blog blog : allBlogsList) {
            double heat = (blog.getViews() + blog.getLikesNum()*100L + 1) / (1+((new Timestamp(System.currentTimeMillis()).getTime() - blog.getCreateTime().getTime()))/86400000);
//            System.out.println("+++++++++++++++++heat="+(1+((new Timestamp(System.currentTimeMillis()).getTime() - blog.getCreateTime().getTime()))/86400000));
            blog.setHeat(heat);
            updateBlogHeat(blog);
            jedisUtil.del("blog:"+blog.getId());
        }
    }

    @Override
    public List<Blog> getHeatTopBlogs() {
        List<Blog> blogList = null;
        try {
            blogList = getSqlSession().getMapper(BlogMapper.class).getHeatTopBlogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogList;
    }

}
