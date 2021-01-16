package com.exi.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
public class CrosConfig implements WebMvcConfigurer{
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }*/
}
