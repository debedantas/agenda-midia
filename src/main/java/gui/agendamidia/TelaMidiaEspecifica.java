package gui.agendamidia;

import BD.AvaliacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TelaMidiaEspecifica implements Initializable {
    private AvaliacaoController ac = new AvaliacaoController();
    private Midia midia;
    public VBox avaliacaoScrollPane;
    public Label midiaLabel;
    public Label campoEspecificoMidia;
    public Label notaMidia;
    public TextArea comentario;
    public Slider notaSlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            midia = ApplicationController.getInstance().getMidia();
            midia.setAvaliacoes(ac.getAvaliacoes(midia.getId()));
            this.carregarAvaliacoes();
            this.carregarNota();

            midiaLabel.setText(midia.getTitulo());
            if (midia instanceof Filme) {
                campoEspecificoMidia.setText("Duração: " + ((Filme) midia).getDuracao() + " minutos");
            } else if (midia instanceof Serie) {
                campoEspecificoMidia.setText("Número de episódios: " + ((Serie) midia).getNumEpisodios());
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarAvaliacoes() throws IOException {
        for (Avaliacao av : midia.getAvaliacoes()) {
            FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("editarAvaliacao.fxml"));
            avalicaoLoader.setController(new AvaliacaoNode(av));
            Node an = avalicaoLoader.load();
            avaliacaoScrollPane.getChildren().add(an);
        }
    }

    private void carregarNota() {
        notaMidia.setText("Nota: " + (!midia.getAvaliacoes().isEmpty() ? midia.getMedia() : "Sem nota"));
    }

    private void recarregarDados() throws SQLException, IOException {
        midia.setAvaliacoes(ac.getAvaliacoes(midia.getId()));
        avaliacaoScrollPane.getChildren().clear();
        carregarAvaliacoes();
        carregarNota();
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
            Midia midia = ApplicationController.getInstance().getMidia();
            Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
            try {
                ac.addAvaliacao(usuario.getUsuario(), midia.getId(), comentario.getText(), notaSlider.getValue());

                alerta.setContentText("Avaliação Criada");
                alerta.showAndWait();

                this.recarregarDados();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
