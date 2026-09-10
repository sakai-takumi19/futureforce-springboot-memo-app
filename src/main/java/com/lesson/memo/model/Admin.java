package com.lesson.memo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "姓を入力してください")
	@Column(nullable = false, length = 255)
	private String last_name;
	
	@NotBlank(message = "名を入力してください")
	@Column(nullable = false, length = 255)
	private String first_name;
	
	@NotBlank(message = "メールアドレスの入力をしてください")
	@Column(nullable = false, unique = true, length = 255)
	private String email;
	
	@NotBlank(message = "パスワードを入力してください")
	@Column(nullable = false, length = 255)
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String created_at;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updated_at;
	
	
}