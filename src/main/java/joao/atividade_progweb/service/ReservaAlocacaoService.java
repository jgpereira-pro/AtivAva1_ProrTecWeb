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
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ReservaAlocacaoResponseDTO salvar(ReservaAlocacaoRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(requestDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                .orElseThrow(() -> new RuntimeException("Ambiente não encontrado!"));

        ReservaAlocacao reserva = modelMapper.map(requestDTO, ReservaAlocacao.class);

        reserva.setUsuario(usuario);
        reserva.setAmbiente(ambiente);
        reserva.setStatus(1);

        ReservaAlocacao reservaSalva = reservaRepository.save(reserva);

        ReservaAlocacaoResponseDTO responseDTO = modelMapper.map(reservaSalva, ReservaAlocacaoResponseDTO.class);
        responseDTO.setNomeUsuario(reservaSalva.getUsuario().getNome());

        return responseDTO;
    }
}