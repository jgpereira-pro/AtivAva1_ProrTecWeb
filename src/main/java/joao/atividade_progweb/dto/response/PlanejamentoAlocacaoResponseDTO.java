package joao.atividade_progweb.dto.response;

import java.time.LocalDate;

public class PlanejamentoAlocacaoResponseDTO {
    private Integer id;
    private String observacao;
    private LocalDate data;
    private String nomeUsuario;
    private String descricaoAmbiente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricaoAmbiente() {
        return descricaoAmbiente;
    }

    public void setDescricaoAmbiente(String descricaoAmbiente) {
        this.descricaoAmbiente = descricaoAmbiente;
    }
}