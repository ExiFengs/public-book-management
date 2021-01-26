package com.exi.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  Copyright: Copyright (c) 2021 Asiainfo
 *
 *  @ClassName: com.exi.bookmanagement.config.CrosConfig
 *  @Description: 该类的功能描述
 * <p>
 *  @version: v1.0.0
 *  @author:   Fengsx
 *  @date: 1/12/21 4:48 PM
 * <p>
 *  Modification History:
 *  Date       Author    Version    Description
 * ----------------------------------------------------------
 *  1/12/21    Fengsx     v1.0.0      修改原因
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9528","null")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                // 最大响应时间
                .maxAge(3600)
                // 是否携带信息
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "/Users/fengshixi/Desktop/graduationProject/book-management/target/classes/static/img/ad");
    }

}
