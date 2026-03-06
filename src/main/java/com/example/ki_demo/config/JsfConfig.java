package com.example.ki_demo.config;

import com.sun.faces.config.ConfigureListener;
import jakarta.faces.webapp.FacesServlet;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsfConfig {

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServlet() {
        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Bean
    public ServletContextInitializer jsfServletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("com.sun.faces.enableCdiResolverChain", "false");
            servletContext.setInitParameter("jakarta.faces.ENABLE_CDI_RESOLVER_CHAIN", "false");
            servletContext.setInitParameter("jakarta.faces.DISABLE_CDI_RESOLVER_CHAIN", "true");
        };
    }
}
