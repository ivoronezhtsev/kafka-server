package ru.xpendence.kafkaserver;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@SpringBootApplication
@EnableKafka
@EnableAutoConfiguration(exclude = {
        KafkaAutoConfiguration.class
})
@ComponentScan(basePackages = {
        "ru.xpendence.kafkaserver.config",
        "ru.xpendence.kafkaserver.controller",
        "ru.xpendence.kafkaserver.service",
})
@PropertySource({
        "classpath:kafka.properties"
})
public class KafkaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServerApplication.class, args);
    }

    @Bean
    public JsonDeserializer jsonDeserializer() {
        return new JsonDeserializer() {
            @Override
            public Object deserialize(JsonParser p, DeserializationContext context) {
                return null;
            }
        };
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.xpendence.kafkaserver"))
                .paths(PathSelectors.any())
                .build();
    }
}

