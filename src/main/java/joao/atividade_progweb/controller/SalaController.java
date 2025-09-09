package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.SalaRequestDTO;
import joao.atividade_progweb.entity.Sala;
import joao.atividade_progweb.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping("/criar")
    public Sala criar(@Valid @RequestBody SalaRequestDTO salaRequestDTO) {
        return salaService.salvar(salaRequestDTO);
    }

    @GetMapping("/listar")
    public List<Sala> listarTodas() {
        return salaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> listarPorId(@PathVariable Long id) {
        Sala sala = salaService.listarPorId(id);
        if (sala != null) {
            return ResponseEntity.ok(sala);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Sala> atualizar(@PathVariable Long id, @Valid @RequestBody SalaRequestDTO salaRequestDTO) {
        Sala salaAtualizada = salaService.atualizar(id, salaRequestDTO);
        if (salaAtualizada != null) {
            return ResponseEntity.ok(salaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (salaService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}