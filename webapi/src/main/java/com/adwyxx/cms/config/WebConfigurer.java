package com.adwyxx.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description: Web Configuration
 * @Auther: Leo.W
 * @Date: 2018/11/15 10:24
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

//    @Autowired
//    private WebApiInterceptor webApiInterceptor;

    /**
    * @description : 全局跨域配置
    * @param registry :
    * @author : Leo.W
    * @date : 2018/11/20 17:11
    **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //跨域请求HTTP路由
                .allowedOrigins("*") //允许跨域请求的域名，多个都好分割
                .allowedMethods("*") //允许跨域请求的Http Method，多个都好分割，如:"GET","POST"
                .allowedHeaders("*") //允许跨域请求的Http Header，多个都好分割，如:"Authorization","Accpet"
                .allowCredentials(true) //是否允许证书 不再默认开启
                .maxAge(3600);
    }

//    @Override
//    public void  addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(webApiInterceptor).addPathPatterns("/**");
//    }
}
