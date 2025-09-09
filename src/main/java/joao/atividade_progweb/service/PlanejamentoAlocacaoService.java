package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.PlanejamentoAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.PlanejamentoAlocacaoResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.entity.PlanejamentoAlocacao;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.repository.AmbienteRepository;
import joao.atividade_progweb.repository.PlanejamentoAlocacaoRepository;
import joao.atividade_progweb.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanejamentoAlocacaoService {

    private final PlanejamentoAlocacaoRepository planejamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;

    public PlanejamentoAlocacaoService(PlanejamentoAlocacaoRepository planejamentoRepository, UsuarioRepository usuarioRepository, AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.planejamentoRepository = planejamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public PlanejamentoAlocacaoResponseDTO salvar(PlanejamentoAlocacaoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente não encontrado!"));

        PlanejamentoAlocacao planejamento = modelMapper.map(requestDTO, PlanejamentoAlocacao.class);
        planejamento.setUsuario(usuario);
        planejamento.setAmbiente(ambiente);
        planejamento.setStatus(1);

        PlanejamentoAlocacao planejamentoSalvo = planejamentoRepository.save(planejamento);

        return convertToDto(planejamentoSalvo);
    }

    private PlanejamentoAlocacaoResponseDTO convertToDto(PlanejamentoAlocacao planejamento) {
        PlanejamentoAlocacaoResponseDTO dto = modelMapper.map(planejamento, PlanejamentoAlocacaoResponseDTO.class);
        dto.setNomeUsuario(planejamento.getUsuario().getNome());
        dto.setDescricaoAmbiente(planejamento.getAmbiente().getDescricao());
        return dto;
    }
}