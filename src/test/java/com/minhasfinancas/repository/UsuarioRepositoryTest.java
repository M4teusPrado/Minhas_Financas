package com.minhasfinancas.repository;

import java.util.Optional;

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

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados(){
        //Cenario
        Usuario usuario = criarUsuario();
        
        //Acao
        Usuario usuarioSalvo = repository.save(usuario);


        // Verificação
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
    }

    @Test 
    public void deveBuscarUmUsuarioPorEmail(){
        //Cenario
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);


        //verificação
        Optional<Usuario> result = repository.findByEmail("usuario@gmail.com");

        Assertions.assertThat(result.isPresent()).isTrue();
        
    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase(){
        //Ceneario

        //verificaçao
        Optional<Usuario> result = repository.findByEmail("usuario@email.com");

        Assertions.assertThat( result.isPresent() ).isFalse();
    }

    public static Usuario criarUsuario() {
        Usuario usuario = Usuario
                            .builder()
                            .nome("usuario")
                            .email("usuario@gmail.com")
                            .senha("senha")
                            .build();
        return usuario;
    }
}
