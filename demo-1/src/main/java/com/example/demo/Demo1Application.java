package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Entities")
@ComponentScan(basePackages = "com.example.Controllers")
@ComponentScan(basePackages = "com.example.Servicos")
@ComponentScan(basePackages = "com.example.Excecoes")
@ComponentScan(basePackages = "com.example.Repositorio_Interfaces")
@ComponentScan(basePackages = "com.example.Repositorio_Interfaces_Implementacao")
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}
