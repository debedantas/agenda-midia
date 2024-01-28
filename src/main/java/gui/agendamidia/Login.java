package gui.agendamidia;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Map;

public class Login {
    private Map<String, String> users = Map.of(
            "user1", "senha1"
    );
    public PasswordField senha;
    public TextField login;
    public AnchorPane cena;

    public void logar(ActionEvent actionEvent) {
        // mudar logica de pegar user do banco, criar exception caso user não exista
        try {
            String user = users.get(login.getText());
            if (user.equals(senha.getText())) {
                System.out.println("LOGADO");
            } else {
                System.out.println("NÃO LOGADO");
            }
        } catch(NullPointerException e) {
            System.out.println("USER NÃO EXISTE");
        }
    }

    public void mudarCena(ActionEvent actionEvent) {
        TelasController.getInstance().mostraCadastro();
    }
}
