package com.bluemsun.service.impl;

import com.bluemsun.dao.PlateMapper;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.Plate;
import com.bluemsun.service.PlateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlateServiceImpl implements PlateService {

    private final PlateMapper plateMapper;

    public PlateServiceImpl(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }

    @Override
    public Map getPlatePage(int pageNum, int pageSize) {
        int totalRecord = plateMapper.getPlateCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        page.setList(plateMapper.getPlatesLimit(page.getStartIndex(),pageSize));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","板块分页成功");
            map.put("status",1);
            map.put("list",page.getList());
        } else {
            map.put("msg","板块分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getAvailablePlatePage(int pageNum, int pageSize) {
        int totalRecord = plateMapper.getAvailablePlateCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        page.setList(plateMapper.getAvailablePlatesLimit(page.getStartIndex(),pageSize));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","未冻结板块分页成功");
            map.put("status",1);
            map.put("list",page.getList());
        } else {
            map.put("msg","未冻结板块分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map checkPlate(Plate plate) {
        Plate plateRes = plateMapper.checkPlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (plateRes != null) {
            map.put("msg","查看板块成功");
            map.put("status",1);
            map.put("data",plateRes);
        } else {
            map.put("msg","查看板块失败");
            map.put("status",2);
        }
        return map;
    }

    //删除板块
    public Map deletePlate(Plate plate) {
        int row = plateMapper.deletePlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除板块成功");
            map.put("status",1);
        } else {
            map.put("msg","删除板块失败");
            map.put("status",2);
        }
        return map;
    }

    //新增置顶板块
    @Override
    public Map toppingPlate(Plate plate) {
        int row = plateMapper.toppingPlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","置顶板块成功");
            map.put("status",1);
        } else {
            map.put("msg","置顶板块失败,该位置已有板块");
            map.put("status",2);
        }
        return map;
    }

    //修改置顶位置
    @Override
    public Map modifyPlateTop(Plate plate) {
        int row = plateMapper.modifyPlateTop(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","修改位置成功");
            map.put("status",1);
        } else {
            map.put("msg","修改位置失败,该位置已有板块");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getPlatesOfHome() {
        List<Plate> plateList = plateMapper.getPlatesOfHome();
        Map<String,Object> map = new HashMap<String,Object>();
        if (plateList != null) {
            map.put("msg","查看置顶板块成功");
            map.put("status",1);
            map.put("list",plateList);
        } else {
            map.put("msg","查看置顶板块失败");
            map.put("status",2);
        }
        return map;
    }

    //取消置顶板块
    @Override
    public Map cancelToppingPlate(Plate plate) {
        int row = plateMapper.cancelToppingPlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","取消板块置顶成功");
            map.put("status",1);
        } else {
            map.put("msg","取消板块置顶失败");
            map.put("status",2);
        }
        return map;
    }

    //冻结板块
    public Map frozenPlate(Plate plate) {
        int row = plateMapper.frozenPlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","冻结板块成功");
            map.put("status",1);
        } else {
            map.put("msg","冻结板块失败");
            map.put("status",2);
        }
        return map;
    }

    //解冻板块
    public Map unfreezePlate(Plate plate) {
        int row = plateMapper.unfreezePlate(plate);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","解冻板块成功");
            map.put("status",1);
        } else {
            map.put("msg","解冻板块失败");
            map.put("status",2);
        }
        return map;
    }

    //修改博客所在板块
    @Override
    public Map updateBlogPlate(Map map) {
        int row = plateMapper.updateBlogPlate(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","修改博客所在板块成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","修改博客所在板块失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    //取消博客所在板块
    @Override
    public Map deselectPlate(Map map) {
        int row = plateMapper.deselectPlate(map);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","取消博客所在板块成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","取消博客所在板块失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map updatePlateDescription(Plate plate) {
        int row = plateMapper.updatePlateDescription(plate);
        Map<String,Object> mapRes = new HashMap<String,Object>();
        if (row > 0) {
            mapRes.put("msg","修改博客简介成功");
            mapRes.put("status",1);
        } else {
            mapRes.put("msg","修改博客简介失败");
            mapRes.put("status",2);
        }
        return mapRes;
    }

    @Override
    public Map selectPlatePage(int pageNum, int pageSize, String plateName) {
        int totalRecord = plateMapper.getSelectPlateCount(plateName);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("plateName",plateName);
        page.setList(plateMapper.selectPlateList(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","搜索板块分页成功");
            map.put("status",1);
            map.put("userList",page.getList());
        } else {
            map.put("msg","未搜索到板块");
            map.put("status",2);
        }
        return map;
    }


}
