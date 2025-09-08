package joao.atividade_progweb.controller;

import joao.atividade_progweb.dto.request.ReservaAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.ReservaAlocacaoResponseDTO;
import joao.atividade_progweb.entity.ReservaAlocacao;
import joao.atividade_progweb.service.ReservaAlocacaoService;
import org.springframework.web.bind.annotation.*;

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
    public ReservaAlocacaoResponseDTO criar(@RequestBody ReservaAlocacaoRequestDTO requestDTO) {
        return reservaService.salvar(requestDTO);
    }
}