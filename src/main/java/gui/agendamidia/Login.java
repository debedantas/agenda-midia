package gui.agendamidia;

import BD.UsuarioController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import modelo.Usuario;

import java.sql.SQLException;
import java.util.Map;

public class Login {
    private Map<String, String> users = Map.of(
            "user1", "senha1"
    );
    public PasswordField senha;
    public TextField login;
    public AnchorPane cena;

    public void logar(ActionEvent actionEvent) {
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u = uc.login(login.getText(), senha.getText());
            if (u == null) {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Alerta");
                alerta.setHeaderText(null);
                alerta.setContentText("Usuário ou senha incorretos");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Alerta");
                alerta.setHeaderText(null);
                alerta.setContentText("LOGADO");
                alerta.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mudarCena(ActionEvent actionEvent) {
        TelasController.getInstance().mostraCadastro();
    }
}
