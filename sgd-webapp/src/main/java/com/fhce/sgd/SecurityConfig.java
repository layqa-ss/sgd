package com.fhce.sgd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fhce.sgd.util.CustomUserDetailsContextMapper;

@Configuration
@EnableWebSecurity
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

	@Bean
	LdapAuthenticationProvider ldapAuthenticationProvider() {
		LdapAuthenticationProvider auth = new LdapAuthenticationProvider(authenticator());
		auth.setUserDetailsContextMapper(mapper);
		return auth;
	}

	@Bean
	BindAuthenticator authenticator() {

		FilterBasedLdapUserSearch search = new FilterBasedLdapUserSearch("ou=people", "(uid={0})", contextSource());
		BindAuthenticator authenticator = new BindAuthenticator(contextSource());
		authenticator.setUserSearch(search);
		return authenticator;
	}

	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		DefaultSpringSecurityContextSource dsCtx = new DefaultSpringSecurityContextSource(
				"ldap://localhost:8389/dc=springframework,dc=org");
		dsCtx.setUserDn("uid=admin,ou=people,dc=springframework,dc=org");
		dsCtx.setPassword("password");
		return dsCtx;
	}

}
