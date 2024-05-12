import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;

import javax.swing.*;

public class Menu {
    public static void exibirMenu() {

        int resposta;
        String[] lista = {"Criar projeto", "Adicionar tarefa", "Adicionar pessoa ao projeto", "Alocar tarefa", "Alocar recurso", "Gerar relatório", "Sair"};
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
            }

        } while (resposta != 6);


    }

    private static void gerarRelatorio() {
        if (getHasProjects()) {
            int tipo = EntradaSaidaDados.retornarInteiro("Informe o tipo de relatório desejado:" + "\n 1 - Dados gerais do projeto" + "\n 2 - Tarefas alocadas de um projeto");

            int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());

            Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);

            if (projetoEscolhido.retornarListaPessoas() == null) {
                EntradaSaidaDados.mostrarMensagem(
                    "Nenhuma pessoa encontrada no projeto selecionado.\n" +
                    "Adicione uma pessoa no projeto para poder gerar relatórios."
                );

                return;
            }

            int posicaoSolicitante = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas());
            Pessoa solicitante = projetoEscolhido.retornarPessoa(posicaoSolicitante);

            int pessoaSolicitante = EntradaSaidaDados.escolherSolicitante(projetoEscolhido.retornarListaPessoas());

            String data = LocalDate.now().toString();
            RelatorioGeral relatorio = new RelatorioGeral(data, solicitante);

            switch (tipo) {
                case 1:
                    EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDadosGerais(projetoEscolhido, solicitante));
                    break;
                case 2:
                    EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasAlocadas(projetoEscolhido, solicitante));
                    break;
            }
        } else {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder gerar relatórios.");
            //criarProjeto();
        }


    }

    private static void adicionarRecurso() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder alocar um recurso nele.");
//			criarProjeto();
        } else {
            int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
            Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
            String nome = EntradaSaidaDados.retornarTexto("Informe o nome do recurso");
            double valor = EntradaSaidaDados.retornarReal("Informe o preço do recurso");
            Recurso recurso = new Recurso(nome, valor);
            projetoEscolhido.adicionarRecurso(recurso);
        }
    }

    private static void alocarTarefa() {
        Pessoa pessoaEscolhida = null;
        Tarefa tarefaEscolhida = null;
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder alocar uma tarefa nele.");
//			criarProjeto();
        } else {
            int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
            Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
            if (projetoEscolhido.retornarListaTarefas() == null) {
                EntradaSaidaDados.mostrarMensagem("Adicione tarefas ao projeto");
                adicionarTarefa();
            } else {
                int posicaoTarefa = EntradaSaidaDados.escolherTarefa(projetoEscolhido.retornarListaTarefas());
                tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
            }

            if (projetoEscolhido.retornarListaPessoas() == null) {
                EntradaSaidaDados.mostrarMensagem("Adicione pessoas ao projeto");
                adicionarPessoa();
            } else {
                int posicaoPessoa = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas());
                pessoaEscolhida = projetoEscolhido.retornarPessoa(posicaoPessoa);
            }
            projetoEscolhido.alocarTarefa(pessoaEscolhida, tarefaEscolhida);
            String pessoaCargo = "Pessoa alocada: " + pessoaEscolhida.getNome() + "\nCargo da pessoa alocada: " + pessoaEscolhida.getCargo().getNome();
            EntradaSaidaDados.mostrarMensagem(pessoaCargo);
        }
    }

    private static void adicionarPessoa() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder adicionar uma pessoa nele.");
//			criarProjeto();
        } else {
            String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa");
            String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa");
            Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto"));
            Pessoa p = new Pessoa(nome, sobrenome, cargo);
            int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
            Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
            projetoEscolhido.adicionarPessoa(p);
        }
    }

    private static void adicionarTarefa() {
        if (!getHasProjects()) {
            EntradaSaidaDados.mostrarMensagem("Nenhum projeto encontrado. Adicione um projeto para poder adicionar uma tarefa nele.");
//			criarProjeto();
        } else {
            String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa");
            String prazo = EntradaSaidaDados.retornarTexto("Informe o prazo da tarefa");
            var prioridade = EntradaSaidaDados.escolherPrioridade();
            Tarefa t = new Tarefa(nome, prazo, prioridade);
            int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
            Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
            projetoEscolhido.adicionarTarefa(t);
        }
    }

    private static void criarProjeto() {
        String titulo = EntradaSaidaDados.retornarTexto("Informe o título do projeto");
        String cliente = EntradaSaidaDados.retornarTexto("Informe o cliente do projeto");
        var dataInicial = EntradaSaidaDados.retornarData("Informe a data inicial do projeto");
        var dataFinal = EntradaSaidaDados.retornarData("Informe a data final do projeto");

        Projeto p = new Projeto(titulo, cliente, dataInicial, dataFinal);
        GestaoProjetos.adicionarProjeto(p);
    }

    private static boolean getHasProjects() {
        return GestaoProjetos.retornarListaProjetos().getItemCount() > 0;
    }
}

