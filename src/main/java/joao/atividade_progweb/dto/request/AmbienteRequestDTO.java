package joao.atividade_progweb.dto.request;

public class AmbienteRequestDTO {
    private String descricao;
    private int andar;
    private String tipo;
    private int numeroPcs;
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
