package br.com.b3.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Classe respons�vel por configurar as url's de seguran�a do sistema.
 * @author j.a.vasconcelos
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

	private static final String[] PUBLIC_MATCHERS = {"/api/users/**"};
	
	
	/**
	 * Configura as permiss�es de acesso aos endpoints
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		for (String string : PUBLIC_MATCHERS) {
			LOG.info("Urls  p�blicas - " + string);
		}
		
		http.cors().and().csrf().disable();
		
		
		http.authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS).permitAll() 
				.anyRequest().authenticated();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	/**
	 * Metodo respons�vel por retornar um encriptador para criptografar senha da
	 * classe User.
	 * 
	 * @return Objeto para encripta��o.
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncorder() {
		return new BCryptPasswordEncoder();
	}

}
