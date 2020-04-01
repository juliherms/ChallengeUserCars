package br.com.b3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.b3.dto.CredenciaisDTO;
import br.com.b3.dto.CurrentUserDTO;
import br.com.b3.entity.User;
import br.com.b3.security.JwtTokenUtil;
import br.com.b3.service.UserService;

/**
 * Controller responsável por autenticação no sistema
 * 
 * @author j.a.vasconcelos
 *
 */
@RestController
@RequestMapping(value = "/api/signin")
public class SessionController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody CredenciaisDTO credenciaisDTO) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(credenciaisDTO.getLogin(), credenciaisDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final UserDetails userDetails = userDetailsService.loadUserByUsername(credenciaisDTO.getLogin());

		final String token = jwtTokenUtil.generateToken(userDetails);
		final User user = userService.findByLogin(credenciaisDTO.getLogin());
		user.setPassword(null);

		return ResponseEntity.ok(new CurrentUserDTO(token, user));
	}
}
