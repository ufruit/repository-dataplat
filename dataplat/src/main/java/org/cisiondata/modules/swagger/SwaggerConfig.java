package org.cisiondata.modules.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket createApi() {
		List<ResponseMessage> responseMessages = new ArrayList<>();
		responseMessages.add(new ResponseMessageBuilder()
                		.code(500).message("500 message")
                		.responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder()
                		.code(403).message("403 message")
                		.responseModel(new ModelRef("Forbidden")).build());
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
                .select()  //选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) //对所有api进行监控 RequestHandlerSelectors.basePackage(basePackage)
                .paths(PathSelectors.any()) //对所有路径进行监控
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages);
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger构建api文档")
                .description("简单优雅的restfun风格")
                .termsOfServiceUrl("http://www.cisiondata.com")
                .version("1.0")
                .build();
    }

}
