package com.bluemsun.service.impl;

import com.bluemsun.dao.PlateNoticeMapper;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.PlateNotice;
import com.bluemsun.service.PlateNoticeService;

import java.util.HashMap;
import java.util.Map;

public class PlateNoticeServiceImpl implements PlateNoticeService {

    private final PlateNoticeMapper plateNoticeMapper;

    public PlateNoticeServiceImpl(PlateNoticeMapper plateNoticeMapper) {
        this.plateNoticeMapper = plateNoticeMapper;
    }

    @Override
    public Map getPlateNoticePage(int pageNum, int pageSize, int plateId) {
        int totalRecord = plateNoticeMapper.getPlateNoticeCount(plateId);
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        map1.put("plateId",plateId);
        page.setList(plateNoticeMapper.getPlateNoticeLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","板块公告分页成功");
            map.put("status",1);
            map.put("list",page.getList());
            map.put("totalRecord",totalRecord);
        } else {
            map.put("mag","板块公告分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map checkPlateNotice(PlateNotice plateNotice) {
        PlateNotice plateNoticeRes = plateNoticeMapper.checkPlateNotice(plateNotice);
        Map<String,Object> map = new HashMap<String,Object>();
        if (plateNoticeRes != null) {
            map.put("msg","查看板块公告详情成功");
            map.put("status",1);
            map.put("data",plateNoticeRes);
        } else {
            map.put("msg","查看板块公告详情失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map addPlateNotice(PlateNotice plateNotice) {
        int row = plateNoticeMapper.addPlateNotice(plateNotice);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","发布板块公告成功");
            map.put("status",1);
        } else {
            map.put("msg","发布板块公告失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map deletePlateNotice(PlateNotice plateNotice) {
        int row = plateNoticeMapper.deletePlateNotice(plateNotice);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除板块公告成功");
            map.put("status",1);
        } else {
            map.put("msg","删除板块公告失败");
            map.put("status",2);
        }
        return map;
    }


}
