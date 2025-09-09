package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AmbienteRequestDTO {

    @NotBlank(message = "A descrição não pode ser vazia.")
    private String descricao;

    @NotNull(message = "O andar não pode ser nulo.")
    private int andar;

    @NotBlank(message = "O tipo não pode ser vazio.")
    private String tipo;

    private int numeroPcs;

    @Positive(message = "A capacidade deve ser um número positivo.")
    private int capacidade;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroPcs() {
        return numeroPcs;
    }

    public void setNumeroPcs(int numeroPcs) {
        this.numeroPcs = numeroPcs;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
