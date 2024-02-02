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

    public Stage getMainStage() {
        return this.mainStage;
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
        try {
            FXMLLoader telaPrincipalLoader = new FXMLLoader(Main.class.getResource("TelaPrincipal.fxml"));
            Scene telaPrincipalScene = new Scene(telaPrincipalLoader.load());
            this.mainStage.setScene(telaPrincipalScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraMeuPerfil() {
        try {
            FXMLLoader meuPerfilLoader = new FXMLLoader(Main.class.getResource("MeuPerfil.fxml"));
            Scene meuPerfilScene = new Scene(meuPerfilLoader.load());
            this.mainStage.setScene(meuPerfilScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraMinhasAvaliacoes() {
        try {
            FXMLLoader minhasAvaliacoes = new FXMLLoader(Main.class.getResource("MinhasAvaliacoes.fxml"));
            Scene minhasAvaliacoesScene = new Scene(minhasAvaliacoes.load());
            this.mainStage.setScene(minhasAvaliacoesScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraTelaMidias() {
        try {
            FXMLLoader midiasLoader = new FXMLLoader(Main.class.getResource("TelaMidias.fxml"));
            Scene midiasScene = new Scene(midiasLoader.load());
            this.mainStage.setScene(midiasScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraTelaMidiaEspecifica() {
        try {
            FXMLLoader midiaEspecificaLoader = new FXMLLoader(Main.class.getResource("TelaMidiaEspecifica.fxml"));
            Scene midiaEspecificaScene = new Scene(midiaEspecificaLoader.load());
            this.mainStage.setScene(midiaEspecificaScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
