package com.bluemsun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController extends HttpServlet {
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public  String uploadFileHandler( @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                HttpServletRequest req;

                // 文件存放服务端的位置
                String rootPath = "d:/tmp";
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                // 写文件到服务器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                file.transferTo(serverFile);
                return "You successfully uploaded file=" +  file.getOriginalFilename();
            } catch (Exception e) {
                return "You failed to upload " +  file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " +  file.getOriginalFilename() + " because the file was empty.";
        }
    }

}
