package gui.agendamidia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class Cadastro implements Initializable {
    private ObservableList<String> contaSelectorItems = FXCollections.observableArrayList("Padrão", "Administrador", "Crítico");

    @FXML
    private AnchorPane cena;

    @FXML
    private ChoiceBox<String> contaSelector;

    @FXML
    private TextField login;

    @FXML
    private TextField nome;

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

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        contaSelector.setItems(contaSelectorItems);
        contaSelector.setValue("Padrão");
    }
}
