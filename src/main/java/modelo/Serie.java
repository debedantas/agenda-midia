package modelo;

public class Serie extends Midia {
    private int numEpisodios;

    public Serie(int id, String titulo, int ano, Genero genero, int numEpisodios) {
        super(id, titulo, ano, genero);
        this.numEpisodios = numEpisodios;
    }

    // CRUD();
    public int getNumEpisodios() {
        return numEpisodios;
    }

    public void setNumEpisodios(int numEpisodios) {
        this.numEpisodios = numEpisodios;
    }
}
