package com.lesson.memo.controller;

import jakarta.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

	AdminController(AdminRepository adminRepository,
			PasswordEncoder passwordEncoder) {

		this.adminRepository = adminRepository;

		this.passwordEncoder = passwordEncoder;
	}

	//	ユーザー登録処理

	@GetMapping("/signup")
	public String signupfrom(Admin admin) {
		return "signup";
	}

	//	ユーザー登録処理
	@PostMapping("/signup")
	public String signup(@ModelAttribute @Valid Admin admin,
			BindingResult result) {
		if (result.hasErrors()) {
			return "signup";
		}

		String encodePwd = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodePwd);

		adminRepository.save(admin);

		return "redirect:/admin/signin";

	}

	//ログイン画面	
	@GetMapping("/signin")
	public String signinfrom(Admin admin) {
		return "signin";
	}

}
