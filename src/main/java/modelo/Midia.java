package modelo;

import java.util.Vector;

public abstract class Midia {
    private int id;
    private String titulo;
    private int ano;
    private Vector<Avaliacao> avaliacoes = new Vector<>();
    private Genero genero;

    public Midia(int id, String titulo, int ano, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.genero = genero;
    }

    public float getMedia() {
        float total = 0;
        for (int i = 0; i < this.avaliacoes.size(); i++) {
            total += this.avaliacoes.get(i).getNota();
        }
        total /= this.avaliacoes.size();
        return total;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Vector<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Vector<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
