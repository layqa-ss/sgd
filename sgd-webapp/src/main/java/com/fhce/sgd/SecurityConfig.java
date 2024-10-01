package com.fhce.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fhce.sgd.service.CustomUsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomUsuarioDetailsService customUserDetailsService; 

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http.authorizeHttpRequests(auth ->
			auth.requestMatchers("/css/**").permitAll()
			.requestMatchers("/images/**").permitAll()
			.anyRequest().authenticated()
	        )
	        .formLogin(login ->
	        	login.loginPage("/login")
	        	.loginProcessingUrl("/login")
	            .defaultSuccessUrl("/index.jsf", true)
	            .permitAll()
	        )
	        .logout(logout -> logout.invalidateHttpSession(true)
	        						.clearAuthentication(true)
	        						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        						.logoutSuccessUrl("/login?logout")
	        						.permitAll()
	        		)
	        .csrf(AbstractHttpConfigurer::disable);

		return http.build();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

	}

}
