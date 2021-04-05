package com.minhasfinancas.service;

import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.model.Usuario;
import com.minhasfinancas.repository.UsuarioRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class UsuarioServiceTest {
    
    @Autowired
    private UsuarioService service;

    @Autowired 
    private UsuarioRepository repository;

    /*@Test
    public void deveValidarEmail(){

        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
    
                //Cenario 
                repository.deleteAll();

                //acao
                service.validarEmail("email@gmail.com");

            }
        });
    }*/

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado(){

        Assertions.assertThrows(RegraDeNegocioException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
    
                //Cenario 
                Usuario usuario = Usuario.builder().nome("usuario").email("email@gmail.com").build();
                repository.save(usuario);

                //acao
                service.validarEmail("email@gmail.com");

            }
        });
    }


}
