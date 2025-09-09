package joao.atividade_progweb.controller;

import joao.atividade_progweb.dto.request.ReservaAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.ReservaAlocacaoResponseDTO;
import joao.atividade_progweb.entity.ReservaAlocacao;
import joao.atividade_progweb.service.ReservaAlocacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaAlocacaoController {

    private final ReservaAlocacaoService reservaService;

    public ReservaAlocacaoController(ReservaAlocacaoService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/listar")
    public List<ReservaAlocacao> listarTodas() {
        return reservaService.listarTodas();
    }

    @PostMapping("/criar")
    public ReservaAlocacaoResponseDTO criar(@Valid @RequestBody ReservaAlocacaoRequestDTO requestDTO) {
        return reservaService.salvar(requestDTO);
    }

    // Continua retornando DTO, pois é uma consulta mais específica
    @GetMapping("/usuario/{usuarioId}")
    public List<ReservaAlocacaoResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return reservaService.listarPorUsuario(usuarioId);
    }

    // Retorna a lista de entidades
    @GetMapping("/ambiente/{ambienteId}")
    public List<ReservaAlocacao> listarPorAmbiente(@PathVariable Integer ambienteId) {
        return reservaService.listarPorAmbiente(ambienteId);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ReservaAlocacaoResponseDTO> atualizar(@PathVariable int id, @Valid @RequestBody ReservaAlocacaoRequestDTO requestDTO) {
        ReservaAlocacaoResponseDTO dto = reservaService.atualizar(id, requestDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        if (reservaService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}