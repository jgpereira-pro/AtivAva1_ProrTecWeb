package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.request.DiasSemAlocacaoRequestDTO;
import joao.atividade_progweb.entity.Ambiente;
import joao.atividade_progweb.entity.DiasSemAlocacao;
import joao.atividade_progweb.repository.AmbienteRepository;
import joao.atividade_progweb.repository.DiasSemAlocacaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DiasSemAlocacaoService {

    private final DiasSemAlocacaoRepository diasSemAlocacaoRepository;
    private final AmbienteRepository ambienteRepository;
    private final ModelMapper modelMapper;


    public DiasSemAlocacaoService(DiasSemAlocacaoRepository diasSemAlocacaoRepository, AmbienteRepository ambienteRepository, ModelMapper modelMapper) {
        this.diasSemAlocacaoRepository = diasSemAlocacaoRepository;
        this.ambienteRepository = ambienteRepository;
        this.modelMapper = modelMapper;
    }

    public DiasSemAlocacao salvar(DiasSemAlocacaoRequestDTO requestDTO) {
        DiasSemAlocacao bloqueio = modelMapper.map(requestDTO, DiasSemAlocacao.class);

        if (requestDTO.getAmbienteId() != null) {
            Ambiente ambiente = ambienteRepository.findById(requestDTO.getAmbienteId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ambiente não encontrado!"));
            bloqueio.setAmbiente(ambiente);
        }

        bloqueio.setStatus(1);
        return diasSemAlocacaoRepository.save(bloqueio);
    }

    public List<DiasSemAlocacao> listarTodos() {
        return diasSemAlocacaoRepository.findAll();
    }

    public boolean deletar(int id) {
        if (diasSemAlocacaoRepository.existsById(id)) {
            diasSemAlocacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}