package com.bluemsun.dao.impl;

import com.bluemsun.dao.PlateMapper;
import com.bluemsun.entity.Plate;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;
import java.util.Map;

public class PlateMapperImpl extends SqlSessionDaoSupport implements PlateMapper {

    //获取所有板块数量
    @Override
    public int getPlateCount() {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).getPlateCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //获取所有板块list
    @Override
    public List<Plate> getPlatesLimit(int startIndex, int pageSize) {
        List<Plate> plateList = null;
        try {
            plateList = getSqlSession().getMapper(PlateMapper.class).getPlatesLimit(startIndex,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateList;
    }

    //获取所有未冻结的板块数量
    @Override
    public int getAvailablePlateCount() {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateMapper.class).getAvailablePlateCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    //获取所有未冻结的板块list
    @Override
    public List<Plate> getAvailablePlatesLimit(int startIndex,int pageSize) {
        List<Plate> plateList = null;
        try {
            plateList = getSqlSession().getMapper(PlateMapper.class).getAvailablePlatesLimit(startIndex,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateList;
    }

    @Override
    public Plate checkPlate(Plate plate) {
        Plate plateRes = null;
        try {
            plateRes = getSqlSession().getMapper(PlateMapper.class).checkPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateRes;
    }

    @Override
    public int addPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).addPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //删除板块
    @Override
    public int deletePlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).deletePlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //查找板块
    @Override
    public int selectPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).selectPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public List<Plate> getPlatesOfHome() {
        List<Plate> plateList = null;
        try {
            plateList = getSqlSession().getMapper(PlateMapper.class).getPlatesOfHome();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateList;
    }

    //新增置顶表内的板块并确定置顶位置
    @Override
    public int toppingPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).toppingPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改已在置顶表内板块的置顶位置
    @Override
    public int modifyPlateTop(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).modifyPlateTop(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //取消置顶
    @Override
    public int cancelToppingPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).cancelToppingPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //冻结板块
    @Override
    public int frozenPlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).frozenPlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //解冻板块
    @Override
    public int unfreezePlate(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).unfreezePlate(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    //判断该板块是否已经在首页板块置顶表内
    @Override
    public Plate isPlateTopped(Plate plate) {
        Plate plateRes = null;
        try {
            plateRes = getSqlSession().getMapper(PlateMapper.class).isPlateTopped(plate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateRes;
    }

    @Override
    public int releaseBlogInPlate(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).releaseBlogInPlate(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateBlogPlate(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).updateBlogPlate(map);
        } catch (Exception e)  {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deselectPlate(Map map) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).deselectPlate(map);
        } catch (Exception e)  {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updatePlateDescription(Plate plate) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).updatePlateDescription(plate);
        } catch (Exception e)  {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int getSelectPlateCount(String plateName) {
        int count = 0;
        try {
            count = getSqlSession().getMapper(PlateMapper.class).getSelectPlateCount(plateName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Plate> selectPlateList(Map map) {
        List<Plate> plateList = null;
        try {
            plateList = getSqlSession().getMapper(PlateMapper.class).selectPlateList(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plateList;
    }

    @Override
    public int addPlateBlogNum(@Param("plateId") int plateId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).addPlateBlogNum(plateId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int reducePlateBlogNum(@Param("plateId") int plateId) {
        int row = 0;
        try {
            row = getSqlSession().getMapper(PlateMapper.class).reducePlateBlogNum(plateId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }


}
