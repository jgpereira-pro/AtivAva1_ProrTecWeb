package joao.atividade_progweb.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_utilizacao_ambiente")
public class RegistroUtilizacaoAmbiente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registro_utilizacao_ambiente_id")
    private Integer id;

    @Column(name = "registro_utilizacao_ambiente_hora_entrada")
    private LocalDateTime horaEntrada;

    @Column(name = "registro_utilizacao_ambiente_hora_saida")
    private LocalDateTime horaSaida;

    @Column(name = "registro_utilizacao_ambiente_observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "planejamento_alocacao_id")
    private PlanejamentoAlocacao planejamentoAlocacao;

    @ManyToOne
    @JoinColumn(name = "ambiente_id", nullable = false)
    private Ambiente ambiente;

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

    public PlanejamentoAlocacao getPlanejamentoAlocacao() {
        return planejamentoAlocacao;
    }

    public void setPlanejamentoAlocacao(PlanejamentoAlocacao planejamentoAlocacao) {
        this.planejamentoAlocacao = planejamentoAlocacao;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
}