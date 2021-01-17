package com.exi.bookmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // 允许跨域访问的 URL
        List<String> allowedOriginsUrl = new ArrayList<>();
        allowedOriginsUrl.add("http://localhost:9528");
        allowedOriginsUrl.add("http://172.31.11.47:9528");
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置允许跨域访问的 URL
        config.setAllowedOrigins(allowedOriginsUrl);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
