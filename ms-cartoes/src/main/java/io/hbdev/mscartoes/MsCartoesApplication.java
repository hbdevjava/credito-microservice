package io.hbdev.mscartoes;



import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
@EnableRabbit
public class MsCartoesApplication {

	public static void main(String[] args) {
		log.info("Informação: {}", "teste info");
		log.error("Error: {}", "teste erro");
		log.warn("Warn: {}", "teste warn");
		SpringApplication.run(MsCartoesApplication.class, args);
	}

}
