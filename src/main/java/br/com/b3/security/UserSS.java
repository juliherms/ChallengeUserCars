package br.com.b3.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Classe responsável por atender as regras do spring security
 * @author j.a.vasconcelos
 *
 */
public class UserSS implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String login;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {};
	
	public UserSS(Long id,String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getPassword() {
		return senha;
	}

	public String getUsername() {
		return login;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	
	public boolean isAccountNonLocked() {
		return true;
	}

	
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	public boolean isEnabled() {
		return true;
	}
}
