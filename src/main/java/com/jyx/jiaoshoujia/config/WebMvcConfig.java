package com.jyx.jiaoshoujia.config;



import com.jyx.jiaoshoujia.interceptor.MyIntercepyor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyIntercepyor myIntercepyor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercepyor).excludePathPatterns("/user/user/login","/user/user/hello").addPathPatterns("/**");
    }

}
