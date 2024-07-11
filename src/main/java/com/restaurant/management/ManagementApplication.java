package com.restaurant.management;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;



@SpringBootApplication  (exclude = {SecurityAutoConfiguration.class , ManagementWebSecurityAutoConfiguration.class })
public class ManagementApplication {

	public static void main(String[] args) { 
		SpringApplication.run(ManagementApplication.class, args);
	}

	
}

    
