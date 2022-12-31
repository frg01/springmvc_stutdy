package com.hspedu.web.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * 处理文件上传的handler
 */
@Controller
public class FileUploadHandler {

    //编写方法，处理文件上穿的请求
    @RequestMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file,
                             HttpServletRequest request,
                             String introduce) throws Exception{
//        System.out.println("introduce=" + introduce);
//        //接收到提交的文件名
//        String originalFilename = file.getOriginalFilename();
//        System.out.println("你上传的文件名=" + originalFilename);
//        //得到把上传的文件保存到哪个路径
//        String fileFullPath = request.getServletContext().getRealPath("/img/" + originalFilename);
//        System.out.println("fileFullPath=" + fileFullPath);
//        //创建文件
//        File saveToFile = new File(fileFullPath);
//        //将上传的文件转存saveToFile
//        file.transferTo(saveToFile);

        String fileFullPath = file.getOriginalFilename();
        System.out.println("getOriginalFilename=" + fileFullPath);

        InputStream resourceAsStream = request.getServletContext().getResourceAsStream("/img/" + fileFullPath);

        File saveToFile = new File(fileFullPath);
        file.transferTo(saveToFile);
        return "login_ok";
    }
}
