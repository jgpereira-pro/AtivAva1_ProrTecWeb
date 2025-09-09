package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.RegistroEntradaRequestDTO;
import joao.atividade_progweb.dto.request.RegistroSaidaRequestDTO;
import joao.atividade_progweb.dto.response.RegistroUtilizacaoResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.entity.PlanejamentoAlocacao;
import joao.atividade_progweb.entity.RegistroUtilizacaoAmbiente;
import joao.atividade_progweb.repository.AmbienteRepository;
import joao.atividade_progweb.repository.PlanejamentoAlocacaoRepository;
import joao.atividade_progweb.repository.RegistroUtilizacaoAmbienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class RegistroUtilizacaoAmbienteService {

    private final RegistroUtilizacaoAmbienteRepository registroRepository;
    private final AmbienteRepository ambienteRepository;
    private final PlanejamentoAlocacaoRepository planejamentoRepository;
    private final ModelMapper modelMapper;

    public RegistroUtilizacaoAmbienteService(RegistroUtilizacaoAmbienteRepository registroRepository, AmbienteRepository ambienteRepository, PlanejamentoAlocacaoRepository planejamentoRepository, ModelMapper modelMapper) {
        this.registroRepository = registroRepository;
        this.ambienteRepository = ambienteRepository;
        this.planejamentoRepository = planejamentoRepository;
        this.modelMapper = modelMapper;
    }

    public RegistroUtilizacaoResponseDTO registrarEntrada(RegistroEntradaRequestDTO requestDTO) {
        Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente não encontrado!"));

        RegistroUtilizacaoAmbiente novoRegistro = new RegistroUtilizacaoAmbiente();
        novoRegistro.setAmbiente(ambiente);
        novoRegistro.setHoraEntrada(LocalDateTime.now());
        novoRegistro.setObservacao(requestDTO.getObservacao());

        if (requestDTO.getPlanejamentoId() != null) {
            PlanejamentoAlocacao planejamento = planejamentoRepository.findById(requestDTO.getPlanejamentoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Planejamento não encontrado!"));
            novoRegistro.setPlanejamentoAlocacao(planejamento);
        }

        RegistroUtilizacaoAmbiente registroSalvo = registroRepository.save(novoRegistro);
        return convertToDto(registroSalvo);
    }

    public RegistroUtilizacaoResponseDTO registrarSaida(int id, RegistroSaidaRequestDTO requestDTO) {
        return registroRepository.findById(id).map(registroExistente -> {
            registroExistente.setHoraSaida(LocalDateTime.now());
            registroExistente.setObservacao(requestDTO.getObservacao());
            RegistroUtilizacaoAmbiente registroSalvo = registroRepository.save(registroExistente);
            return convertToDto(registroSalvo);
        }).orElse(null);
    }

    private RegistroUtilizacaoResponseDTO convertToDto(RegistroUtilizacaoAmbiente registro) {
        RegistroUtilizacaoResponseDTO dto = modelMapper.map(registro, RegistroUtilizacaoResponseDTO.class);
        dto.setDescricaoAmbiente(registro.getAmbiente().getDescricao());
        return dto;
    }
}