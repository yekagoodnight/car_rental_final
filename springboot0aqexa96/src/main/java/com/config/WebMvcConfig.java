package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.FileNotFoundException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            // 获取项目根目录
            String projectPath = System.getProperty("user.dir");
            
            // 配置项目根目录下的upload路径
            String rootUploadPath = projectPath + File.separator + "upload";
            File rootUploadDir = new File(rootUploadPath);
            if (!rootUploadDir.exists()) {
                rootUploadDir.mkdirs();
            }
            
            // 获取classpath:static路径
            String staticPath = ResourceUtils.getURL("classpath:static").getPath();
            String staticUploadPath = staticPath + "/upload";
            File staticUploadDir = new File(staticUploadPath);
            if (!staticUploadDir.exists()) {
                staticUploadDir.mkdirs();
            }
            
            // 注册多个可能的资源路径
            registry.addResourceHandler("/upload/**")
                    .addResourceLocations(
                        "file:" + rootUploadPath + File.separator,
                        "file:" + staticUploadPath + File.separator,
                        "classpath:/static/upload/"
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 