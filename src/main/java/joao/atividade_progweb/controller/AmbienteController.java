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
    public List<AmbienteResponseDTO> listarTodos() {
        return ambienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public AmbienteResponseDTO listarPorId(@PathVariable int id) {
        return ambienteService.listarPorId(id);
    }

    @PostMapping("/criar")
    public AmbienteResponseDTO criar(@Valid @RequestBody AmbienteRequestDTO ambienteRequestDTO) {
        return ambienteService.salvar(ambienteRequestDTO);
    }

    @PutMapping("/atualizar/{id}")
    public AmbienteResponseDTO atualizar(@PathVariable int id, @Valid @RequestBody AmbienteRequestDTO ambienteRequestDTO) {
        return ambienteService.atualizar(id, ambienteRequestDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        ambienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}