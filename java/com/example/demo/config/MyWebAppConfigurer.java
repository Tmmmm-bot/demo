package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer  {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String destFileName = System.getProperty("user.dir") + "/images/" ;
        String destvideos = System.getProperty("user.dir") + "/videos/" ;
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+destFileName)
                .addResourceLocations("file:"+destvideos);
    }

}
