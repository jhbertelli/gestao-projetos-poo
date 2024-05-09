import java.sql.Date;

public class RelatorioGeral extends Relatorio {

    RelatorioGeral(String dataSolicitacao, Pessoa pessoa) {
        super(dataSolicitacao, pessoa);
    }


    public String gerarRelatorioDeTarefasAlocadas(Projeto projeto) {
        String dados = "Tarefas alocadas do projeto " + projeto.getTitulo();
        for (TarefaAlocada t : projeto.getListaDeTarefasAlocadas()) {
            dados += "\nNome da tarefa " + t.getTarefa().getNome();
            dados += "\nPessoa alocada para a tarefa " + t.getPessoa().getNome();
        }

        return dados;
    }

    public String gerarRelatorioDadosGerais(Projeto projeto) {
        String dados = String.format(
            "Dados do projeto: %s\nData inicial: %s\nCliente: %s\nStatus do projeto: %d",
            projeto.getTitulo(),
            projeto.getDataInicial(),
            projeto.getDataFinal(),
            projeto.getCliente(),
            projeto.getStatus()
        );

        return dados;
    }
}
