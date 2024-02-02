package gui.agendamidia;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Usuario;

import java.net.URL;
import java.util.ResourceBundle;

public class MeuPerfil implements Initializable {
    public Label nomeLabel;
    public Label tipoContaLabel;
    private Usuario usuario;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuario = ApplicationController.getInstance().getUsuarioLogado();
        nomeLabel.setText("Nome: " + usuario.getUsuario());
        tipoContaLabel.setText("Tipo de conta: " + usuario.getTipo());
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaPrincipal();
    }

    public void mostraMinhasAvaliacoes(ActionEvent actionEvent) {
        TelasController.getInstance().mostraMinhasAvaliacoes();
    }
}
