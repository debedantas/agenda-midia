package gui.agendamidia;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelasController {
    private static TelasController instance;
    private Stage mainStage;
    private TelasController() {}

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
        try {
            FXMLLoader loginLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
            Scene loginScene = new Scene(loginLoader.load());
            this.mainStage.setScene(loginScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraCadastro() {
        try {
            FXMLLoader cadastroLoader = new FXMLLoader(Main.class.getResource("Cadastro.fxml"));
            Scene cadastroScene = new Scene(cadastroLoader.load());
            this.mainStage.setScene(cadastroScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void mostraMinhasListas() {
        try {
            FXMLLoader minhasListas = new FXMLLoader(Main.class.getResource("MinhasListas.fxml"));
            Scene minhasListasScene = new Scene(minhasListas.load());
            this.mainStage.setScene(minhasListasScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraAdmin() {
        try {
            FXMLLoader telaAdmin = new FXMLLoader(Main.class.getResource("TelaAdmin.fxml"));
            Scene telaAdminScene = new Scene(telaAdmin.load());
            this.mainStage.setScene(telaAdminScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraAdicionarMidia() {
        try {
            FXMLLoader adicionarMidia = new FXMLLoader(Main.class.getResource("AdicionarMidia.fxml"));
            Scene adicionarMidiaScene = new Scene(adicionarMidia.load());
            this.mainStage.setScene(adicionarMidiaScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraDeletar() {
        try {
            FXMLLoader deletar = new FXMLLoader(Main.class.getResource("Deletar.fxml"));
            Scene deletarScene = new Scene(deletar.load());
            this.mainStage.setScene(deletarScene);
            this.mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
