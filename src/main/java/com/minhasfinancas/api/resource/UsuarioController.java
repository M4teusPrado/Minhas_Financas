package com.minhasfinancas.api.resource;

import java.net.URI;

import com.minhasfinancas.dto.UsuarioDTO;
import com.minhasfinancas.model.Usuario;
import com.minhasfinancas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/autenticar")
    public ResponseEntity<Usuario> autenticar( @RequestBody UsuarioDTO dto ) {

        Usuario usuarioAutenticacao = service.autenticar(dto.getEmail(), dto.getSenha());

        return ResponseEntity.ok(usuarioAutenticacao);
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar( @RequestBody UsuarioDTO dto ) {
        
        Usuario usuario = Usuario.builder()
                        .nome(dto.getNome())
                        .email(dto.getEmail())
                        .senha(dto.getSenha())
                        .build();

        Usuario usuarioSalvo = service.salvarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder
                                            .fromCurrentRequest()
                                            .path("/{id}")
                                            .buildAndExpand(usuarioSalvo.getId())
                                            .toUri();   
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }
}