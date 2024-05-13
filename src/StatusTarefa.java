public enum StatusTarefa {
    EmAberto("Em Aberto"),
    Encerrada("Encerrada");

    private final String displayName;

    StatusTarefa(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
