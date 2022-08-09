package com.hime.config;


import com.hime.interceptor.CookieInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper=new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        urlPathHelper.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/date/**").addResourceLocations("file:D:/date/");
    }


    @Bean
    public CookieInterceptor cookieInterceptor() {
        return new CookieInterceptor();
    }

    @Override
    //此方法用来专门注册一个拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns("/**")对所有请求都拦截，但是排除了/apiList/v1/calculator和/js/**和/*/v1/returnJson请求的拦截。
        registry.addInterceptor(cookieInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/Login/*")
                .excludePathPatterns("/Login")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/index/**");
    }
}