package com.felipeaguiarfullstack.webchat.web.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		
		Set<SimpleGrantedAuthority> authority = new HashSet<>();
		authority.add(new SimpleGrantedAuthority("USER"));
		
		return new User(username, encode(usuario.getCpf()), authority);
	}
	
	private String encode(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}
