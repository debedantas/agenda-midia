package gui.agendamidia;

import BD.AvaliacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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

    public AvaliacaoComponente(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = false;
        this.scrollPane = null;
    }

    public AvaliacaoComponente(Avaliacao avaliacao, VBox scrollPane) {
        this.avaliacao = avaliacao;
        this.mostrarEditavel = false;
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
        } else {
            user.setText(avaliacao.getMidiaTitulo());
        }
    }

    @FXML
    void deletar(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja deletar a avaliação?");

        ButtonType botaoDeletar = new ButtonType("Deletar");
        ButtonType botaoCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(botaoDeletar, botaoCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == botaoDeletar){
            AvaliacaoController ac = new AvaliacaoController();
            ac.deletaAvaliacao(avaliacao.getId());

            assert scrollPane != null;
            scrollPane.getChildren().remove(anchorPane);
        } else {
            System.out.println("cancelou");
        }
    }

    @FXML
    void editar(ActionEvent actionEvent) throws IOException {
        FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("EditarAvaliacaoComponente.fxml"));
        avalicaoLoader.setController(new EditarAvalicaoComponente(avaliacao, scrollPane));
        Parent editAv = avalicaoLoader.load();
        assert scrollPane != null;
        int index = scrollPane.getChildren().indexOf(anchorPane);
        scrollPane.getChildren().remove(index);
        scrollPane.getChildren().add(index, editAv);
    }
}
