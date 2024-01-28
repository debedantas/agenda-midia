package modelo;

import java.util.Vector;

public class UsuarioAdmin implements IUsuario {
    private String usuario;
    private String senha;
    private Vector<Lista> listas = new Vector<>();

    public UsuarioAdmin(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public Vector<Lista> getListas() {
        return this.listas;
    }

    @Override
    public Lista getLista(String nomeDaLista) {
        // Procura nas listas se existe alguma lista com o nome passado
        for (int i = 0; i < this.listas.size(); i++) {
            if (this.listas.get(i).getNomeDaLista().equals(nomeDaLista)) {
                return this.listas.get(i);
            }
        }

        return null;
    }
}
