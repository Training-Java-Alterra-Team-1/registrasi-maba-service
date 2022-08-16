package com.mahasiswabaru.register;

import com.mahasiswabaru.register.dto.DegreesDto;
import com.mahasiswabaru.register.services.DegreesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(DegreesService degreesService){
		return args ->{
			degreesService.addDegree(new DegreesDto("S1"));
			degreesService.addDegree(new DegreesDto("S2"));
			degreesService.addDegree(new DegreesDto("S3"));
		};
	}
}
