package com.mahasiswabaru.register;

import com.mahasiswabaru.register.dto.DegreesDto;
import com.mahasiswabaru.register.dto.DepartmentDto;
import com.mahasiswabaru.register.dto.MajorsDto;
import com.mahasiswabaru.register.services.InitialDataService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableJpaAuditing
@EnableEncryptableProperties
@EnableEurekaClient
public class RegisterApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(InitialDataService initData){
		return args ->{
			initData.addDegree(new DegreesDto("S1"));
			initData.addDegree(new DegreesDto("S2"));
			initData.addDegree(new DegreesDto("S3"));

			initData.addDepartment(new DepartmentDto("Mathematics and Natural Science"));
			initData.addDepartment(new DepartmentDto("Electrical Engineering"));
			initData.addDepartment(new DepartmentDto("Industrial Technology"));

			initData.addMajor(new MajorsDto("Mathematics", 1, 1));
			initData.addMajor(new MajorsDto("Mathematics", 1, 2));
			initData.addMajor(new MajorsDto("Mathematics", 1, 3));
			initData.addMajor(new MajorsDto("Statistics", 1, 1));
			initData.addMajor(new MajorsDto("Statistics", 1, 2));
			initData.addMajor(new MajorsDto("Statistics", 1, 3));
			initData.addMajor(new MajorsDto("Chemistry", 1,1));
			initData.addMajor(new MajorsDto("Chemistry", 1,2));
			initData.addMajor(new MajorsDto("Chemistry", 1,3));
			initData.addMajor(new MajorsDto("Biology", 1,1));
			initData.addMajor(new MajorsDto("Biology", 1,2));
			initData.addMajor(new MajorsDto("Biology", 1,3));
			initData.addMajor(new MajorsDto("Physics", 1,1));
			initData.addMajor(new MajorsDto("Physics", 1,2));
			initData.addMajor(new MajorsDto("Physics", 1,3));
			initData.addMajor(new MajorsDto("Astronomics", 1,1));
			initData.addMajor(new MajorsDto("Astronomics", 1,2));
			initData.addMajor(new MajorsDto("Astronomics", 1,3));
			initData.addMajor(new MajorsDto("Computer Science", 1,1));
			initData.addMajor(new MajorsDto("Computer Science", 1,2));
			initData.addMajor(new MajorsDto("Computer Science", 1,3));

			initData.addMajor(new MajorsDto("Electrical Engineering", 2,1));
			initData.addMajor(new MajorsDto("Electrical Engineering", 2,2));
			initData.addMajor(new MajorsDto("Electrical Engineering", 2,3));
			initData.addMajor(new MajorsDto("Automation System Engineering", 2,1));
			initData.addMajor(new MajorsDto("Automation System Engineering", 2,2));
			initData.addMajor(new MajorsDto("Automation System Engineering", 2,3));
			initData.addMajor(new MajorsDto("Power System Engineering", 2,1));
			initData.addMajor(new MajorsDto("Power System Engineering", 2,2));
			initData.addMajor(new MajorsDto("Power System Engineering", 2,3));

			initData.addMajor(new MajorsDto("Industrial Engineering", 1, 1));
			initData.addMajor(new MajorsDto("Industrial Engineering", 1, 2));
			initData.addMajor(new MajorsDto("Industrial Engineering", 1, 3));
		};
	}
}
