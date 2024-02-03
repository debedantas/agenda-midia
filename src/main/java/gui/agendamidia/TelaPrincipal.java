package gui.agendamidia;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipal implements Initializable {
    public Label bemVindo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bemVindo.setText("Bem vindo, " + ApplicationController.getInstance().getUsuarioLogado().getUsuario());
    }

    public void logout(ActionEvent actionEvent) {
        ApplicationController.getInstance().setUsuarioLogado(null);
        TelasController.getInstance().mostraLogin();
    }

    public void meuPerfil(ActionEvent actionEvent) {
        TelasController.getInstance().mostraMeuPerfil();
    }

    public void mostraFilmes(ActionEvent actionEvent) {
        ApplicationController.getInstance().setShowMidia("Filme");
        TelasController.getInstance().mostraTelaMidias();
    }

    public void mostraSeries(ActionEvent actionEvent) {
        ApplicationController.getInstance().setShowMidia("Serie");
        TelasController.getInstance().mostraTelaMidias();
    }

    public void mostraListas(ActionEvent actionEvent) {
        TelasController.getInstance().mostraMinhasListas();
    }
}
