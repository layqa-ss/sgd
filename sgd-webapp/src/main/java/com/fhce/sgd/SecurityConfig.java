package com.fhce.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fhce.sgd.util.CustomUserDetailsContextMapper;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsContextMapper mapper;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(ldapAuthenticationProvider());

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
            .failureUrl("/login-error")
        )
        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				        		.invalidateHttpSession(true)
								.clearAuthentication(true)
								.deleteCookies("JSESSIONID")
        						.logoutSuccessUrl("/login?faces-redirect=true")
        						.permitAll()
        		)
        .csrf(AbstractHttpConfigurer::disable);

		return http.build();

	}

	LdapAuthenticationProvider ldapAuthenticationProvider() {
		LdapAuthenticationProvider auth = new LdapAuthenticationProvider(authenticator());
		auth.setUserDetailsContextMapper(mapper);
		return auth;
	}
	
	@Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

	@Bean
	BindAuthenticator authenticator() {

		FilterBasedLdapUserSearch search = new FilterBasedLdapUserSearch("ou=people", "(uid={0})", contextSource());
		log.info(search.toString());
		BindAuthenticator authenticator = new BindAuthenticator(contextSource());
		authenticator.setUserSearch(search);
		return authenticator;
	}
	
//	@Bean
//	public DefaultSpringSecurityContextSource contextSource() {
//		DefaultSpringSecurityContextSource dsCtx = new DefaultSpringSecurityContextSource(
//				"ldap://ldapmaster.fhce/dc=fhce,dc=edu,dc=uy");
//		return dsCtx;
//	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		DefaultSpringSecurityContextSource dsCtx = new DefaultSpringSecurityContextSource(
				"ldap://localhost:8389/dc=springframework,dc=org");
//		dsCtx.setUserDn("uid=admin,ou=people,dc=springframework,dc=org");
//		dsCtx.setPassword("password");
		return dsCtx;
	}

}
