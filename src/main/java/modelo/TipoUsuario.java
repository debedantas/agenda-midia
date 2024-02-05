package modelo;

public enum TipoUsuario {
    Padrao("Padrão"),
    Critico("Crítico"),
    Admin("Admin");

    private final String label;
    private TipoUsuario(String tipoUsuario) {
        this.label = tipoUsuario;
    }
    @Override
    public String toString() {
        return this.label;
    }
}
