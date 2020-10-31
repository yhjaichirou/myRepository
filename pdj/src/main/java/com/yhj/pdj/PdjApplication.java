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
		
		String a  = EncryptDesUtils.encrypt("杨海军","chinaunicom");
		String cert  = EncryptDesUtils.encrypt("152601199110154153","chinaunicom");
		String phonumber  = EncryptDesUtils.encrypt("18647410031","chinaunicom");
		System.out.println(a);
		System.out.println(cert);
		System.out.println(phonumber);
		try {
			
			String a2 = EncryptDesUtils.decrypt(a);
			String cert2  = EncryptDesUtils.decrypt(cert);
			String phonumber2  = EncryptDesUtils.decrypt(phonumber);
		
			String name2 = DesUtil.encode("杨海军",null);
			System.out.println(name2);
			String name = DesUtil.decode(name2,null);
			System.out.println(name);
			
			String dateTimeStr = Long.toString(System.currentTimeMillis(), 100);
			String abc = "";
			for(int i =0;i<2;i++) {
				abc += (char)(Math.random()*26+'A');	
			}
			String appointCode = abc +dateTimeStr;
			System.out.println(appointCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		SpringApplication.run(PdjApplication.class, args);
	}

}
