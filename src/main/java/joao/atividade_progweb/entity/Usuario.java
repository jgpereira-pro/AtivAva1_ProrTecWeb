package joao.atividade_progweb.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;

    @Column(name = "usuario_nome")
    private String nome;

    @Column(name = "usuario_matricula")
    private String matricula;

    @Column(name = "usuario_tipo")
    private String tipo;

    @Column(name = "usuario_status")
    private int status;

    @Column(name = "usuario_log_data_criacao")
    private LocalDateTime logDataCriacao;

    @Column(name = "usuario_lod_responsavel_id")
    private int lodResponsavelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getLogDataCriacao() {
        return logDataCriacao;
    }

    public void setLogDataCriacao(LocalDateTime logDataCriacao) {
        this.logDataCriacao = logDataCriacao;
    }

    public int getLodResponsavelId() {
        return lodResponsavelId;
    }

    public void setLodResponsavelId(int lodResponsavelId) {
        this.lodResponsavelId = lodResponsavelId;
    }
}
