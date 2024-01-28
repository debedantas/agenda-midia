package gui.agendamidia;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelasController {
    private static TelasController instance;
    private Scene loginScene;
    private Scene cadastroScene;
    private Stage mainStage;
    private TelasController() {
        try {
            FXMLLoader loginLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            this.loginScene = new Scene(loginLoader.load());
            FXMLLoader cadastroLoader = new FXMLLoader(Main.class.getResource("Cadastro.fxml"));
            this.cadastroScene = new Scene(cadastroLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TelasController getInstance() {
        if (instance == null) {
            instance = new TelasController();
        }

        return instance;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
        mainStage.setWidth(720);
        mainStage.setHeight(480);

        mainStage.setTitle("Agenda de Mídia");
    }

    public void mostraLogin() {
        this.mainStage.setScene(loginScene);
        this.mainStage.show();
    }

    public void mostraCadastro() {
        this.mainStage.setScene(cadastroScene);
        this.mainStage.show();
    }

    public void mostraTelaPrincipal() {
    }
}
