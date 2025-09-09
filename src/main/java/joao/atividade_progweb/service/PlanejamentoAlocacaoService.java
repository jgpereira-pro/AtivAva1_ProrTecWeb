package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.PlanejamentoAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.PlanejamentoAlocacaoResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.entity.DiasSemAlocacao;
import joao.atividade_progweb.entity.PlanejamentoAlocacao;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.repository.AmbienteRepository;
import joao.atividade_progweb.repository.DiasSemAlocacaoRepository;
import joao.atividade_progweb.repository.PlanejamentoAlocacaoRepository;
import joao.atividade_progweb.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanejamentoAlocacaoService {

    private final PlanejamentoAlocacaoRepository planejamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;
    private DiasSemAlocacaoRepository diasSemAlocacaoRepository;

    public PlanejamentoAlocacaoService(PlanejamentoAlocacaoRepository planejamentoRepository, UsuarioRepository usuarioRepository, AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.planejamentoRepository = planejamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public PlanejamentoAlocacaoResponseDTO salvar(PlanejamentoAlocacaoRequestDTO requestDTO) {
        verificarSeDiaEstaBloqueado(requestDTO.getAmbienteId(), requestDTO.getData());

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

    public List<PlanejamentoAlocacao> listarTodos() {
        return planejamentoRepository.findAll();
    }

    public PlanejamentoAlocacaoResponseDTO listarPorId(int id) {
        return planejamentoRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public PlanejamentoAlocacaoResponseDTO atualizar(int id, PlanejamentoAlocacaoRequestDTO requestDTO) {
        verificarSeDiaEstaBloqueado(requestDTO.getAmbienteId(), requestDTO.getData());

        return planejamentoRepository.findById(id).map(planejamentoExistente -> {
            Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
            Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente não encontrado!"));

            modelMapper.map(requestDTO, planejamentoExistente);
            planejamentoExistente.setUsuario(usuario);
            planejamentoExistente.setAmbiente(ambiente);

            PlanejamentoAlocacao planejamentoSalvo = planejamentoRepository.save(planejamentoExistente);
            return convertToDto(planejamentoSalvo);
        }).orElse(null);
    }

    private void verificarSeDiaEstaBloqueado(Integer ambienteId, LocalDate data) {
        List<DiasSemAlocacao> bloqueios = diasSemAlocacaoRepository.findByAmbienteIdAndData(ambienteId, data);
        if (!bloqueios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O ambiente está bloqueado para planejamento nesta data.");
        }
    }

    public boolean deletar(int id) {
        if (planejamentoRepository.existsById(id)) {
            planejamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PlanejamentoAlocacaoResponseDTO convertToDto(PlanejamentoAlocacao planejamento) {
        PlanejamentoAlocacaoResponseDTO dto = modelMapper.map(planejamento, PlanejamentoAlocacaoResponseDTO.class);
        dto.setNomeUsuario(planejamento.getUsuario().getNome());
        return dto;
    }
}