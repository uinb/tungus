package tech.uinb.tungus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                //.host("127.0.0.1:8080")
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.uinb.tungus.controller"))

                .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInf() {

        return new ApiInfoBuilder()
                .title("tungus")
                .description("Exploere Api Description")
                .version("1.0")
                .build();

    }
}

