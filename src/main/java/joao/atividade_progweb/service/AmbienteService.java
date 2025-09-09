package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.AmbienteRequestDTO;
import joao.atividade_progweb.dto.response.AmbienteResponseDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.repository.AmbienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmbienteService {

    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;

    public AmbienteService(AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<AmbienteResponseDTO> listarTodos() {
        List<Ambiente> ambientes = ambienteRepository.findAll();
        return ambientes.stream()
                .map(ambiente -> modelMapper.map(ambiente, AmbienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    // --- MÉTODO ATUALIZADO ---
    public AmbienteResponseDTO listarPorId(int id) {
        Ambiente ambiente = ambienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ambiente não encontrado com o ID: " + id));
        return modelMapper.map(ambiente, AmbienteResponseDTO.class);
    }

    public AmbienteResponseDTO salvar(AmbienteRequestDTO ambienteRequestDTO) {
        Ambiente ambiente = modelMapper.map(ambienteRequestDTO, Ambiente.class);
        ambiente.setStatus(1);
        Ambiente ambienteSalvo = ambienteRepository.save(ambiente);
        return modelMapper.map(ambienteSalvo, AmbienteResponseDTO.class);
    }

    public AmbienteResponseDTO atualizar(int id, AmbienteRequestDTO ambienteRequestDTO) {
        Ambiente ambienteExistente = ambienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente nao encontrado"));

        modelMapper.map(ambienteRequestDTO, ambienteExistente);
        ambienteExistente.setId(id);

        Ambiente ambienteSalvo = ambienteRepository.save(ambienteExistente);
        return modelMapper.map(ambienteSalvo, AmbienteResponseDTO.class);
    }

    public void deletar(int id) {
        if (!ambienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente nao encontrado");
        }
        ambienteRepository.deleteById(id);
    }
}