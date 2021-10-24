package com.bluemsun.dao.impl;

import com.bluemsun.dao.BlogMapper;
import com.bluemsun.entity.Blog;
import com.bluemsun.entity.Plate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class BlogMapperImpl extends SqlSessionDaoSupport implements BlogMapper {

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
    public int releaseBlog(Map map) {
        int row = 0;
        try {
            map.put("createTime",new Timestamp(System.currentTimeMillis()));
            row = getSqlSession().getMapper(BlogMapper.class).releaseBlog(map);
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
    public Blog checkBlog(Blog blog) {
        Blog blogRes = null;
        try {
            blogRes = getSqlSession().getMapper(BlogMapper.class).checkBlog(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogRes;
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
    public int addViews(Blog blog) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).addViews(blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
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
    public int deleteBlogFromPlate(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(BlogMapper.class).deleteBlogFromPlate(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


}
