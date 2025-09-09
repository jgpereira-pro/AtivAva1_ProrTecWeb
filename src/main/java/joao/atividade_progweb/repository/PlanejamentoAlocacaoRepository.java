package joao.atividade_progweb.repository;

import joao.atividade_progweb.entity.PlanejamentoAlocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanejamentoAlocacaoRepository extends JpaRepository<PlanejamentoAlocacao, Integer> {
}