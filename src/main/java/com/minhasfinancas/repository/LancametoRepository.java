package com.minhasfinancas.repository;

import com.minhasfinancas.model.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancametoRepository extends JpaRepository<Lancamento, Long> {
    
}
