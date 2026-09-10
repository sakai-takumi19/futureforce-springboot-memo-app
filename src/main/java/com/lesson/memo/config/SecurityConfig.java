package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lesson.memo.security.AdminDetailService;

@Configuration
public class SecurityConfig {

	//パスワードエンコード
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	//adminDetailService　を使えるようにする
	private AdminDetailService adminDetailService;

	SecurityConfig(AdminDetailService adminDetailService) {
		this.adminDetailService = adminDetailService;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//securityにadminDetailServiceを使ってユーザー検索をさせる
		http.userDetailsService(adminDetailService);

		//承認で見れるURLの設定
		//auth.requestMatchers permitAll() を使ってログインしなくても公開
		
		http.authorizeHttpRequests(auth -> auth
		.requestMatchers("/admin/signup","/admin/signin","/css/**", "/js/**").permitAll()
		.anyRequest().authenticated()
		)
		.formLogin(form -> form
//		ログイン画面の設定
		.loginPage("/admin/signin")
		.loginProcessingUrl("/admin/signin") 
		.usernameParameter("email") 
		.passwordParameter("password")
//ログイン後のリダイレクト先
		.defaultSuccessUrl("/memo",true)
		.permitAll()
				)
		.logout(auth -> auth
			.logoutUrl("/logout")
			.logoutSuccessUrl("/admin/signin/")
			.permitAll()
				);
		;
		return http.build();
	}
}
