package joao.atividade_progweb.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaAlocacaoRequestDTO {
    private String justificativa;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Integer usuarioId;
    private Integer ambienteId;

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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