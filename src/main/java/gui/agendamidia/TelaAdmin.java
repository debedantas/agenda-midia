package gui.agendamidia;

import javafx.event.ActionEvent;

public class TelaAdmin {
    public void adicionarMidia(ActionEvent actionEvent) {
        TelasController.getInstance().mostraAdicionarMidia();
    }

    public void deletarMidia(ActionEvent actionEvent) {
        ApplicationController.getInstance().setDeletarTipo("Midia");
        TelasController.getInstance().mostraDeletar();
    }

    public void cadastrarUsuario(ActionEvent actionEvent) {
        TelasController.getInstance().mostraCadastro();
    }

    public void removerUsuario(ActionEvent actionEvent) {
        ApplicationController.getInstance().setDeletarTipo("Usuario");
        TelasController.getInstance().mostraDeletar();
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaPrincipal();
    }
}
