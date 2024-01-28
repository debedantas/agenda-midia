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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.TipoUsuario;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Cadastro implements Initializable {
    private ObservableList<String> contaSelectorItems = FXCollections.observableArrayList("Padrao", "Admin", "Critico");

    @FXML
    private AnchorPane cena;

    @FXML
    private ChoiceBox<String> contaSelector;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    void getSource(MouseEvent event) {

    }

    @FXML
    void showLogin(ActionEvent event) {
        TelasController.getInstance().mostraLogin();
    }

    @FXML
    void validar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (login.getText().isEmpty() || senha.getText().isEmpty() || contaSelector.getValue().isEmpty()) {
            alerta.setContentText("Todos os campos precisam ser preenchidos");
            alerta.showAndWait();
        } else {
            try {
                UsuarioController uc = new UsuarioController();
                Usuario user = new Usuario(login.getText(), senha.getText(), TipoUsuario.valueOf(contaSelector.getValue()));
                uc.addUsuario(user); // sai do bloco caso já exista

                alerta.setContentText("Usuario Cadastrado");
                alerta.showAndWait();
                showLogin(null);
            } catch (SQLException | UsuarioJaExisteException e) {
                alerta.setContentText("Usuario Já existe");
                alerta.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        contaSelector.setItems(contaSelectorItems);
        contaSelector.setValue("Padrao");
    }
}
