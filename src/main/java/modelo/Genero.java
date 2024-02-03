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

    @Override
    public String toString() {
        return this.label;
    }

    public static void main(String[] args) {
        System.out.println(Genero.ACAO.name());
    }
}
