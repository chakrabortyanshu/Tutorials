package com.anshuman.learning.springbatchexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshuman.learning.springbatchexample.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
}
