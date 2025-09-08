package joao.atividade_progweb.repository;

import joao.atividade_progweb.entity.ReservaAlocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaAlocacaoRepository extends JpaRepository<ReservaAlocacao, Integer> {
}