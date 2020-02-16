package com.nalaolla.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing  //JPA Auditing 어노테이션들을 모두 활성화
@SpringBootApplication
@ComponentScan("com.nalaolla.www")
public class WwwApplication {

	public static void main(String[] args) {
		SpringApplication.run(WwwApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
