package gui.agendamidia;

import BD.ListaController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.Lista;
import modelo.Serie;
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

    public void criarLista(ActionEvent actionEvent) throws SQLException {
        TextInputDialog alert = new TextInputDialog("");
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("Nome da Lista:");

        ButtonType botaoCriar = new ButtonType("Criar", ButtonType.OK.getButtonData());
        ButtonType botaoCancelar = new ButtonType("Cancelar", ButtonType.CANCEL.getButtonData());

        alert.getDialogPane().getButtonTypes().setAll(botaoCriar, botaoCancelar);
        alert.getDialogPane().lookupButton(botaoCriar).disableProperty().bind(alert.getEditor().textProperty().isEmpty());

        Optional<String> result = alert.showAndWait();
        if (result.isPresent() && !result.get().trim().isEmpty()){
            Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
            lc.addLista(usuario.getUsuario(), result.get());
            listaScrollPane.getChildren().clear();
            carregarListas();
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
                System.out.println(lista.getNomeDaLista());
//                ApplicationController.getInstance().setMidia(serie);
//                TelasController.getInstance().mostraTelaMidiaEspecifica();
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
