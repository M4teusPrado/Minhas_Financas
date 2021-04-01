package com.minhasfinancas.service;

import com.minhasfinancas.model.Usuario;

public interface UsuarioService {
    
    Usuario autenticar(String name, String senha);

    Usuario salvarUsuario(Usuario usuario);

    void validarEmail(String email);

}
