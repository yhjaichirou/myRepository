package com.yhj.singlesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yhj.singlesign.utils.EncryptDesUtils;

public class textMain {

	public static void main(String[] args) {
		
		try {
			String pwdEnctypt = EncryptDesUtils.encrypt("yhj911015");
			System.out.println(pwdEnctypt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SpringApplication.run(textMain.class, args);
	}

}
