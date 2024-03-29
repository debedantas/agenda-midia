package modelo;

import exceptions.MidiaJaNaListaException;

public class testeModelos {
    public static void main(String[] args) throws MidiaJaNaListaException {
        Midia filme1 = new Filme(1, "filme1", 2012, Genero.AVENTURA, 120);
        Midia filme2 = new Filme(2, "filme2", 2022, Genero.TERROR, 100);
        Midia serie1 = new Serie(3, "serie1", 1999, Genero.AVENTURA, 24);
        Midia serie2 = new Filme(4, "serie2", 2018, Genero.MUSICAL, 40);

        Usuario usuario = new Usuario("user", "senha", TipoUsuario.Padrao);
        Lista lista1 = new Lista("Lista 1");
        try {
            lista1.adicionarMidia(filme1);
            lista1.adicionarMidia(filme2);
            lista1.adicionarMidia(serie1);
            lista1.adicionarMidia(serie1);
        } catch (MidiaJaNaListaException exception) {
            System.out.println(exception.getMessage());
        }
        for (int i = 0; i < lista1.getListaDeMidia().size(); i ++) {
            System.out.println(lista1.getListaDeMidia().get(i).getTitulo());
        }

        lista1.adicionarMidia(serie2);
        Lista lista2 = lista1.getMidiasPorGenero(Genero.AVENTURA);
        System.out.println("Midias com genero de aventura:");
        for (int i = 0; i < lista2.getListaDeMidia().size(); i ++) {
            System.out.println(lista2.getListaDeMidia().get(i).getTitulo());
        }
        usuario.getListas().add(lista1);
    }
}
