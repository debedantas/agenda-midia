package modelo;

import exceptions.MidiaJaNaListaException;

import java.util.Vector;

public class Lista {
    private String nomeDaLista;
    private Vector<Midia> listaDeMidia = new Vector<>();

    public Lista() {
        this.nomeDaLista = "";
    }

    public Lista(String nomeDaLista) {
        this.nomeDaLista = nomeDaLista;
    }

    public void adicionarMidia(Midia midia) throws MidiaJaNaListaException {
        if (midia != null) {
            if (listaDeMidia.contains(midia)) {
                throw new MidiaJaNaListaException("modelo.Midia '" + midia.getTitulo() + "' já está na lista");
            }
            listaDeMidia.add(midia);
        }
    }

    public void removerMidia(Midia midia) {
        if (midia != null) {
            listaDeMidia.remove(midia);
        }
    }

    public Lista getMidiasPorGenero(Genero genero) {
        Vector<Midia> midiaComGenero = new Vector<>();
        for (int i = 0; i < this.listaDeMidia.size(); i++) {
            if (this.listaDeMidia.get(i).getGenero().equals(genero)) {
                midiaComGenero.add(this.listaDeMidia.get(i));
            }
        }
        Lista novaLista = new Lista();
        novaLista.setListaDeMidia(midiaComGenero);
        return novaLista;
    }

    public String getNomeDaLista() {
        return nomeDaLista;
    }

    public void setNomeDaLista(String nomeDaLista) {
        this.nomeDaLista = nomeDaLista;
    }

    public Vector<Midia> getListaDeMidia() {
        return listaDeMidia;
    }

    private void setListaDeMidia(Vector<Midia> lista) {
        this.listaDeMidia = lista;
    }
}
