package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.ReservaAlocacaoRequestDTO;
import joao.atividade_progweb.dto.response.ReservaAlocacaoResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.entity.ReservaAlocacao;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.repository.AmbienteRepository;
import joao.atividade_progweb.repository.ReservaAlocacaoRepository;
import joao.atividade_progweb.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaAlocacaoService {

    private final ReservaAlocacaoRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;

    public ReservaAlocacaoService(ReservaAlocacaoRepository reservaRepository, UsuarioRepository usuarioRepository, AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReservaAlocacao> listarTodas() {
        return reservaRepository.findAll();
    }

    public List<ReservaAlocacaoResponseDTO> listarPorUsuario(Integer usuarioId) {
        List<ReservaAlocacao> reservas = reservaRepository.findByUsuarioId(usuarioId);
        return reservas.stream()
                .map(reserva -> {
                    ReservaAlocacaoResponseDTO dto = modelMapper.map(reserva, ReservaAlocacaoResponseDTO.class);
                    dto.setNomeUsuario(reserva.getUsuario().getNome());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<ReservaAlocacao> listarPorAmbiente(Integer ambienteId) {
        return reservaRepository.findByAmbienteId(ambienteId);
    }

    public ReservaAlocacaoResponseDTO salvar(ReservaAlocacaoRequestDTO requestDTO) {
        validarConflito(requestDTO, 0);

        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente não encontrado!"));

        ReservaAlocacao reserva = modelMapper.map(requestDTO, ReservaAlocacao.class);
        reserva.setUsuario(usuario);
        reserva.setAmbiente(ambiente);
        reserva.setStatus(1);
        ReservaAlocacao reservaSalva = reservaRepository.save(reserva);
        ReservaAlocacaoResponseDTO responseDTO = modelMapper.map(reservaSalva, ReservaAlocacaoResponseDTO.class);
        responseDTO.setNomeUsuario(reservaSalva.getUsuario().getNome());
        return responseDTO;
    }

    public ReservaAlocacaoResponseDTO atualizar(int id, ReservaAlocacaoRequestDTO requestDTO) {
        validarConflito(requestDTO, id);

        ReservaAlocacao reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));

        modelMapper.map(requestDTO, reservaExistente);
        reservaExistente.setId(id);
        ReservaAlocacao reservaSalva = reservaRepository.save(reservaExistente);
        ReservaAlocacaoResponseDTO responseDTO = modelMapper.map(reservaSalva, ReservaAlocacaoResponseDTO.class);
        responseDTO.setNomeUsuario(reservaSalva.getUsuario().getNome());
        return responseDTO;
    }

    private void validarConflito(ReservaAlocacaoRequestDTO requestDTO, int idDaReservaParaIgnorar) {
        List<ReservaAlocacao> conflitos = reservaRepository.findReservasConflitantes(
                requestDTO.getAmbienteId(),
                requestDTO.getData(),
                requestDTO.getHoraInicio(),
                requestDTO.getHoraFim(),
                idDaReservaParaIgnorar
        );

        if (!conflitos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O ambiente já está reservado neste horário.");
        }
    }

    public void deletar(int id) {
        if (!reservaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao encontrada");
        }
        reservaRepository.deleteById(id);
    }


}