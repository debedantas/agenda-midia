package modelo;

public class Filme extends Midia {
    private int duracao;

    public Filme(String titulo, int ano, Genero genero, int duracao) {
        super(titulo, ano, genero);
        this.duracao = duracao;
    }

    // CRUD();
    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
