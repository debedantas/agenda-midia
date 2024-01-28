package modelo;

import java.util.Vector;

public class UsuarioCritico implements IUsuario {
    private String usuario;
    private String senha;
    private Vector<Lista> listas = new Vector<>();

    public UsuarioCritico(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setListas(Vector<Lista> listas) {
        this.listas = listas;
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
