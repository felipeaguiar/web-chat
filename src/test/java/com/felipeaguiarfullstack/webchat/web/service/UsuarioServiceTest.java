package com.felipeaguiarfullstack.webchat.web.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.felipeaguiarfullstack.webchat.web.model.Usuario;
import com.felipeaguiarfullstack.webchat.web.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
public class UsuarioServiceTest {
	
    @TestConfiguration
    static class UsuarioServiceImplTestContextConfiguration {
  
        @Bean
        public UsuarioService employeeService() {
            return new UsuarioService();
        }
    }
	
	@Autowired
	private UsuarioService usuarioService;
	
    @MockBean
    private UsuarioRepository usuarioRepository;
    
    @MockBean
    private EmailService emailService;
    
    @Before
    public void setUp() {
        Usuario usuario = new Usuario();
        usuario.setEmail("feliperdaguiar@gmail.com");
        usuario.setPrimeiroLogin(true);
     
        Mockito.when(usuarioRepository.findByEmail(usuario.getEmail()))
          .thenReturn(Optional.of(usuario));
    }
    

	@Test
	public void testIniciarChat() {
		String email = "feliperdaguiar@gmail.com";
		String enderecoIp = "2804:7f1:e107:48fa:1e4:f53:498d:aec0";
		
		Usuario usuario = usuarioService.iniciarChat(email, enderecoIp);
		
		assertEquals(usuario.getPrimeiroLogin(), false);
	}

}
