package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.RegistroEntradaRequestDTO;
import joao.atividade_progweb.dto.request.RegistroSaidaRequestDTO;
import joao.atividade_progweb.dto.response.RegistroUtilizacaoResponseDTO;
import joao.atividade_progweb.service.RegistroUtilizacaoAmbienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros")
public class RegistroUtilizacaoAmbienteController {

    private final RegistroUtilizacaoAmbienteService registroService;

    public RegistroUtilizacaoAmbienteController(RegistroUtilizacaoAmbienteService registroService) {
        this.registroService = registroService;
    }

    @PostMapping("/entrada")
    public RegistroUtilizacaoResponseDTO registrarEntrada(@Valid @RequestBody RegistroEntradaRequestDTO requestDTO) {
        return registroService.registrarEntrada(requestDTO);
    }

    @PatchMapping("/saida/{id}")
    public ResponseEntity<RegistroUtilizacaoResponseDTO> registrarSaida(@PathVariable int id, @RequestBody RegistroSaidaRequestDTO requestDTO) {
        RegistroUtilizacaoResponseDTO dto = registroService.registrarSaida(id, requestDTO);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }
}