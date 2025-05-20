import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")  // Разрешить все пути
            .allowedOrigins("*")    // Разрешить все домены
            .allowedMethods("*")    // Разрешить все HTTP-методы (GET, POST, etc.)
            .allowedHeaders("*")    // Разрешить все заголовки
    }
}