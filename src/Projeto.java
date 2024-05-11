import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;

public class Projeto {

    private String titulo;
    private String cliente;
    private ArrayList<Recurso> listaDeRecursos = new ArrayList<Recurso>();
    private ArrayList<Pessoa> listaDePessoas = new ArrayList<Pessoa>();
    private Date dataInicial;
    private Date dataFinal;
    private ArrayList<Tarefa> listaDeTarefas = new ArrayList<Tarefa>();
    private ArrayList<TarefaAlocada> listaDeTarefasAlocadas = new ArrayList<TarefaAlocada>();
    private StatusProjeto status;

    //Não é necessário representar no diagrama os getters and setters

    Projeto(String titulo, String cliente, Date dataInicial, Date dataFinal) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.status = StatusProjeto.EmAndamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setArea(String cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Recurso> getListaDeRecursos() {
        return listaDeRecursos;
    }

    public void setListaDeRecursos(ArrayList<Recurso> listaDeRecursos) {
        this.listaDeRecursos = listaDeRecursos;
    }

    public ArrayList<Pessoa> getListaDePessoas() {
        return listaDePessoas;
    }

    public void setListaDePessoas(ArrayList<Pessoa> listaDePessoas) {
        this.listaDePessoas = listaDePessoas;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public ArrayList<Tarefa> getListaDeTarefas() {
        return listaDeTarefas;
    }

    public void setListaDeTarefas(ArrayList<Tarefa> listaDeTarefas) {
        this.listaDeTarefas = listaDeTarefas;
    }

    public ArrayList<TarefaAlocada> getListaDeTarefasAlocadas() {
        return listaDeTarefasAlocadas;
    }

    public void setListaDeTarefasAlocadas(ArrayList<TarefaAlocada> listaDeTarefasAlocadas) {
        this.listaDeTarefasAlocadas = listaDeTarefasAlocadas;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public void adicionarRecurso(Recurso recurso) {
        this.listaDeRecursos.add(recurso);
    }

    public void adicionarPessoa(Pessoa pessoa) {
        this.listaDePessoas.add(pessoa);
    }

    public void adicionarTarefa(Tarefa tarefa) {
        this.listaDeTarefas.add(tarefa);
    }


    public void alocarTarefa(Pessoa pessoa, Tarefa tarefa) {
        TarefaAlocada tarefaAlocada = new TarefaAlocada(pessoa, tarefa, pessoa);
        this.listaDeTarefasAlocadas.add(tarefaAlocada);

    }

    public JComboBox<String> retornarListaTarefas() {
        JComboBox<String> lista = new JComboBox<String>();
        if (!this.listaDeTarefas.isEmpty()) {
            for (Tarefa t : listaDeTarefas) {
                lista.addItem(t.getNome());
            }
        } else {
            return null;
        }
        return lista;
    }

    public JComboBox<String> retornarListaPessoas() {
        JComboBox<String> lista = new JComboBox<String>();
        if (this.listaDePessoas.size() > 0) {
            for (Pessoa p : this.listaDePessoas) {
                lista.addItem(p.getNome());
            }
        } else {
            return null;
        }
        return lista;
    }

    public Tarefa retornarTarefa(int posicaoTarefa) {
        return this.listaDeTarefas.get(posicaoTarefa);
    }

    public Pessoa retornarPessoa(int posicaoPessoa) {
        return this.listaDePessoas.get(posicaoPessoa);
    }


}
