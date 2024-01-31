package gui.agendamidia;

import modelo.Midia;
import modelo.Usuario;

public class ApplicationController {
    private static ApplicationController instance;
    private Usuario usuarioLogado;
    private Midia midia;
    private String showMidia;

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

    public void setShowMidia(String showMidia) {
        this.showMidia = showMidia;
    }

    public String getShowMidia() {
        return showMidia;
    }
}
