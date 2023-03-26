package com.taskPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TaskPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerApplication.class, args);
	}

	public Docket docket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TaskPlanner")
				.apiInfo(ApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.taskPlanner"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo ApiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("TaskPlanner")
				.description("A Task Planner")
				.licenseUrl("kapilkhursade.210@gamil.com")
				.version("1.0")
				.build();
	}
}

