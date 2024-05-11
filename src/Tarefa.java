import java.sql.Date;

public class Tarefa {

    private String nome;
    private String prazo;
    private Prioridade prioridade;
    private StatusTarefa status;

    public String getNome() {
        return nome;
    }

    public String getPrazo() {
        return prazo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    Tarefa(String nome, String prazo, Prioridade prioridade) {
        this.nome = nome;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.status = StatusTarefa.EmAberto;
    }

}
