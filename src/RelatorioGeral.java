import java.util.Date;

public class RelatorioGeral extends Relatorio {

    RelatorioGeral(String dataSolicitacao, Pessoa pessoa) {
        super(dataSolicitacao, pessoa);
    }


    public String gerarRelatorioDeTarefasAlocadas(Projeto projeto, Pessoa solicitante) {
        String dados = "\nPessoa Solicitante do relatório: " + solicitante.getNome();

        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "Tarefas alocadas do projeto " + projeto.getTitulo();

        for (TarefaAlocada t : projeto.getListaDeTarefasAlocadas()) {
            dados += "\nNome da tarefa " + t.getTarefa().getNome();
            dados += "\nPessoa alocada para a tarefa " + t.getPessoa().getNome();
        }

        return dados;
    }

    public String gerarRelatorioDadosGerais(Projeto projeto, Pessoa solicitante) {
        String dados = String.format(
            "Pessoa solicitante do relatório: %s\nData de solicitação do relatório: %s\nDados do projeto: %s\nData inicial: %s\nData final: %s\nCliente: %s\nStatus do projeto: %s",
            solicitante.getNome(),
            new Date().toString(),
            projeto.getTitulo(),
            projeto.getDataInicial(),
            projeto.getDataFinal(),
            projeto.getCliente(),
            projeto.getStatus()
        );

        return dados;
    }
}
