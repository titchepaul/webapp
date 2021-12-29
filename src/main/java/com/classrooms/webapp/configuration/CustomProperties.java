package com.classrooms.webapp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration  //ce bean indique que cette classe est utilsé pour la config de l'application
@ConfigurationProperties(prefix = "com.classrooms.webapp")
public class CustomProperties {

    private String apiUrl;
}
