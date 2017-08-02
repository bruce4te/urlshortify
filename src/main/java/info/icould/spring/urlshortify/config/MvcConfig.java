package info.icould.spring.urlshortify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/web/home").setViewName("home");
        registry.addViewController("/web/statistics").setViewName("statistics");
        registry.addViewController("/web/login").setViewName("login");
    }
}
