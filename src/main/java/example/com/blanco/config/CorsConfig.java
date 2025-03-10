package example.com.blanco.config;


import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/users/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST")
                .allowCredentials(false);

        registry.addMapping("users/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("DELETE", "PUT", "PATCH", "GET")
                .allowCredentials(true);

        registry.addMapping("/transfer/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST")
                .allowCredentials(true);
    }
}
