package com.felipeaguiarfullstack.webchat.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
