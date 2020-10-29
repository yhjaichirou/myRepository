package com.yhj.pdj;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.yhj.pdj.kit.DesUtil;
import com.yhj.pdj.kit.EncryptDesUtils;

//@EnableScheduling
//@EnableCaching
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PdjApplication {

	public static void main(String[] args) {
		
		String a  = EncryptDesUtils.encrypt("杨海军",null);
		String cert  = EncryptDesUtils.encrypt("152601199110154153",null);
		String phonumber  = EncryptDesUtils.encrypt("18647410031",null);
		String ss = Long.toString(System.currentTimeMillis(), 100);
		System.out.println(ss);
		try {
			
			String a2 = EncryptDesUtils.decrypt(a);
			String cert2  = EncryptDesUtils.decrypt(cert);
			String phonumber2  = EncryptDesUtils.decrypt(phonumber);
		
			String name2 = DesUtil.encode("杨海军",null);
			System.out.println(name2);
			String name = DesUtil.decode(name2,null);
			System.out.println(name);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		SpringApplication.run(PdjApplication.class, args);
	}

}
