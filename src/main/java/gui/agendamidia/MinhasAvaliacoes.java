package gui.agendamidia;

import BD.AvaliacaoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

public class MinhasAvaliacoes implements Initializable {
    private Vector<Avaliacao> avaliacoes;
    public VBox avaliacoesScrollPane;

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraMeuPerfil();
    }

    private void carregarAvaliacoes() throws IOException {
        for (Avaliacao av : avaliacoes) {
            FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("AvaliacaoComponente.fxml"));
            avalicaoLoader.setController(new AvaliacaoComponente(av, avaliacoesScrollPane, true));
            Node an = avalicaoLoader.load();
            avaliacoesScrollPane.getChildren().add(an);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AvaliacaoController ac = new AvaliacaoController();
            String usuario = ApplicationController.getInstance().getUsuarioLogado().getUsuario();
            avaliacoes = ac.getAvaliacoes(usuario);
            carregarAvaliacoes();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
