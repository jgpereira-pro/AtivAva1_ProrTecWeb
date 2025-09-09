package joao.atividade_progweb.dto.response;

import java.time.LocalDateTime;

public class RegistroUtilizacaoResponseDTO {
    private Integer id;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private String observacao;
    private String descricaoAmbiente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDescricaoAmbiente() {
        return descricaoAmbiente;
    }

    public void setDescricaoAmbiente(String descricaoAmbiente) {
        this.descricaoAmbiente = descricaoAmbiente;
    }
}