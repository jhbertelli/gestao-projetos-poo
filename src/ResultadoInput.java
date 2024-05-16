public class ResultadoInput {
    private boolean cancelado;

    public boolean isCancelado() {
        return cancelado;
    }

    protected void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    protected static <T> boolean inputCancelado(T input) {
        if (input == null) {
            EntradaSaidaDados.mostrarMensagem("Operação cancelada.");
            return true;
        }

        return false;
    }
}
