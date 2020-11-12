package com.yhj.singlesign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yhj.singlesign.vo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByAccount(String account);

}
