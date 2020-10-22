package com.yhj.pdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.yhj.pdj.kit.EncryptDesUtils;

//@EnableScheduling
//@EnableCaching
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PdjApplication {

	public static void main(String[] args) {
		
		String a  = EncryptDesUtils.encrypt("张翔",null);
		String cert  = EncryptDesUtils.encrypt("152601199110154166",null);
		String phonumber  = EncryptDesUtils.encrypt("18647410096",null);
		System.out.println(a);
		System.out.println(cert);
		System.out.println(phonumber);
		SpringApplication.run(PdjApplication.class, args);
	}

}
