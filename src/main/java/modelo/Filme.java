package modelo;

public class Filme extends Midia {
    private int duracao;

    public Filme(int id, String titulo, int ano, Genero genero, int duracao) {
        super(id, titulo, ano, genero);
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
