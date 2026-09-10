package com.lesson.memo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lesson.memo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
//DBの中身を探している処理	
	Optional <Admin> findByemail(String email);
	Optional <Admin> findBypassword(String password);
	
	
}