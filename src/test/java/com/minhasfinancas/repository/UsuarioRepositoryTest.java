package com.minhasfinancas.repository;

import com.minhasfinancas.model.Usuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith( SpringExtension.class )
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmEmail(){
        //Cenario
        Usuario user = Usuario.builder().nome("usuario").email("usuario@gmail.com").build();
        repository.save(user);

        //Ação / Execução
        boolean resultado = repository.existsByEmail("usuario@gmail.com");

        //Verificação
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail(){
            //Cenario
            repository.deleteAll();
    
            //Ação / Execução
            boolean resultado = repository.existsByEmail("usuario@gmail.com");
    
            //Verificação
            Assertions.assertThat(resultado).isFalse();
    }
}
