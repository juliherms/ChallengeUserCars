package br.com.b3.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe respons�vel por configurar as url's de seguran�a do sistema.
 * 
 * @author j.a.vasconcelos
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

	private static final String[] PUBLIC_MATCHERS = { "/api/users/**" };

	/**
	 * Configura as permiss�es de acesso aos endpoints
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		for (String string : PUBLIC_MATCHERS) {
			LOG.info("Urls  p�blicas - " + string);
		}

		http.cors().and().csrf().disable();

		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	/**
	 * Metodo respons�vel por realizar a configura��o do token para autentica��o.
	 * Intercepta a autenticacao e encripta o password.
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncorder());
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

	/**
	 * Realiza a configura�ao do cors para chamada externa Permite acesso ao
	 * endpoints
	 * 
	 * @return
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS")); // libera o cors
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

}
