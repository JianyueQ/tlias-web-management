package com.itheima.controller;

import com.aliyun.oss.AliOssUtil;
import com.itheima.properties.FileProperties;
import com.itheima.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
public class FileController {

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private AliOssUtil aliOssUtil;


    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            log.info("文件上传开始,文件名:{}", filename);
            String substring = filename.substring(filename.lastIndexOf("."));
            //对文件的后缀名进行校验,如果不是图片文件则拒绝上传
            if (!".png".equals(substring) && !".jpg".equals(substring) && !".jpeg".equals(substring)) {
                return Result.error("请上传图片文件");
            }
            String newFileName = UUID.randomUUID().toString() + substring;
            //todo 可以替换成阿里云oss
            String url = aliOssUtil.upload(file.getBytes(), newFileName);
            return Result.success(url);
//        try {
//            String url = aliOssUtil.upload(file.getBytes(),  newFileName);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
            //保存文件到指定路径
//            String fullPath = fileProperties.getUploadPath() + newFileName;
//            log.info("保存文件到指定路径：{}",fullPath);
//            File dest = new File(fullPath);
//            if (!dest.exists()){
//                dest.mkdirs();
//            }
//            file.transferTo(dest);
//
//            String accessUrl = fileProperties.getAccessUrl() + newFileName;
//            return Result.success(accessUrl);

        } catch (Exception e) {
            return Result.error("上传失败");
        }

    }

}
