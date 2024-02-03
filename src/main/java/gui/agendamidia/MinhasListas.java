package gui.agendamidia;

import BD.ListaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.Lista;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class MinhasListas implements Initializable {
    public Label midiaLabel;
    public VBox listaScrollPane;
    private final ListaController lc = new ListaController();

    public void criarLista(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Alerta");
        dialog.setHeaderText(null);
        dialog.setContentText("Nome da Lista:");

        ButtonType botaoCriar = new ButtonType("Criar", ButtonType.OK.getButtonData());
        ButtonType botaoCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

        dialog.getDialogPane().getButtonTypes().setAll(botaoCriar, botaoCancelar);
        dialog.getDialogPane().lookupButton(botaoCriar).disableProperty().bind(dialog.getEditor().textProperty().isEmpty());

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().trim().isEmpty()){
            try {
                Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
                lc.addLista(usuario.getUsuario(), result.get());
                listaScrollPane.getChildren().clear();
                carregarListas();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText(null);
                alert.setContentText("Nome já em uso");
                alert.showAndWait();
            }
        }
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaPrincipal();
    }

    private void carregarListas() throws SQLException {
        Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
        Vector<Lista> listas = lc.getListas(usuario.getUsuario());
        for (Lista lista : listas) {
            Button b = new Button();
            b.setOnAction(actionEvent -> {
                try {
                    ApplicationController.getInstance().setShowMidias(lc.midiasDaLista(lista.getId()));
                    ApplicationController.getInstance().setPaginaLabel(lista.getNomeDaLista());
                    ApplicationController.getInstance().setPaginaAnterior("Minhas Listas");
                    ApplicationController.getInstance().setListaId(lista.getId());
                    TelasController.getInstance().mostraTelaMidias();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            b.setText(lista.getNomeDaLista());
            listaScrollPane.getChildren().add(b);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            carregarListas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
