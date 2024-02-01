package gui.agendamidia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AvaliacaoComponente implements Initializable {
    private final Avaliacao avaliacao;
    private final boolean mostrarEditavel;
    private final VBox scrollPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox avaliacaoVBox;

    @FXML
    private Label comentario;

    @FXML
    private Label data;

    @FXML
    private HBox editAvaliacao;

    @FXML
    private Label nota;

    @FXML
    private Label user;

    public AvaliacaoComponente(Avaliacao avaliacao, VBox scrollPane) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = true;
        this.scrollPane = scrollPane;
    }

    public AvaliacaoComponente(Avaliacao avaliacao, VBox scrollPane, boolean mostrarEditavel) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = mostrarEditavel;
        this.scrollPane = scrollPane;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.setText(avaliacao.getUsuario());
        nota.setText("Nota: " + avaliacao.getNota());
        comentario.setText("Comentário: " + avaliacao.getComentario());
        data.setText("Data: " + avaliacao.getCreatedAt().toString());
        if (!this.mostrarEditavel) {
            avaliacaoVBox.getChildren().remove(editAvaliacao);
        }
    }

    @FXML
    void deletar(ActionEvent actionEvent) {
        System.out.println("deletado");
    }

    @FXML
    void editar(ActionEvent actionEvent) throws IOException {
        FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("EditarAvaliacaoComponente.fxml"));
        Parent editAv = avalicaoLoader.load();
        int index = scrollPane.getChildren().indexOf(anchorPane);
        scrollPane.getChildren().remove(index);
        scrollPane.getChildren().add(index, editAv);
    }
}
