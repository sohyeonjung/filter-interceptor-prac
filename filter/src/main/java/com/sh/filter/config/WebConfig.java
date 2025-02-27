package com.sh.filter.config;

import com.sh.filter.interceptor.OpenApilnterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private OpenApilnterceptor openApilnterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //들어오는 모든 주소(/**)에, openApiInteceptor 적용
        registry.addInterceptor(openApilnterceptor)
                .addPathPatterns("/**");
    }
}
