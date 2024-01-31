package gui.agendamidia;

import BD.AvaliacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import modelo.Filme;
import modelo.Midia;
import modelo.Serie;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TelaMidiaEspecifica implements Initializable {
    public Label midiaLabel;
    public Label campoEspecificoMidia;
    public TextArea comentario;
    public Slider notaSlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Midia midia = ApplicationController.getInstance().getMidia();
        midiaLabel.setText(midia.getTitulo());
        if (midia instanceof Filme) {
            campoEspecificoMidia.setText("Duração: " + ((Filme) midia).getDuracao() + " minutos");
        } else if (midia instanceof Serie) {
            campoEspecificoMidia.setText("Número de episódios: " + ((Serie) midia).getNumEpisodios());
        }
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaMidias();
    }

    public void criarAvaliacao(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (comentario.getText().isEmpty() || notaSlider.getValue() == 0) {
            alerta.setContentText("Preencha nota ou comentário");
            alerta.showAndWait();
        } else {
            AvaliacaoController ac = new AvaliacaoController();
            Midia midia = ApplicationController.getInstance().getMidia();
            Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
            try {
                ac.addAvaliacao(usuario.getUsuario(), midia.getId(), comentario.getText(), notaSlider.getValue());

                alerta.setContentText("Avaliação Criada");
                alerta.showAndWait();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
