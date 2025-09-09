package joao.atividade_progweb.repository;

import joao.atividade_progweb.entity.RegistroUtilizacaoAmbiente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroUtilizacaoAmbienteRepository extends JpaRepository<RegistroUtilizacaoAmbiente, Integer> {
}