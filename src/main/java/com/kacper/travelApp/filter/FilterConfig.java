package com.kacper.travelApp.filter;

import com.kacper.travelApp.service.Service.SessionService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private SessionService sessionService;

    public FilterConfig(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionFilter(sessionService));
        registrationBean.addUrlPatterns("/api/protected/*");
        return registrationBean;
    }
}
