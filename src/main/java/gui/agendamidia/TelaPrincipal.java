package gui.agendamidia;

import BD.MidiaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaPrincipal implements Initializable {
    private MidiaController mc = new MidiaController();
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

    public void mostraFilmes(ActionEvent actionEvent) throws SQLException {
        ApplicationController.getInstance().setShowMidias(mc.getMidias("Filme"));
        ApplicationController.getInstance().setPaginaLabel("Filmes");
        ApplicationController.getInstance().setPaginaAnterior("Tela Principal");
        TelasController.getInstance().mostraTelaMidias();
    }

    public void mostraSeries(ActionEvent actionEvent) throws SQLException {
        ApplicationController.getInstance().setShowMidias(mc.getMidias("Serie"));
        ApplicationController.getInstance().setPaginaLabel("Serie");
        ApplicationController.getInstance().setPaginaAnterior("Tela Principal");
        TelasController.getInstance().mostraTelaMidias();
    }

    public void mostraListas(ActionEvent actionEvent) {
        TelasController.getInstance().mostraMinhasListas();
    }
}
