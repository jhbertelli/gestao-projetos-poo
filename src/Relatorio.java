import java.sql.Date;

public abstract class Relatorio {

    protected String dataSolicitacao;
    protected Pessoa solicitante;

    Relatorio(String dataSolicitacao, Pessoa pessoaSolicitante) {
        this.dataSolicitacao = dataSolicitacao;
        this.solicitante = pessoaSolicitante;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }



}
