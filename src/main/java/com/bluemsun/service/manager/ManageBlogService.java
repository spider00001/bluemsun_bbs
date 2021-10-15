package com.bluemsun.service.manager;

import java.util.Map;

public interface ManageBlogService {

    //博客分页(|全部类型)
    Map getBlogsPage(int pageNum, int pageSize);

}
