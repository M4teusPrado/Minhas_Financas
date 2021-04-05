package com.minhasfinancas.repository;

import com.minhasfinancas.model.Usuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith( SpringExtension.class )
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail(){
        //Cenario
        Usuario user = Usuario.builder().nome("usuario").email("usuario@gmail.com").build();
        entityManager.persist(user);

        //Ação / Execução
        boolean resultado = repository.existsByEmail("usuario@gmail.com");

        //Verificação
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail(){
            //Cenario
    
            //Ação / Execução
            boolean resultado = repository.existsByEmail("usuario@gmail.com");
    
            //Verificação
            Assertions.assertThat(resultado).isFalse();
    }
}
