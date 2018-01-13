package com.huskyoneapp;


import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.huskyoneapp.filters.SanitizeFilter;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/register").setViewName("login");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/jobs.htm").setViewName("jobs");
        registry.addViewController("/forsale.htm").setViewName("forsale");
        registry.addViewController("/post.htm").setViewName("postListing");
        registry.addViewController("/adminpage.htm").setViewName("adminpage");
        registry.addViewController("/user.htm").setViewName("user");
        
    }   
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/upload/**")
          .addResourceLocations("file:upload/")
          .setCachePeriod(0);
    }
  
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sanitizeFilter());
        registration.addUrlPatterns("/postProduct");
        registration.addUrlPatterns("/postHousing");
        registration.addUrlPatterns("/api/v1/getHouses");
        registration.addUrlPatterns("/api/v1/getProducts");
        registration.setName("sanitizeFilter");
        registration.setOrder(1);
        return registration;
    } 

    @Bean(name = "sanitizeFilter")
    public Filter sanitizeFilter() {
        return new SanitizeFilter();
    }

}