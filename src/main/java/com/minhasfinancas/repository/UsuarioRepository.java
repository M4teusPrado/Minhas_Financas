package com.minhasfinancas.repository;

import java.util.Optional;

import com.minhasfinancas.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

    Boolean existsByEmail(String email);

    Optional<Usuario>findByEmail(String email);
}
