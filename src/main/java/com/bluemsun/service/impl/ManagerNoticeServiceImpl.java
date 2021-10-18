package com.bluemsun.service.impl;

import com.bluemsun.dao.ManagerNoticeMapper;
import com.bluemsun.entity.ManagerNotice;
import com.bluemsun.entity.Page;
import com.bluemsun.service.ManagerNoticeService;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ManagerNoticeServiceImpl implements ManagerNoticeService {

    private final ManagerNoticeMapper managerNoticeMapper;

    public ManagerNoticeServiceImpl(ManagerNoticeMapper managerNoticeMapper) {
        this.managerNoticeMapper = managerNoticeMapper;
    }

    @Override
    public Map getManagerNoticePage(int pageNum, int pageSize) {
        int totalRecord = managerNoticeMapper.getManagerNoticeCount();
        Page page = new Page(pageNum,pageSize,totalRecord);
        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("startIndex",page.getStartIndex());
        map1.put("pageSize",pageSize);
        page.setList(managerNoticeMapper.getManagerNoticeLimit(map1));
        Map<String,Object> map = new HashMap<String,Object>();
        if (page.getList() != null) {
            map.put("msg","管理员公告分页成功");
            map.put("status",1);
            map.put("list",page.getList());
        } else {
            map.put("msg","管理员公告分页失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map addManagerNotice(ManagerNotice managerNotice) {
        managerNotice.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int row = managerNoticeMapper.addManagerNotice(managerNotice);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","发布公告成功");
            map.put("status",1);
        } else {
            map.put("msg","发布公告失败");
            map.put("status",2);
        }
        return map;
    }

    @Override
    public Map deleteManagerNotice(ManagerNotice managerNotice) {
        int row = managerNoticeMapper.deleteManagerNotice(managerNotice);
        Map<String,Object> map = new HashMap<String,Object>();
        if (row > 0) {
            map.put("msg","删除公告成功");
            map.put("status",1);
        } else {
            map.put("msg","删除公告失败");
            map.put("status",2);
        }
        return map;
    }
}
