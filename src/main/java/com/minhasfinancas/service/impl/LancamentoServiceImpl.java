package com.minhasfinancas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.model.Lancamento;
import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.repository.LancametoRepository;
import com.minhasfinancas.service.LancamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancametoRepository repository;

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        validar(lancamento);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    @Transactional()
    public List<Lancamento>  buscar(Lancamento lancamentoFiltro) {
        Example<Lancamento> lancamentos = Example.of(
                                            lancamentoFiltro, 
                                            ExampleMatcher.matching()
                                                .withIgnoreCase()
                                                .withStringMatcher(StringMatcher.CONTAINING) 
                                            );
                                                   
        return repository.findAll(lancamentos);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);
    }

    @Override
    public void validar(Lancamento lancamento) {
        
        if ( lancamento.getDescricao()==  null|| lancamento.getDescricao().trim() == "") {
            throw new RegraDeNegocioException(HttpStatus.BAD_REQUEST, "Informe uma descrição valida");
        }

        if ( lancamento.getMes() == null || lancamento.getMes()  < 1 || lancamento.getMes() > 12 ) {
            throw new RegraDeNegocioException (HttpStatus.BAD_REQUEST, "Informa um Mês valido");
        }

        if( lancamento.getAno() == null || lancamento.getAno().toString().length() != 4 ) {
            throw new RegraDeNegocioException (HttpStatus.BAD_REQUEST, "Informe um ano valido");
        }

        if( lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null ) {
            throw new RegraDeNegocioException (HttpStatus.BAD_REQUEST, "Informe o usuario");   
        }

        if( lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1 ) {
            throw new RegraDeNegocioException (HttpStatus.BAD_REQUEST, "Informe um valor valido");
        }

        if ( lancamento.getTipo() == null ) {
            throw new RegraDeNegocioException (HttpStatus.BAD_REQUEST, "Informe um tipo de lancamento");
        }
        
    }

    @Override
    public Optional<Lancamento> obterPorId(Long id) {
        return repository.findById(id);
    }


    
}
