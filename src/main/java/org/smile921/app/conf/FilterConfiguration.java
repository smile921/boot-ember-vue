package org.smile921.app.conf;

import org.smile921.app.conf.filter.RewriteFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean getFilterRegistration(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new RewriteFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter(RewriteFilter.REWRITE_TO,"/index.html");
        bean.addInitParameter(RewriteFilter.REWRITE_PATTERNS,"/ui/.*");
        bean.setName("rewriteFilter");
        bean.setOrder(1);
        return bean;
    }
}
