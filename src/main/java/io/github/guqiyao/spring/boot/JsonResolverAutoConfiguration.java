package io.github.guqiyao.spring.boot;

import io.github.guqiyao.JsonParameterResolver;
import io.github.guqiyao.jackson.JacksonParameterParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author: qiyao.gu
 * @Eamil: qiyao.gu@nalaa.com
 * @Date: 2019/4/26 15:57
 */
@Configuration
public class JsonResolverAutoConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(createHandlerMethodArgumentResolver());
    }

    private HandlerMethodArgumentResolver createHandlerMethodArgumentResolver() {
        JsonParameterResolver jsonParameterResolver = new JsonParameterResolver();
        jsonParameterResolver.setParameterParser(new JacksonParameterParser());

        return jsonParameterResolver;
    }
}