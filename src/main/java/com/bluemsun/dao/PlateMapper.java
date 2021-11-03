package com.bluemsun.dao;

import com.bluemsun.entity.Plate;
import com.bluemsun.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PlateMapper {

    //获取所有板块数量
    int getPlateCount();

    //获取所有板块list
    List<Plate> getPlatesLimit(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    //获取未冻结板块数量
    int getAvailablePlateCount();

    //获取未冻结板块list
    List<Plate> getAvailablePlatesLimit(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    //查看板块详情
    Plate checkPlate(Plate plate);

    //增加板块
    int addPlate(Plate plate);

    //删除板块
    int deletePlate(Plate plate);

    //查找板块(sql语句还没写)
    int selectPlate(Plate plate);

    //查看置顶板块
    List<Plate> getPlatesOfHome();

    //新增置顶板块
    int toppingPlate(Plate plate);

    //修改置顶板块位置
    int modifyPlateTop(Plate plate);

    //取消置顶板块
    int cancelToppingPlate(Plate plate);

    //冻结板块
    int frozenPlate(Plate plate);

    //解冻板块
    int unfreezePlate(Plate plate);

    //判断是否已经在置顶表内（暂时没用....）
    Plate isPlateTopped(Plate plate);

    //判断博客是否在该板块内
    int isBlogExistInPlate(Map map);

    //选择博客所在板块
    int releaseBlogInPlate(Map map);

    //修改博客所在板块
//    int updateBlogPlate(Map map);

    //取消选择博客所在板块
    int deselectPlate(Map map);

    //修改板块简介
    int updatePlateDescription(Plate plate);

    //搜索板块总数
    int getSelectPlateCount(@Param("plateName") String plateName);

    //搜索板块分页
    List<Plate> selectPlateList(Map map);

    //板块内博客数量+1
    int addPlateBlogNum(@Param("plateId") int plateId);

    //板块内博客数量-1
    int reducePlateBlogNum(@Param("plateId") int plateId);

    //查看自己的板块
    List<Plate> checkUserPlate(User user);

    //查询板块名称是否存在(申请板块时板块名称查重)
    int isPlateNameExist(@Param("plateName") String plateName);

}
