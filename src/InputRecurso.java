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

        Double valor = EntradaSaidaDados.retornarReal("Informe o preço do recurso");
        if (inputCancelado(valor)) {
            setCancelado(true);
            return;
        }

        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}
