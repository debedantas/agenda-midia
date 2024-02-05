package gui.agendamidia;

import BD.MidiaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.TipoUsuario;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaPrincipal implements Initializable {
    private MidiaController mc = new MidiaController();
    public Label bemVindo;
    public VBox telaPrincipalVBox;
    public Button botaoAdmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
        bemVindo.setText("Bem vindo, " + usuario.getUsuario());
        if (!usuario.getTipo().equals(TipoUsuario.Admin)) {
            telaPrincipalVBox.getChildren().remove(botaoAdmin);
        }
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

    public void mostraAdmin(ActionEvent actionEvent) {
        TelasController.getInstance().mostraAdmin();
    }
}
