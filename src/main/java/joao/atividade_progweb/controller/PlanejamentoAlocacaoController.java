package joao.atividade_progweb.controller;

import jakarta.validation.Valid;
import joao.atividade_progweb.dto.request.PlanejamentoAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.PlanejamentoAlocacaoResponseDTO;
import joao.atividade_progweb.service.PlanejamentoAlocacaoService;
import org.springframework.web.bind.annotation.*;

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
}