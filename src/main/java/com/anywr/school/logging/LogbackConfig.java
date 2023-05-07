package com.anywr.school.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogbackConfig {

    @Bean
    public static Logger logger() {
        return LoggerFactory.getLogger("com.anywr.school");
    }

}

