package io.hbdev.msavaliador;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients//HABILITA A COMINICAÇAO SINCRONA ENTRE OS MS
@EnableRabbit
public class MsAvaliadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAvaliadorApplication.class, args);
	}

}
