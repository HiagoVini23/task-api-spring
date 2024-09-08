package com.example.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200") // Permite o frontend Angular
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos HTTP necessários
            .allowedHeaders("*") // Permite todos os cabeçalhos
            .exposedHeaders("Authorization") // Expõe o cabeçalho de autorização para o cliente
            .allowCredentials(true); // Permite o envio de cookies ou credenciais
    }
    
}
