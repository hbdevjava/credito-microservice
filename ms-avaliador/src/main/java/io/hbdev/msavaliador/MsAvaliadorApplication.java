package io.hbdev.msavaliador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class MsAvaliadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAvaliadorApplication.class, args);
	}

}
