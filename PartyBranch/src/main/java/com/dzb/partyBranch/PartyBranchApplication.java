package com.dzb.partyBranch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScans({@ComponentScan("com.**.controller"),@ComponentScan("com.**.interceptor"),@ComponentScan("com.**.serviceImp"),@ComponentScan("com.**.service")})
@EnableJpaRepositories(basePackages = "com.**.repository")
@EntityScan("com.**.po")
@EnableTransactionManagement
public class PartyBranchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyBranchApplication.class, args);
	}

}
