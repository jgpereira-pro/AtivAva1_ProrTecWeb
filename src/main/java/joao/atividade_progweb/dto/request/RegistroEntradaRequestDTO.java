package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.NotNull;

public class RegistroEntradaRequestDTO {

    @NotNull(message = "O ID do ambiente é obrigatório.")
    private Integer ambienteId;
    private Integer planejamentoId;
    private String observacao;

    public Integer getPlanejamentoId() {
        return planejamentoId;
    }

    public void setPlanejamentoId(Integer planejamentoId) {
        this.planejamentoId = planejamentoId;
    }

    public Integer getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Integer ambienteId) {
        this.ambienteId = ambienteId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}