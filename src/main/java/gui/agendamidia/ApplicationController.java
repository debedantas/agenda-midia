package gui.agendamidia;

import modelo.Midia;
import modelo.Usuario;

import java.util.Vector;

public class ApplicationController {
    private static ApplicationController instance;
    private Usuario usuarioLogado;
    private Midia midia;
    private Vector<Midia> showMidias;
    private String paginaAnterior;
    private String paginaLabel;
    private int listaId;

    private ApplicationController() {}

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }

        return instance;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario u) {
        usuarioLogado = u;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setShowMidias(Vector<Midia> showMidias) {
        this.showMidias = showMidias;
    }

    public Vector<Midia> getShowMidias() {
        return showMidias;
    }

    public String getPaginaAnterior() {
        return paginaAnterior;
    }

    public void setPaginaAnterior(String paginaAnterior) {
        this.paginaAnterior = paginaAnterior;
    }

    public String getPaginaLabel() {
        return paginaLabel;
    }

    public void setPaginaLabel(String paginaLabel) {
        this.paginaLabel = paginaLabel;
    }

    public int getListaId() {
        return listaId;
    }

    public void setListaId(int listaId) {
        this.listaId = listaId;
    }
}
