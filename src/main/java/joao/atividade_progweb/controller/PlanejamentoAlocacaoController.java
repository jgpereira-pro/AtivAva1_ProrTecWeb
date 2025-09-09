package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.PlanejamentoAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.PlanejamentoAlocacaoResponseDTO;
import joao.atividade_progweb.entity.PlanejamentoAlocacao;
import joao.atividade_progweb.service.PlanejamentoAlocacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planejamentos")
public class PlanejamentoAlocacaoController {

    private final PlanejamentoAlocacaoService planejamentoService;

    public PlanejamentoAlocacaoController(PlanejamentoAlocacaoService planejamentoService) {
        this.planejamentoService = planejamentoService;
    }

    @PostMapping("/criar")
    public PlanejamentoAlocacaoResponseDTO criar(@Valid @RequestBody PlanejamentoAlocacaoRequestDTO requestDTO) {
        return planejamentoService.salvar(requestDTO);
    }

    @GetMapping("/listar")
    public List<PlanejamentoAlocacao> listarTodos() {
        return planejamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoAlocacaoResponseDTO> listarPorId(@PathVariable int id) {
        PlanejamentoAlocacaoResponseDTO dto = planejamentoService.listarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PlanejamentoAlocacaoResponseDTO> atualizar(@PathVariable int id, @Valid @RequestBody PlanejamentoAlocacaoRequestDTO requestDTO) {
        PlanejamentoAlocacaoResponseDTO dto = planejamentoService.atualizar(id, requestDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (planejamentoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}