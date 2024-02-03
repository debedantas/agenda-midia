package gui.agendamidia;

import BD.AvaliacaoController;
import BD.ListaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Midia;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class TelaMidias implements Initializable {
    public VBox midiasScrollPane;
    public Label midiaLabel;
    public HBox botoesHBox;
    public Button deletarListaButton;

    private void setButtonsMidia(Vector<Midia> midias) throws SQLException {
        for (Midia midia : midias) {
            Button b = new Button();
            b.setOnAction(actionEvent -> {
                ApplicationController.getInstance().setMidia(midia);
                TelasController.getInstance().mostraTelaMidiaEspecifica();
            });
            b.setText(midia.getTitulo());
            midiasScrollPane.getChildren().add(b);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        String paginaAnterior = ApplicationController.getInstance().getPaginaAnterior();
        if (paginaAnterior.equals("Minhas Listas")) {
            TelasController.getInstance().mostraMinhasListas();
        } else {
            TelasController.getInstance().mostraTelaPrincipal();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Vector<Midia> midias = ApplicationController.getInstance().getShowMidias();
            midiaLabel.setText(ApplicationController.getInstance().getPaginaLabel());
            setButtonsMidia(midias);
            String paginaAnterior = ApplicationController.getInstance().getPaginaAnterior();
            if (!paginaAnterior.equals("Minhas Listas")) {
                botoesHBox.getChildren().remove(deletarListaButton);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarLista(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja deletar a lista?");

        ButtonType botaoDeletar = new ButtonType("Deletar");
        ButtonType botaoCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(botaoDeletar, botaoCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == botaoDeletar){
            ListaController lc = new ListaController();
            int listaId = ApplicationController.getInstance().getListaId();
            lc.deletarLista(listaId);

            voltar(null);
        } else {
            System.out.println("cancelou");
        }
    }
}
