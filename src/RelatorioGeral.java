import java.beans.PropertyEditor;
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

    public String gerarRelatorioTodasTarefasProjeto(Projeto projeto, Pessoa solicitante) {
        String dados = "\nPessoa solicitante do relatório: " + solicitante.getNome();
        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "\nTarefas alocadas do projeto: " + projeto.getTitulo();

        for (TarefaAlocada t : projeto.getListaDeTarefasAlocadas()){
            dados += "\nNome da tarefa: " + t.getTarefa().getNome() + " Pessoa alocada: " + t.getPessoa().getNome();
        }

        return dados;
    }

    public String gerarRelatorioTodasPessoasProjeto(Projeto projeto, Pessoa solicitante) {
        String dados = "\nPessoa solicitante do relatório: " + solicitante.getNome();
        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "\nPessoas alocadas ao projeto: " + projeto.getTitulo();

        for (Pessoa p : projeto.getListaDePessoas()){
            dados += "\n" + p.getNome() + " " + p.getSobrenome();
        }

        return dados;
    }

    public String gerarRelatorioTodosRecursosProjeto(Projeto projeto, Pessoa solicitante) {
        String dados = "\nPessoa solicitante do relatório: " + solicitante.getNome();
        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "\nRecursos alocados ao projeto: " + projeto.getTitulo();

        for (Recurso r : projeto.getListaDeRecursos()){
            dados += "\n" + r.getNome() + " Preço: " + r.getValor();
        }

        return dados;
    }

    public String gerarRelatorioTodasTarefasFinalizadas(Projeto projeto, Pessoa solicitante) {
        String dados = "\nPessoa solicitante do relatório: " + solicitante.getNome();
        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "\nTarefas finalizadas do projeto: " + projeto.getTitulo();

        for (TarefaAlocada t : projeto.getListaDeTarefasAlocadas()){
            if(t.getTarefa().getStatus() == StatusTarefa.Encerrada){
                dados += "\n" + t.getTarefa().getNome();
            }
        }

        return dados;
    }

    public String gerarRelatorioTodasTarefasEmAndamento(Projeto projeto, Pessoa solicitante){
        String dados = "\nPessoa solicitante do relatório: " + solicitante.getNome();
        dados += "\nData da solicitação do relatório " + new Date().toString();
        dados += "\nTarefas em andamento/aberto do projeto: " + projeto.getTitulo();

        for (TarefaAlocada t : projeto.getListaDeTarefasAlocadas()){
            if(t.getTarefa().getStatus() == StatusTarefa.EmAberto){
                dados += "\n" + t.getTarefa().getNome();
            }
        }

        return dados;
    }

}
