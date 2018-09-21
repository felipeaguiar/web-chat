package com.felipeaguiarfullstack.webchat.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipeaguiarfullstack.webchat.web.model.Palavra;

public interface PalavraRepository extends JpaRepository<Palavra, Long> {
	
}
