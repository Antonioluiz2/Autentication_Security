package com.luiz.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.luiz.Security.repository.UserRepository;
import com.luiz.Security.service.SSUserDatailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
		@Bean
		public static BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		@Autowired
		private SSUserDatailsService userDetailsService;
		@Autowired
		private UserRepository userRepository;
		@Autowired
		private UserDetailsService UserDetailsServiceBean() throws Exception{
			return new SSUserDatailsService(userRepository);
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.authorizeRequests()
			.antMatchers("/", "/h2-console/**").permitAll()
			.antMatchers("/admin").access("hasAnyAuthority('ADMIN')")
			.anyRequest().authenticated()
			.and().formLogin()//para custumizar o formulario ->.loginPage("/login").permitAll();
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
			.and().httpBasic();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		}
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//			auth.inMemoryAuthentication()
//			.withUser("luiz").password(passwordEncoder().encode("luiz2021"))
//			.authorities("ADMIN")
//			.and()
//			.withUser("user")
//			.password(passwordEncoder().encode("password")).authorities("USER");
			auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		}

		public SSUserDatailsService getUserDetailsService() {
			return userDetailsService;
		}

		public void setUserDetailsService(SSUserDatailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
		}
		
}
