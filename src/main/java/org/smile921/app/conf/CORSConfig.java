/**
 * 
 */
package org.smile921.app.conf;

import static org.springframework.web.cors.CorsConfiguration.ALL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator
 *
 */
@Configuration
public class CORSConfig {
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                        .allowedOrigins(ALL)
	                        .allowedMethods(ALL)
	                        .allowedHeaders(ALL)
	                        .allowCredentials(true);
	            }
	        };
	    }
}
