package com.minhasfinancas.api.resource;

import java.util.Optional;

import com.minhasfinancas.dto.LancamentoDTO;
import com.minhasfinancas.exception.RegraDeNegocioException;
import com.minhasfinancas.model.Lancamento;
import com.minhasfinancas.model.Usuario;
import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.model.enums.TipoLancamento;
import com.minhasfinancas.service.LancamentoService;
import com.minhasfinancas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {
    
    @Autowired
    private LancamentoService service;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Lancamento> salvar( @RequestBody LancamentoDTO dto) {
        Lancamento lancamento = converter(dto);
        lancamento = service.salvar(lancamento);
        return ResponseEntity.ok(lancamento);
        
    }

    @PutMapping("/{id}")
    public Optional<Object> atualizar( @PathVariable Long id, @RequestBody LancamentoDTO dto ){
        return service.obterPorId(id).map( entity -> {
            Lancamento lancamento = converter(dto);
            lancamento.setId(entity.getId());
            service.atualizar(lancamento);
            return ResponseEntity.ok(lancamento);
        });
    }

    private Lancamento converter (LancamentoDTO dto ) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());

        Usuario usuario = usuarioService.obterPorId(dto.getIdUsuario())
                                .orElseThrow( () -> new RegraDeNegocioException(
                                        HttpStatus.BAD_REQUEST,
                                        "Usuario não encontrado"));
        lancamento.setUsuario(usuario);
        lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));

        return lancamento;
    }
}
