package modelo;

public enum Genero {
    ACAO("Ação"),
    ANIMACAO("Animação"),
    AVENTURA("Aventura"),
    BIOGRAFIA("Biografia"),
    COMEDIA("Comédia"),
    CRIME("Crime"),
    DOCUMENTARIO("Documentário"),
    DRAMA("Drama"),
    FANTASIA("Fantasia"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    TERROR("Terror");

    private final String label;
    private Genero(String genero) {
        this.label = genero;
    }

    public static Genero getByLabel(String label) {
        for (Genero genero : values()) {
            if (genero.label.equals(label)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("No enum constant with label: " + label);
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static void main(String[] args) {
        System.out.println(Genero.ACAO.name());
        System.out.println(Genero.getByLabel("Ação"));
    }
}
