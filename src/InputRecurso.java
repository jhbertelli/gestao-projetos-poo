public class InputRecurso extends ResultadoInput {
    private String nome;
    private double valor;

    InputRecurso() {
        String nome = EntradaSaidaDados.retornarTexto("Informe o nome do recurso");
        if (inputCancelado(nome)) {
            setCancelado(true);
            return;
        }

        this.nome = nome;

        String valorString = EntradaSaidaDados.retornarTexto("Informe o preço do recurso");
        if (inputCancelado(valorString)) {
            setCancelado(true);
            return;
        }

        this.valor = Double.parseDouble(valorString);
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}
