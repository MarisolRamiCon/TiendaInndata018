package com.inndata.tienda18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class Tienda18Application {

	public static void main(String[] args) {
		SpringApplication.run(Tienda18Application.class, args);
	}

}
