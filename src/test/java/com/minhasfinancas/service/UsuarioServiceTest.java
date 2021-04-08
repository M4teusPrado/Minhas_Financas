package com.minhasfinancas.service;

import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.repository.UsuarioRepository;
import com.minhasfinancas.service.impl.UsuarioServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class UsuarioServiceTest {
    
    private UsuarioService service;

    @MockBean
    private UsuarioRepository repository;


    @BeforeEach 
    public void setUp(){
        service = new UsuarioServiceImpl(repository);
    }

    // @Test 
    // public void deveAutenticarUmUsuarioComSucesso () {

    //     Assertions.assertDoesNotThrow(Exception.class, new Executable() {

    //         @Override
    //         public void execute() throws Throwable {
               
    //             //Cenario
    //             String email = "email@gmail.com";
    //             String senha = "senha";

    //             //Acao
    //             Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
    //             Mockito.when( repository.findByEmail(email) ).thenReturn(Optional.of(usuario));

    //             //acao 
    //             Usuario result = service.autenticar(email, senha);

    //             //Verificacao
    //             Assertions.assertNotNull(result);
    //         }
    //     });
        
    // }

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
                Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

                //acao
                service.validarEmail("email@gmail.com");

            }
        });
    }


}
