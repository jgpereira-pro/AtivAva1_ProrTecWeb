package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SalaRequestDTO {

    @NotBlank(message = "O bloco не pode ser vazio.")
    private String bloco;

    @NotNull(message = "A capacidade não pode ser nula.")
    @Positive(message = "A capacidade deve ser um número positivo.")
    private Integer capacidade;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}