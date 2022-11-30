package com.xxxx.yeb.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xxxx.yeb.config.UploadProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @description： 文件上传
 * @author: Hxxiapgy
 * @date: 2020/7/9 22:01
 */
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {

    private Log log= LogFactory.getLog(UploadService.class);

    @Resource
    private FastFileStorageClient storageClient;

    @Resource
    private UploadProperties uploadProperties;

    /**
    * 功能描述:     文件上传
    * @param multipartFile  要上传的文件
    * @return {@link String}    返回文件路径
    * @author hxxiapgy
    * @data 2020/7/9 22:05
    */
    public String upload(MultipartFile multipartFile){

        // 1.校验上传文件是否为空
        if (multipartFile == null){
            log.error("文件不存在");
            throw new RuntimeException("文件为空！");
        }

        // 1.校验文件类型
        //获取上传文件的类型
        String contentType = multipartFile.getContentType();
        if (!uploadProperties.getAllowTypes().contains(contentType)){
            log.info("文件类型不支持！");
            throw new RuntimeException("文件类型不支持！");
        }

        try {
            // 2.校验文件内容
            // 读取文件
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if (bufferedImage == null || bufferedImage.getWidth() == 0 || bufferedImage.getHeight() == 0){
                log.error("上传文件有问题");
                throw new RuntimeException("上传文件有问题！");
            }
        } catch (IOException e) {
            log.error("检验文件内容失败...{}",e);
            throw new RuntimeException("检验文件内容失败...{}" + e.getMessage());
        }

        // 3.获取扩展名
        String extension = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(),".");

        try {
            // 4.上传文件
            StorePath storePath  = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), extension, null);
            // 5.返回路径
            return uploadProperties.getBaseUrl() + storePath.getFullPath();
        } catch (Exception e) {
            log.error("文件上传失败！...{}",e);
            throw new RuntimeException("文件上传失败...{}" + e.getMessage());
        }

    }

}
