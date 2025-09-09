package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlanejamentoAlocacaoRequestDTO {

    @NotBlank(message = "A observação не pode ser vazia.")
    private String observacao;

    @NotNull(message = "A data não pode ser nula.")
    @FutureOrPresent(message = "A data do planejamento não pode ser no passado.")
    private LocalDate data;

    @NotNull(message = "A hora de início não pode ser nula.")
    private LocalTime horaInicio;

    @NotNull(message = "A hora de fim não pode ser nula.")
    private LocalTime horaFim;

    @NotNull(message = "O ID do usuário é obrigatório.")
    private Integer usuarioId;

    @NotNull(message = "O ID do ambiente é obrigatório.")
    private Integer ambienteId;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Integer ambienteId) {
        this.ambienteId = ambienteId;
    }
}