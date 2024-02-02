package gui.agendamidia;

import BD.AvaliacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;
import modelo.Midia;
import modelo.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditarAvalicaoComponente implements Initializable {
    private Avaliacao avaliacao;
    private VBox scrollPane;
    public AnchorPane anchorPane;
    public VBox edicaoVBox;
    public Label filmeLabel;
    public Slider notaSlider;
    public TextField comentario;

    public EditarAvalicaoComponente(Avaliacao avaliacao, VBox scrollPane) {
        this.avaliacao = avaliacao;
        this.scrollPane = scrollPane;
    }

    public void atualizarAvaliacao(ActionEvent actionEvent) throws SQLException, IOException {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (comentario.getText().isEmpty() || notaSlider.getValue() == 0) {
            alerta.setContentText("Preencha nota ou comentário");
            alerta.showAndWait();
        } else {
            try {
                AvaliacaoController ac = new AvaliacaoController();
                avaliacao = ac.atualizaAvaliacao(avaliacao.getId(), comentario.getText(), notaSlider.getValue());

                alerta.setContentText("Avaliação Atualizada");
                alerta.showAndWait();
                cancelar(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cancelar(ActionEvent actionEvent) throws IOException {
        FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("AvaliacaoComponente.fxml"));
        avalicaoLoader.setController(new AvaliacaoComponente(avaliacao, scrollPane, true));
        Parent avaliacaoComponente = avalicaoLoader.load();
        int index = scrollPane.getChildren().indexOf(anchorPane);
        scrollPane.getChildren().remove(index);
        scrollPane.getChildren().add(index, avaliacaoComponente);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notaSlider.setValue(avaliacao.getNota());
        comentario.setText(avaliacao.getComentario());
        filmeLabel.setText(avaliacao.getMidiaTitulo());
    }
}
