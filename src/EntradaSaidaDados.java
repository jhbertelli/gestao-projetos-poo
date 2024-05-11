import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

import static javax.swing.JOptionPane.*;

public class EntradaSaidaDados {
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static String retornarTexto(String mensagem) {
        return showInputDialog(mensagem);
    }

    public static Date retornarData(String mensagem) {
        Date date = new Date();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

        boolean retry = false;

        do {
            String input = showInputDialog(mensagem);

            try {
                var localDate = LocalDate.parse(input, formatter);

                // converte de LocalDate para date
                date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                retry = false;
                System.out.println(date);
            }
            catch (DateTimeParseException ex) {
                retry = true;
                showMessageDialog(
                    null,
                    "Você inseriu uma data inválida! Insira uma data válida, no formato: " + DATE_FORMAT
                );
            }
        } while (retry);

        return date;
    }

    public static int retornarInteiro(String mensagem) {
        return Integer.parseInt(showInputDialog(mensagem));
    }

    public static double retornarReal(String mensagem) {
        return Double.parseDouble(showInputDialog(mensagem));
    }

    public static int escolherProjeto(JComboBox<String> listaDeProjetos) {
        showInternalMessageDialog(null, listaDeProjetos, "Lista de projetos", INFORMATION_MESSAGE, null);
        return listaDeProjetos.getSelectedIndex();
    }

    public static int escolherTarefa(JComboBox<String> listaDeTarefas) {
        showMessageDialog(null, listaDeTarefas, "lista de tarefas", INFORMATION_MESSAGE, null);
        return listaDeTarefas.getSelectedIndex();
    }

    public static int escolherPessoa(JComboBox<String> listaDePessoas) {
        showMessageDialog(null, listaDePessoas, "Escolher pessoa", INFORMATION_MESSAGE, null);
        return listaDePessoas.getSelectedIndex();
    }

    public static int escolherSolicitante(JComboBox<String> solicitante) {
        showMessageDialog(null, solicitante, "Escolher solicitante", INFORMATION_MESSAGE, null);
        return solicitante.getSelectedIndex();
    }

    public static void mostrarMensagem(String mensagem) {
        showMessageDialog(null, mensagem);

    }

    public static void mostrarRelatorio(String dadosRelatorio) {
        showMessageDialog(null, "Relatório\n" + dadosRelatorio, "Dados do relatório", INFORMATION_MESSAGE, null);
    }

    public static Prioridade escolherPrioridade() {
        String[] lista = { "1 - Baixa", "2 - Média", "3 - Alta" };
        JComboBox opcoes = new JComboBox(lista);
        // TODO: adicionar uma mensagem de "Escolha a prioridade"
        JOptionPane.showMessageDialog(null, opcoes);

        int prioridadeSelecionada = opcoes.getSelectedIndex();
        // converte a prioridade para o enum
        return Prioridade.values()[prioridadeSelecionada];
    }
}
