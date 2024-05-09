import javax.swing.JComboBox;
import static javax.swing.JOptionPane.*;

public class EntradaSaidaDados {
	
	
	public static String retornarTexto(String mensagem) {
		return showInputDialog(mensagem);
	}
	
	public static int retornarInteiro(String mensagem) {		
		return Integer.parseInt(showInputDialog(mensagem));
	}
	
	public static double retornarReal(String mensagem) {		
		return Double.parseDouble(showInputDialog(mensagem));
	}
	
	public static int  escolherProjeto(JComboBox<String> listaDeProjetos) {		
		showInternalMessageDialog(null, listaDeProjetos, "Lista de projetos", INFORMATION_MESSAGE, null);
		return listaDeProjetos.getSelectedIndex();
	}

	public static int  escolherTarefa(JComboBox<String> listaDeTarefas) {		
		showMessageDialog(null, listaDeTarefas);
		return listaDeTarefas.getSelectedIndex();
	}
	
	public static int  escolherPessoa(JComboBox<String> listaDePessoas) {		
		showMessageDialog(null, listaDePessoas);
		return listaDePessoas.getSelectedIndex();
	}

	public static void mostrarMensagem(String mensagem) {
		showMessageDialog(null, mensagem);
		
	}

	public static void mostrarRelatorio(String dadosRelatorio) {
		showMessageDialog(null, "Relatório\n"+dadosRelatorio);
	}
	

}
