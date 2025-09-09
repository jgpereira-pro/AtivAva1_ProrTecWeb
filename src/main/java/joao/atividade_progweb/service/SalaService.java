package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.SalaRequestDTO;
import joao.atividade_progweb.entity.Sala;
import joao.atividade_progweb.repository.SalaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final ModelMapper modelMapper;

    public SalaService(SalaRepository salaRepository, ModelMapper modelMapper) {
        this.salaRepository = salaRepository;
        this.modelMapper = modelMapper;
    }

    public Sala salvar(SalaRequestDTO salaRequestDTO) {
        Sala sala = modelMapper.map(salaRequestDTO, Sala.class);
        return salaRepository.save(sala);
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    public Sala listarPorId(Long id) {
        return salaRepository.findById(id).orElse(null);
    }

    public Sala atualizar(Long id, SalaRequestDTO salaRequestDTO) {
        return salaRepository.findById(id).map(salaExistente -> {
            modelMapper.map(salaRequestDTO, salaExistente);
            return salaRepository.save(salaExistente);
        }).orElse(null);
    }

    public boolean deletar(Long id) {
        if (salaRepository.existsById(id)) {
            salaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}