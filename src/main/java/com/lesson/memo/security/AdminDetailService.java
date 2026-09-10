package com.lesson.memo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Service

public class AdminDetailService  implements UserDetailsService{

//	repository を使う
@Autowired
	private AdminRepository adminrepository;
	
	@Override
//spring securityを通してusernameに　emailの情報が入る
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		
		
//UserDetailsService DBからユーザーを探す
//		入力されたemailを元にDBの情報を取得している
		Admin user = adminrepository.findByemail(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		
	   
	    return User.withUsername(user.getEmail())
	    		.password(user.getPassword())
	    		.build();
	}
	

	
}