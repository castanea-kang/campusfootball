package com.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"com.campus","cn.com.hisee.common"})
@ImportResource(value = {"classpath:campus-dao.xml", "classpath:campus-domain.xml"})
@EnableAutoConfiguration//启用自动配置
@SpringBootApplication

@MapperScan("com.campus.mybatis.mapper")
public class CampusCmsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}
	public static void main(String[] args) {
		SpringApplication.run(CampusCmsApplication.class, args);
	}
}
