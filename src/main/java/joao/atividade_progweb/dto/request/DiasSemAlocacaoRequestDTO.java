package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiasSemAlocacaoRequestDTO {

    @NotNull(message = "A data não pode ser nula.")
    private LocalDate data;

    private Integer diaSemana;
    private Integer ambienteId;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Integer getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Integer ambienteId) {
        this.ambienteId = ambienteId;
    }
}