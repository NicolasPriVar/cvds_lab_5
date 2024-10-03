package com.hazinlab.gestortareasbackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(@NonNull CorsRegistry registry) {
    // Permitir solicitudes desde cualquier origen (incluyendo file://)
    registry
      .addMapping("/**")
      .allowedOrigins("*") // Permitir todos los orígenes
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders("*");
  }
}
