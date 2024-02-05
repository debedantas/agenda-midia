package gui.agendamidia;

import BD.ListaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Midia;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class TelaMidias implements Initializable {
    private ListaController lc = new ListaController();
    private boolean estadoDeleta = false;
    public VBox midiasScrollPane;
    public Label midiaLabel;
    public HBox botoesHBox;
    public Button removerMidiaButton;
    public HBox labelHBox;
    public Button deletarListaButton;
    public Button editarListaButton;

    private void setButtonsMidia(Vector<Midia> midias) throws SQLException {
        for (Midia midia : midias) {
            Button b = new Button();
            b.setText(midia.getTitulo());
            b.setOnAction(actionEvent -> {
                if (estadoDeleta) {
                    removerMidiaLista(b, midia);
                } else {
                    ApplicationController.getInstance().setMidia(midia);
                    TelasController.getInstance().mostraTelaMidiaEspecifica();
                }
            });
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
                labelHBox.getChildren().remove(deletarListaButton);
                labelHBox.getChildren().remove(editarListaButton);
                botoesHBox.getChildren().remove(removerMidiaButton);
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
            int listaId = ApplicationController.getInstance().getListaId();
            lc.deletarLista(listaId);

            voltar(null);
        } else {
            System.out.println("cancelou");
        }
    }

    public void editarNomeLista(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog(midiaLabel.getText());
        dialog.setTitle("Alerta");
        dialog.setHeaderText(null);
        dialog.setContentText("Nome da Lista:");

        ButtonType botaoCriar = new ButtonType("Editar", ButtonType.OK.getButtonData());
        ButtonType botaoCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

        dialog.getDialogPane().getButtonTypes().setAll(botaoCriar, botaoCancelar);
        dialog.getDialogPane().lookupButton(botaoCriar).disableProperty().bind(dialog.getEditor().textProperty().isEmpty());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().trim().isEmpty()){
            try {
                int listaId = ApplicationController.getInstance().getListaId();
                lc.editarNomeLista(listaId, result.get().trim());
                midiaLabel.setText(result.get().trim());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Nome já em uso");
                alert.showAndWait();
            }
        }
    }

    public void trocarEstado(ActionEvent actionEvent) {
        String deleteButtonStyle = "-fx-border-color: #777; -fx-border-radius: 2; -fx-background-color: #e63946; -fx-text-fill: #FCF6F5";
        if (estadoDeleta) {
            removerMidiaButton.setText("Remover Mídia");
            removerMidiaButton.setStyle(deleteButtonStyle);
            for (Node botao : midiasScrollPane.getChildren()) {
                botao.setStyle("");
            }
        } else {
            removerMidiaButton.setText("Cancelar");
            removerMidiaButton.setStyle("");
            for (Node botao : midiasScrollPane.getChildren()) {
                botao.setStyle(deleteButtonStyle);
            }
        }
        estadoDeleta = !estadoDeleta;
    }

    private void removerMidiaLista(Button button, Midia midia) {
        try {
            int listaId = ApplicationController.getInstance().getListaId();
            lc.removerMidiaLista(listaId, midia.getId());

            midiasScrollPane.getChildren().remove(button);
            ApplicationController.getInstance().setShowMidias(lc.midiasDaLista(listaId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
