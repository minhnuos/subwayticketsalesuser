package com.nguyenvanminh.subwayticketsalesuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nguyenvanminh.subwayticketsalesuser.service.impl.UserLoginServiceImpl;

@SpringBootApplication
public class SubwayticketsalesuserApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	UserLoginServiceImpl loginServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(SubwayticketsalesuserApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/").hasAnyRole("USER").anyRequest().authenticated().and().
		formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?e=error").permitAll().and().logout().permitAll().
		and().exceptionHandling().accessDeniedPage("/login");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/images/**","/scss/**","/registration","/assets/**","/fonts/**","/regitrationConfirm");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.loginServiceImpl);
	}

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
