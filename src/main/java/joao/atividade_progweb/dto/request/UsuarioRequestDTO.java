package joao.atividade_progweb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotBlank(message = "O nome do usuário não pode ser vazio.")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    private String nome;

    @NotBlank(message = "A matrícula não pode ser vazia.")
    private String matricula;

    @NotBlank(message = "O tipo do usuário não pode ser vazio.")
    private String tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
