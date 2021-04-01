package com.minhasfinancas.service.impl;

import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.model.Usuario;
import com.minhasfinancas.repository.UsuarioRepository;
import com.minhasfinancas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario autenticar(String name, String senha) {
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void validarEmail(String email) {
        
        boolean op = repository.existsByEmail(email);

        if(op) {
            throw new RegraDeNegocioException("Ja existe um usuario cadastrado com esse email");
        }
    }
    


}
