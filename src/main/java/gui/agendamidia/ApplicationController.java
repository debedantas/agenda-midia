package gui.agendamidia;

import modelo.Midia;
import modelo.Usuario;

import java.util.Vector;

public class ApplicationController {
    private static ApplicationController instance;
    private Usuario usuarioLogado; // Informação do usuário que está usando a aplicação (EX: nome, se é admin, as listas, etc)
    private Midia midia; // Informação de qual mídia deve ser aberta na tela de mídia específica
    private Vector<Midia> showMidias; // Informação de qual mídias devem aparecer na lista de midias
    private String paginaAnterior; // Algumas páginas precisam da informação de qual página voltar (ex: TelaMidias)
    private String paginaLabel; // Informação para a TelaMidias qual deve ser o título da página
    private String deletarTipo; // Na tela de Deletar, passa a informação de qual tipo de deletar deve ser (ou é usuario ou midia), a informação vem da tela Admin

    private int listaId; //  na TelaMidias (quando está mostrando as midias de uma lista) serve para saber qual a lista selecionada quando for fazer alterações nela (Exemplo: renomear, deletar, remover midia)


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

    public String getDeletarTipo() {
        return deletarTipo;
    }

    public void setDeletarTipo(String deletarTipo) {
        this.deletarTipo = deletarTipo;
    }
}
