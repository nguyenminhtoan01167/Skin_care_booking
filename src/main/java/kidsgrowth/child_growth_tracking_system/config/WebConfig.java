package kidsgrowth.child_growth_tracking_system.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;  // Import @NonNull annotation
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.context.annotation.Bean;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {  // Add @NonNull here
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedOrigins("http://localhost:8080", "http://localhost:5500")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}