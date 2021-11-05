//package com.bluemsun.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@RequestMapping("/user")
//@RestController
//public class FileUploadController {
//
//    //用户头像上传
//    @PostMapping(value="/uploadHeadPortrait")
//    public Map uploadHeadPortrait(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        Map<String,Object> map = new HashMap<String,Object>();
//        try {
//            if (file.getSize() <= 2097152) {
//                String disposition = file.getOriginalFilename();
//                String suffix = disposition.substring(disposition.lastIndexOf("."));
//                if (suffix.contains("png")||suffix.contains("jpg")) {
//                    //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
//                    String filename = UUID.randomUUID()+suffix;
//                    String serverPath = request.getSession().getServletContext().getRealPath("headPortrait");
//                    File fileDisk = new File(serverPath);
//                    if (!fileDisk.exists()) {
//                        fileDisk.mkdir();
//                    }
//                    String fileParts = serverPath + "/" + filename;
//                    file.transferTo(new File(fileParts));
//                    String projectServerPath = request.getScheme() + "://" + request.getServerName() +
//                            ":" + request.getServerPort() + request.getContextPath() + "/headPortrait/" + filename;
//                    map.put("msg","上传成功");
//                    map.put("status",1);
//                    map.put("headPortrait",projectServerPath);
//                } else {
//                    map.put("msg","上传失败,只支持.png或.jpg格式的图片！");
//                    map.put("status",2);
//                }
//            } else {
//                map.put("msg","上传失败,上传图片最大2M！");
//                map.put("status",2);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    //资源博客上传文件
//    @PostMapping("/uploadBlogResourceFile")
//    public Map uploadBlogResourceFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        Map<String,Object> map = new HashMap<String,Object>();
//        try {
//            if (file.getSize() <= 104857600) {
//                String disposition = file.getOriginalFilename();
//                String suffix = disposition.substring(disposition.lastIndexOf("."));
//                //随机生成的UUID，作为文件名的一部分，加上刚才获取到的后缀作为最终文件名
//                String filename = UUID.randomUUID()+suffix;
//                String serverPath = request.getSession().getServletContext().getRealPath("blogResourceFile");
//                File fileDisk = new File(serverPath);
//                if (!fileDisk.exists()) {
//                    fileDisk.mkdir();
//                }
//                String fileParts = serverPath + File.separator + filename;
//                file.transferTo(new File(fileParts));
//                map.put("msg","上传成功");
//                map.put("status",1);
//                map.put("fileURL",fileParts);
//            } else {
//                map.put("msg","上传失败,文件太大");
//                map.put("status",2);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    //资源博客下载文件
//    @PostMapping("/downloadBlogResourceFile")
//    public void downloadBlogResourceFile(@RequestBody Map map, HttpServletResponse resp, HttpServletRequest request) {
//        try {
//            String blogResourceFile = (String) map.get("fileURL");
//            String path = request.getSession().getServletContext().getRealPath("fileURL")+ File.separator + blogResourceFile;
//            File file = new File(path);
//            //设置响应头信息
//            resp.addHeader("Content-Disposition","attachemt;filename="+file.getName());
//            //设置文件ContentType类型，这样设置，会自动判断下载文件类型
//            resp.setContentType("multipart/form-data");
//            InputStream bis = new BufferedInputStream(new FileInputStream(file));
//            BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
//            int len;
//            while((len = bis.read()) != -1){
//                out.write(len);
//                out.flush();
//            }
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}