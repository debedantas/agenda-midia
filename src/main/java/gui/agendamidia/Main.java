package gui.agendamidia;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        TelasController.getInstance().setMainStage(stage);
        TelasController.getInstance().mostraLogin();
    }

    public static void main(String[] args) {
        launch();
    }
}