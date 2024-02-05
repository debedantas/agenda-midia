package gui.agendamidia;

import BD.UsuarioController;
import exceptions.UsuarioJaExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modelo.TipoUsuario;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Cadastro implements Initializable {
    private ObservableList<String> contaSelectorItems = FXCollections.observableArrayList("Padrao", "Admin", "Critico");
    private Usuario usuario;
    @FXML
    private AnchorPane cena;

    @FXML
    private ChoiceBox<String> contaSelector;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;
    public VBox cadastroVBox;
    public VBox contaVBox;

    @FXML
    void voltar(ActionEvent event) {
        if (usuario != null && usuario.getTipo().equals(TipoUsuario.Admin)) {
            TelasController.getInstance().mostraAdmin();
        } else {
            TelasController.getInstance().mostraLogin();
        }
    }

    @FXML
    void validar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (login.getText().isEmpty() || senha.getText().isEmpty()) {
            alerta.setContentText("Todos os campos precisam ser preenchidos");
            alerta.showAndWait();
        } else {
            try {
                Usuario user;
                if (usuario != null && usuario.getTipo().equals(TipoUsuario.Admin)) {
                    user = new Usuario(login.getText(), senha.getText(), TipoUsuario.valueOf(contaSelector.getValue()));
                } else {
                    user = new Usuario(login.getText(), senha.getText(), TipoUsuario.Padrao);
                }

                UsuarioController uc = new UsuarioController();
                uc.addUsuario(user); // sai do bloco caso já exista

                alerta.setContentText("Usuario Cadastrado");
                alerta.showAndWait();
                voltar(null);
            } catch (SQLException | UsuarioJaExisteException e) {
                alerta.setContentText("Usuario Já existe");
                alerta.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuario = ApplicationController.getInstance().getUsuarioLogado();
        if (usuario != null && usuario.getTipo().equals(TipoUsuario.Admin)) {
            contaSelector.setItems(contaSelectorItems);
            contaSelector.setValue("Padrao");
        } else {
            cadastroVBox.getChildren().remove(contaVBox);
        }
    }
}
