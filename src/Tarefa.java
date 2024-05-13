import java.util.Date;

public class Tarefa {

    private String nome;
    private Date prazo;
    private Prioridade prioridade;
    private StatusTarefa status;

    public String getNome() {
        return nome;
    }

    public Date getPrazo() {
        return prazo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    Tarefa(String nome, Date prazo, Prioridade prioridade) {
        this.nome = nome;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.status = StatusTarefa.EmAberto;
    }

}
