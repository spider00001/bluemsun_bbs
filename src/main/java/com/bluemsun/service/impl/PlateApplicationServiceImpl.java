package com.bluemsun.service.impl;

import com.bluemsun.dao.PlateApplicationMapper;
import com.bluemsun.dao.PlateMapper;
import com.bluemsun.dto.PlateApplicationDto;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.Plate;
import com.bluemsun.entity.PlateApplication;
import com.bluemsun.service.PlateApplicationService;
import com.bluemsun.utils.JedisUtil;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class PlateApplicationServiceImpl implements PlateApplicationService {

    private final PlateApplicationMapper plateApplicationMapper;
    private final PlateMapper plateMapper;

    public PlateApplicationServiceImpl(PlateApplicationMapper plateApplicationMapper, PlateMapper plateMapper) {
        this.plateApplicationMapper = plateApplicationMapper;
        this.plateMapper = plateMapper;
    }

    @Override
    public Map getPlateApplicationPage(int pageNum, int pageSize) {
        int totalRecord = plateApplicationMapper.getPlateApplicationCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(plateApplicationMapper.getPlateApplicationsLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","申请分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","申请分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getPlateApplicationClassifiedPage(int pageNum, int pageSize, int status) {
        int totalRecord = plateApplicationMapper.getPlateApplicationClassifiedCount(status);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("status",status);
        page.setList(plateApplicationMapper.getPlateApplicationsClassifiedLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","分类申请分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","分类申请分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map getMyPlateApplicationPage(int pageNum, int pageSize, int userId) {
        int totalRecord = plateApplicationMapper.getMyPlateApplicationCount(userId);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("userId",userId);
        page.setList(plateApplicationMapper.getMyPlateApplicationsLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","用户申请分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","用户申请分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map checkPlateApplication(PlateApplication plateApplication) {
        PlateApplicationDto plateApplicationRes = plateApplicationMapper.checkPlateApplication(plateApplication);
        Map<String,Object> map = new HashMap<String,Object>();
        if (plateApplicationRes != null) {
            map.put("msg","查看申请成功");
            map.put("status",1);
            map.put("data",plateApplicationRes);
        } else {
            map.put("msg","查看申请失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map passPlateApplication(PlateApplication plateApplication) {
        int row = plateApplicationMapper.passPlateApplication(plateApplication);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","通过申请成功");
            map.put("status",1);
            PlateApplication plateApplicationRes = plateApplicationMapper.selectPlateApplication(plateApplication);
            Plate plate = new Plate();
            plate.setPlateName(plateApplicationRes.getPlateName());
            plate.setUserId(plateApplicationRes.getUserId());
            plate.setCreateTime(new Timestamp(System.currentTimeMillis()));
            plateMapper.addPlate(plate);
        } else {
            map.put("msg","通过申请失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map overrulePlateApplication(PlateApplication plateApplication) {
        int row = plateApplicationMapper.overrulePlateApplication(plateApplication);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","不通过申请成功");
            map.put("status",1);
        } else {
            map.put("msg","不通过申请失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map addPlateApplication(PlateApplication plateApplication) {
        plateApplication.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int row = plateApplicationMapper.addPlateApplication(plateApplication);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","发送申请成功");
            map.put("status",1);
        } else {
            map.put("msg","发送申请失败");
            map.put("status",2);
        }
        return map;
    }

}
