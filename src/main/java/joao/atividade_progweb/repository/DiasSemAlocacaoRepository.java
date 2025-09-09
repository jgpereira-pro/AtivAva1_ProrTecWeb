package joao.atividade_progweb.repository;

import joao.atividade_progweb.entity.DiasSemAlocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiasSemAlocacaoRepository extends JpaRepository<DiasSemAlocacao, Integer> {

    List<DiasSemAlocacao> findByAmbienteIdAndData(Integer ambienteId, LocalDate data);
}