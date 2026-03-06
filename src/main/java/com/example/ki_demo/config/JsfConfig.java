package com.example.ki_demo.config;

import jakarta.faces.webapp.FacesServlet;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsfConfig {

    @Bean
    public ServletListenerRegistrationBean<StartupServletContextListener> myFacesStartupListener() {
        return new ServletListenerRegistrationBean<>(new StartupServletContextListener());
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServlet() {
        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
