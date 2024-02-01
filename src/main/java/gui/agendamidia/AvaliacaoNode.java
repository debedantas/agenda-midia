package gui.agendamidia;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AvaliacaoNode implements Initializable {
    private final Avaliacao avaliacao;

    private final boolean mostrarEditavel;

    @FXML
    private VBox avaliacaoVBox;
    @FXML
    private VBox edicaoVBox;

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

    public AvaliacaoNode(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = false;
    }

    public AvaliacaoNode(Avaliacao avaliacao, boolean mostrarEditavel) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = mostrarEditavel;
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
    void editar(ActionEvent actionEvent) {
        edicaoVBox.setVisible(true);
        avaliacaoVBox.setVisible(false);
    }


    @FXML
    void atualizarAvaliacao(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {
    }
}
