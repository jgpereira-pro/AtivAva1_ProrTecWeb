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

@Service
public class AmbienteService {

    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;

    public AmbienteService(AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public List<Ambiente> listarTodos() {
        return ambienteRepository.findAll();
    }

    public Ambiente listarPorId(int id) {
        return ambienteRepository.findById(id).orElse(null);
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