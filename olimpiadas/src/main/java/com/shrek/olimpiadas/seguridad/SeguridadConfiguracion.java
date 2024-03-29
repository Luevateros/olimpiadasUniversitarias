package com.shrek.olimpiadas.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

	@Autowired
	private DetalleUsuarioServicio detalleUsuarioServicio;

	/*
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new PasswordEnconderTest();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception{
    	http.authorizeRequests().anyRequest().permitAll().and()
				.formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("correo").passwordParameter("password")
				.defaultSuccessUrl("/").permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/login").permitAll();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		PasswordEnconderTest passwordEnconder = new PasswordEnconderTest();
		auth.userDetailsService((UserDetailsService) detalleUsuarioServicio).passwordEncoder(passwordEnconder);
	}
    
}
