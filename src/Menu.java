import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Menu {
    public static void exibirMenu() {
        int resposta;

        String[] lista = {
            "Criar projeto",
            "Adicionar tarefa",
            "Adicionar pessoa ao projeto",
            "Alocar tarefa",
            "Alocar recurso",
            "Gerar relatório",
            "Alterar data final de um projeto",
            "Alterar status de um projeto",
            "Alterar status de uma tarefa",
            "Remover recurso",
            "Sair"
        };

        JComboBox opcoes = new JComboBox(lista);

        do {
            JOptionPane.showMessageDialog(null, opcoes, "Opções", JOptionPane.INFORMATION_MESSAGE, null);
            resposta = opcoes.getSelectedIndex();

            switch (resposta) { //Adicionando um novo projeto à lista de projetos da classe GestaoProjetos
                case 0:
                    criarProjeto();
                    break;
                case 1: //Adicionando uma tarefa a um projeto já adicionado à lista de projetos da classe GestaoProjetos
                    adicionarTarefa();
                    break;
                case 2: //Adicionando uma pessoa a um projeto
                    adicionarPessoa();
                    break;
                case 3: //Alocando uma tarefa para uma pessoa na lista de tarefas alocadas de um projeto
                    alocarTarefa();
                    break;
                case 4: //Adicionando um recurso a um projeto já adicionado à lista de projetos da classe GestaoProjetos
                    adicionarRecurso();
                    break;
                case 5:
                    gerarRelatorio();
                    break;
                case 6:
                    alterarDataFinalProjeto();
                    break;
                case 7:
                    alterarStatusProjeto();
                    break;
                case 8:
                    alterarStatusTarefa();
                    break;
                case 9:
                    removerRecurso();
                    break;
            }

        } while (resposta != 10);
    }

    private static void gerarRelatorio() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder gerar relatórios.");
            //criarProjeto();
            return;
        }

        JPanel panel = new JPanel(new GridLayout(1, 2));

        panel.add(new JLabel("Informe o tipo de relatório desejado:"));
        String[] lista = { "1 - Dados gerais do projeto", "2 - Tarefas alocadas de um projeto", "2" +
                "3 - Relatório de todas as tarefas de um projeto",
                "4 - Relatório de todas as pessoas de um projeto", "4 - Relatório de todos os recursos de um projeto",
                "6 -  Relatório de todas as tarefas 'ENCERRADAS' de um projeto", "7 - Gerar relatório de todas as tarefas 'EM ANDAMENTO' de um projeto" };
        JComboBox opcoes = new JComboBox(lista);
        panel.add(opcoes);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) return;

        int tipo = opcoes.getSelectedIndex();

        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());

        Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);

        if (projetoEscolhido.retornarListaPessoas() == null) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhuma pessoa encontrada no projeto selecionado.\n" +
                "Adicione uma pessoa no projeto para poder gerar relatórios."
            );

            return;
        }

        int posicaoSolicitante = EntradaSaidaDados.escolherSolicitante(projetoEscolhido.retornarListaPessoas());
        Pessoa solicitante = projetoEscolhido.retornarPessoa(posicaoSolicitante);

        String data = LocalDate.now().toString();
        RelatorioGeral relatorio = new RelatorioGeral(data, solicitante);

        switch (tipo) {
            case 0:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDadosGerais(projetoEscolhido, solicitante));
                break;
            case 1:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasAlocadas(projetoEscolhido, solicitante));
                break;
            case 2:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioTodasTarefasProjeto(projetoEscolhido, solicitante));
                break;
            case 3:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioTodasPessoasProjeto(projetoEscolhido, solicitante));
                break;
            case 4:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioTodosRecursosProjeto(projetoEscolhido, solicitante));
                break;
            case 5:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioTodasTarefasFinalizadas(projetoEscolhido, solicitante));
                break;
            case 6:
                EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioTodasTarefasEmAndamento(projetoEscolhido, solicitante));
                break;
        }
    }

    private static void adicionarRecurso() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder alocar um recurso nele.");
//			criarProjeto();

            return;
        }

        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
        Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
        String nome = EntradaSaidaDados.retornarTexto("Informe o nome do recurso");
        double valor = EntradaSaidaDados.retornarReal("Informe o preço do recurso");
        Recurso recurso = new Recurso(nome, valor);
        projetoEscolhido.adicionarRecurso(recurso);
    }

    private static void alocarTarefa() {
        Pessoa pessoaEscolhida;
        Tarefa tarefaEscolhida;
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum projeto encontrado. Adicione um projeto para poder alocar uma tarefa nele."
            );
//			criarProjeto();

            return;
        }

        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
        Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
        if (projetoEscolhido.retornarListaTarefas() == null) {
            EntradaSaidaDados.mostrarMensagem("Adicione tarefas ao projeto");
            adicionarTarefa(projetoEscolhido);
            tarefaEscolhida = projetoEscolhido.retornarTarefa(0);
        } else {
            int posicaoTarefa = EntradaSaidaDados.escolherTarefa(projetoEscolhido.retornarListaTarefas());
            tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
        }

        if (projetoEscolhido.retornarListaPessoas() == null) {
            EntradaSaidaDados.mostrarMensagem("Adicione pessoas ao projeto");
            adicionarPessoa(projetoEscolhido);
            pessoaEscolhida = projetoEscolhido.retornarPessoa(0);
        } else {
            int posicaoPessoa = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas());
            pessoaEscolhida = projetoEscolhido.retornarPessoa(posicaoPessoa);
        }

        projetoEscolhido.alocarTarefa(pessoaEscolhida, tarefaEscolhida);
        String pessoaCargo = "Pessoa alocada: " + pessoaEscolhida.getNome() + "\nCargo da pessoa alocada: " + pessoaEscolhida.getCargo().getNome();
        EntradaSaidaDados.mostrarMensagem(pessoaCargo);
    }

    private static void adicionarPessoa() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder adicionar uma pessoa nele.");
//			criarProjeto();
            return;
        }

        String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa");
        String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa");
        Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto"));
        Pessoa p = new Pessoa(nome, sobrenome, cargo);
        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
        Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
        projetoEscolhido.adicionarPessoa(p);
    }

    private static void adicionarPessoa(Projeto projeto) {
        String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa");
        String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa");
        Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto"));
        Pessoa p = new Pessoa(nome, sobrenome, cargo);
        projeto.adicionarPessoa(p);
    }

    private static void adicionarTarefa() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder adicionar uma tarefa nele.");
//			criarProjeto();
            return;
        }

        String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa");
        Date prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa");
        var prioridade = EntradaSaidaDados.escolherPrioridade();
        Tarefa t = new Tarefa(nome, prazo, prioridade);
        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
        Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
        projetoEscolhido.adicionarTarefa(t);
    }

    private static void adicionarTarefa(Projeto projeto) {
        String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa");
        Date prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa");
        var prioridade = EntradaSaidaDados.escolherPrioridade();
        Tarefa t = new Tarefa(nome, prazo, prioridade);
        projeto.adicionarTarefa(t);
    }

    private static void alterarStatusTarefa() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum projeto encontrado. Adicione um projeto para poder alterar suas tarefas."
            );

            return;
        }

        var projeto = selecionarProjeto();
        var listaTarefas = projeto.retornarListaTarefas();

        if (listaTarefas == null) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhuma tarefa encontrada neste projeto. Adicione tarefas para poder alterá-las."
            );

            return;
        }

        int posicaoTarefa = EntradaSaidaDados.escolherTarefa(listaTarefas);
        var tarefa = projeto.retornarTarefa(posicaoTarefa);

        String statusAntigo = tarefa.getStatus().toString();
        EntradaSaidaDados.mostrarMensagem("Status atual da tarefa escolhida: " + statusAntigo);

        var statusNovo = EntradaSaidaDados.escolherStatusTarefa();

        if (statusNovo == null) {
            EntradaSaidaDados.mostrarMensagem("Operação cancelada.");
            return;
        }

        tarefa.setStatus(statusNovo);

        EntradaSaidaDados.mostrarMensagem(
            String.format(
                "Status alterado com sucesso de: %s para %s.",
                statusAntigo,
                statusNovo
            )
        );
    }

    private static void alterarStatusProjeto() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum projeto encontrado. Adicione um projeto para poder alterar seu status."
            );

            return;
        }

        var projeto = selecionarProjeto();

        String statusAntigo = projeto.getStatus().toString();
        EntradaSaidaDados.mostrarMensagem("Status atual do projeto escolhido: " + statusAntigo);

        var statusNovo = EntradaSaidaDados.escolherStatusProjeto();

        if (statusNovo == null) {
            EntradaSaidaDados.mostrarMensagem("Operação cancelada.");
            return;
        }

        projeto.setStatus(statusNovo);

        EntradaSaidaDados.mostrarMensagem(
            String.format(
                "Status alterado com sucesso de: %s para %s.",
                statusAntigo,
                statusNovo
            )
        );
    }

    private static void removerRecurso() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum projeto encontrado. Adicione um projeto para poder remover recursos."
            );

            return;
        }

        Projeto projeto = selecionarProjeto();
        List<Recurso> listaRecursos = projeto.getListaDeRecursos();

        if (listaRecursos.isEmpty()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum recurso encontrado. Adicione um recurso no projeto selecionado para poder remover recursos."
            );

            return;
        }

        Recurso recurso = EntradaSaidaDados.escolherRecurso(listaRecursos);

        if (recurso == null) {
            EntradaSaidaDados.mostrarMensagem("Operação cancelada.");
            return;
        }

        listaRecursos.remove(recurso);
    }

    private static void criarProjeto() {
        String titulo = EntradaSaidaDados.retornarTexto("Informe o título do projeto");
        String cliente = EntradaSaidaDados.retornarTexto("Informe o cliente do projeto");
        var dataInicial = EntradaSaidaDados.retornarData("Informe a data inicial do projeto");
        var dataFinal = EntradaSaidaDados.retornarData("Informe a data final do projeto");

        Projeto p = new Projeto(titulo, cliente, dataInicial, dataFinal);
        GestaoProjetos.adicionarProjeto(p);
    }

    private static void alterarDataFinalProjeto() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem(
                "Nenhum projeto encontrado. Adicione um projeto para poder alterar seu status."
            );

            return;
        }

        var projeto = selecionarProjeto();

        Date dataFinalNova = EntradaSaidaDados.retornarData(
            "Insira a nova data final do projeto.\nData final anterior: " + formatarDataParaString(projeto.getDataFinal())
        );

        if (dataFinalNova == null) {
            EntradaSaidaDados.mostrarMensagem("Operação cancelada.");
            return;
        }

        projeto.setDataFinal(dataFinalNova);

        EntradaSaidaDados.mostrarMensagem(
            String.format(
                "Data alterada com sucesso de: %s para %s.",
                formatarDataParaString(projeto.getDataFinal()),
                formatarDataParaString(dataFinalNova)
            )
        );
    }

    private static boolean getHasProjects() {
        return GestaoProjetos.retornarListaProjetos().getItemCount() > 0;
    }

    private static Projeto selecionarProjeto() {
        int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
        return GestaoProjetos.retornarProjeto(posicaoProjeto);
    }

    private static String formatarDataParaString(Date data) {
        //DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        var formatadorData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatadorData.format(data);

        System.out.println(dataFormatada);

        return dataFormatada;
    }
}

