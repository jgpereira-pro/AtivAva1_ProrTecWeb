package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.DiasSemAlocacaoRequestDTO;
import joao.atividade_progweb.entity.DiasSemAlocacao;
import joao.atividade_progweb.service.DiasSemAlocacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bloqueios")
public class DiasSemAlocacaoController {

    private final DiasSemAlocacaoService bloqueioService;

    public DiasSemAlocacaoController(DiasSemAlocacaoService bloqueioService) {
        this.bloqueioService = bloqueioService;
    }

    @PostMapping("/criar")
    public DiasSemAlocacao criar(@Valid @RequestBody DiasSemAlocacaoRequestDTO requestDTO) {
        return bloqueioService.salvar(requestDTO);
    }

    @GetMapping("/listar")
    public List<DiasSemAlocacao> listarTodos() {
        return bloqueioService.listarTodos();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (bloqueioService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}