package com.ductai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ductai.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findOneByUserNameAndStatus(String userName,int status);
}
