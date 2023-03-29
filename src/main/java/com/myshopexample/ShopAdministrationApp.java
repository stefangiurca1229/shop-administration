package com.myshopexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class ShopAdministrationApp {

	public static void main(String[] args) {
		SpringApplication.run(ShopAdministrationApp.class, args);
	}
}
