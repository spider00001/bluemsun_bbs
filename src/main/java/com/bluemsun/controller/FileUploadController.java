package com.bluemsun.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/user")
@RestController
public class FileUploadController {

    //用户头像上传
    @PostMapping(value="/uploadHeadPortrait")
    public Map uploadHeadPortrait(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String disposition = file.getOriginalFilename();
            String suffix = disposition.substring(disposition.lastIndexOf("."));
            if (suffix.contains("png")||suffix.contains("jpg")) {
                //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
                String filename = UUID.randomUUID()+suffix;
                String serverPath = request.getSession().getServletContext().getRealPath("headPortrait");
                File fileDisk = new File(serverPath);
                if (!fileDisk.exists()) {
                    fileDisk.mkdir();
                }
                String fileParts = serverPath + "/" + filename;
                file.transferTo(new File(fileParts));
                String projectServerPath = request.getScheme() + "://" + request.getServerName() +
                        ":" + request.getServerPort() + request.getContextPath() + "/headPortrait/" + filename;
                map.put("msg","上传成功");
                map.put("status",1);
                map.put("headPortrait",projectServerPath);
            } else {
                map.put("msg","上传失败，只支持.png或.jpg格式的图片");
                map.put("status",2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
//        if(!file.isEmpty()) {
//            try {
//                String disposition = file.getOriginalFilename();
//                String suffix = disposition.substring(disposition.lastIndexOf("."));
//                if (suffix.contains("png")||suffix.contains("jpg")) {
//
//                }
//                //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
//                String filename = UUID.randomUUID()+suffix;
//                String serverPath = request.getSession().getServletContext().getRealPath("headPortrait");
//                File fileDisk = new File(serverPath);
//                if (!fileDisk.exists()) {
//                    fileDisk.mkdir();
//                }
//                String fileParts = serverPath + "/" + filename;
//                file.transferTo(new File(fileParts));
//                projectServerPath = request.getScheme() + "://" + request.getServerName() +
//                        ":" + request.getServerPort() + request.getContextPath() + "/headPortrait/" + filename;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            map.put("msg","上传成功");
//            map.put("status",1);
//            map.put("headPortrait",projectServerPath);
//            return map;
//        } else {
//            map.put("msg","上传失败，图片格式不支持");
//            map.put("status",2);
//            return map;
//        }
    }


}