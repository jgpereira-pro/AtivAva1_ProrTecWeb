package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.AmbienteRequestDTO;
import joao.atividade_progweb.dto.response.AmbienteResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.service.AmbienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ambientes")
public class AmbienteController {

    private final AmbienteService ambienteService;

    public AmbienteController(AmbienteService ambienteService) {
        this.ambienteService = ambienteService;
    }

    @GetMapping("/listar")
    public List<Ambiente> listarTodos() {
        return ambienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ambiente> listarPorId(@PathVariable int id) {
        Ambiente ambiente = ambienteService.listarPorId(id);
        if (ambiente != null) {
            return ResponseEntity.ok(ambiente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/criar")
    public AmbienteResponseDTO criar(@Valid @RequestBody AmbienteRequestDTO ambienteRequestDTO) {
        return ambienteService.salvar(ambienteRequestDTO);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AmbienteResponseDTO> atualizar(@PathVariable int id, @Valid @RequestBody AmbienteRequestDTO ambienteRequestDTO) {
        AmbienteResponseDTO dto = ambienteService.atualizar(id, ambienteRequestDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (ambienteService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}