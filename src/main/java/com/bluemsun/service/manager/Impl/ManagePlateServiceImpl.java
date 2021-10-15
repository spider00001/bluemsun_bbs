package com.bluemsun.service.manager.Impl;

import com.bluemsun.dao.PlateMapper;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.Plate;
import com.bluemsun.service.manager.ManagePlateService;

import java.util.HashMap;
import java.util.Map;

public class ManagePlateServiceImpl implements ManagePlateService {

    private final PlateMapper plateMapper;

    public ManagePlateServiceImpl(PlateMapper plateMapper) {
        this.plateMapper = plateMapper;
    }

    @Override
    public Map getPlatePage(int pageNum, int pageSize) {
        int totalRecord = plateMapper.getPlateCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(plateMapper.getPlatesLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("mag","板块分页成功");
            map.put("status",1);
            map.put("blogList",page.getList());
        } else {
            map.put("mag","板块分页失败");
            map.put("status",2);
        }
        return map;
    }

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
}
