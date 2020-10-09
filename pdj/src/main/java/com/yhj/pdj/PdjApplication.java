package com.yhj.pdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@EnableScheduling
//@EnableCaching
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PdjApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdjApplication.class, args);
	}

}
