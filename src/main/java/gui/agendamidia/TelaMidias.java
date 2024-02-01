package gui.agendamidia;

import BD.AvaliacaoController;
import BD.MidiaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modelo.Filme;
import modelo.Serie;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class TelaMidias implements Initializable {
    public VBox midiasScrollPane;
    public Label midiaLabel;

    private void setButtonsFilme(MidiaController mc) throws SQLException {
        Vector<Filme> filmes = mc.getFilmes();
        for (Filme filme : filmes) {
            Button b = new Button();
            b.setOnAction(actionEvent -> {
                ApplicationController.getInstance().setMidia(filme);
                TelasController.getInstance().mostraTelaMidiaEspecifica();
            });
            b.setText(filme.getTitulo());
            midiasScrollPane.getChildren().add(b);
        }
    }

    private void setButtonsSeries(MidiaController mc) throws SQLException {
        Vector<Serie> series = mc.getSeries();
        for (Serie serie : series) {
            Button b = new Button();
            b.setOnAction(actionEvent -> {
                ApplicationController.getInstance().setMidia(serie);
                TelasController.getInstance().mostraTelaMidiaEspecifica();
            });
            b.setText(serie.getTitulo());
            midiasScrollPane.getChildren().add(b);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaPrincipal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String midia = ApplicationController.getInstance().getShowMidia();
            midiaLabel.setText(midia);
            MidiaController mc = new MidiaController();
            if (midia.equals("Filme")) {
                setButtonsFilme(mc);
            } else if (midia.equals("Serie")) {
                setButtonsSeries(mc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
