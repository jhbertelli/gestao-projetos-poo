public enum StatusProjeto {
    EmAndamento("Em Andamento"),
    Finalizado("Finalizado");

    private final String displayName;

    StatusProjeto(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
