package com.minhasfinancas.service.impl;



import java.util.Optional;

import com.minhasfinancas.exception.ErroAutenticacao;
import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.model.Usuario;
import com.minhasfinancas.repository.UsuarioRepository;
import com.minhasfinancas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository2) {
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);
     
        if(usuario.isEmpty()){
            throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }

        if(!usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha invalida");
        }

        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return repository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean op = repository.existsByEmail(email);
        if(op) {
            throw new RegraDeNegocioException(
                                HttpStatus.BAD_REQUEST,
                                "Ja existe um usuario cadastrado com esse email"
                                );
        }
    }

    @Override
    public Optional<Usuario> obterPorId(Long id) {
        return repository.findById(id);
    }
    


}
