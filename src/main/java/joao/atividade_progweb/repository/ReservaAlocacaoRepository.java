package joao.atividade_progweb.repository;

import joao.atividade_progweb.entity.ReservaAlocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaAlocacaoRepository extends JpaRepository<ReservaAlocacao, Integer> {

    @Query("SELECT r FROM ReservaAlocacao r WHERE r.id <> :reservaId " +
            "AND r.ambiente.id = :ambienteId " +
            "AND r.data = :data " +
            "AND (r.horaInicio < :horaFim AND r.horaFim > :horaInicio)")
    List<ReservaAlocacao> findReservasConflitantes(
            @Param("ambienteId") Integer ambienteId,
            @Param("data") LocalDate data,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFim") LocalTime horaFim,
            @Param("reservaId") Integer reservaId);

    List<ReservaAlocacao> findByUsuarioId(Integer usuarioId);

    List<ReservaAlocacao> findByAmbienteId(Integer ambienteId);
}