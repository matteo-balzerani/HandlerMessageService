package com.neurosevent.messages.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConfigurationProperties(prefix = "swagger")
@EnableSwagger2
public class SwaggerConfiguration {
                               
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
//            .apis(RequestHandlerSelectors
//                .basePackage("net.guides.springboot2.springboot2swagger2.controller"))
            .paths(PathSelectors.regex("/.*"))
            .build().apiInfo(apiEndPointsInfo());
    }
	
	
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Handler Message")
            .description("Handler Message documentation")
            .contact(new Contact("Matteo Balzerani", "_NervousEvents_", "matteo.balzerani@gmail.com"))
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("0.0.1-SNAPSHOT")
            .build();
    }
}