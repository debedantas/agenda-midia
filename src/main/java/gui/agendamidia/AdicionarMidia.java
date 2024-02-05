package gui.agendamidia;

import BD.MidiaController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import modelo.Genero;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdicionarMidia implements Initializable {
    private ObservableList<String> tipoSelectorItems = FXCollections.observableArrayList("Filme", "Série");
    public ChoiceBox<String> tipoSelector;
    public VBox cadastroVBox;
    public TextField titulo;
    public TextField ano;
    public TextField campoEspecifico;
    public VBox generoVBox;
    public ChoiceBox<String> generoSelector;
    public VBox tipoVBox;
    public Label campoEspecificoLabel;

    public void validar(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (titulo.getText().isEmpty() || ano.getText().isEmpty() || campoEspecifico.getText().isEmpty() || generoSelector.getValue().isEmpty()) {
            alerta.setContentText("Todos os campos precisam ser preenchidos");
            alerta.showAndWait();
        } else {
            MidiaController mc = new MidiaController();
            try {
                if (tipoSelector.getValue().equals("Filme")) {
                    mc.adicionarFilme(titulo.getText(), Integer.parseInt(ano.getText()), Genero.getByLabel(generoSelector.getValue()), Integer.parseInt(campoEspecifico.getText()));
                } else {
                    mc.adicionarSerie(titulo.getText(), Integer.parseInt(ano.getText()), Genero.getByLabel(generoSelector.getValue()), Integer.parseInt(campoEspecifico.getText()));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            alerta.setContentText("Mídia Adicionada");
            alerta.showAndWait();
            voltar(null);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraAdmin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFormatter<Integer> formatoNumerico = textFieldNumerico();
        ano.setTextFormatter(formatoNumerico);
        TextFormatter<Integer> formatoNumerico2 = textFieldNumerico();
        campoEspecifico.setTextFormatter(formatoNumerico2);

        tipoSelector.getSelectionModel().selectedItemProperty().addListener((observable, antigoValor, novoValor) -> {
            if (novoValor.equals("Filme")) {
                campoEspecificoLabel.setText("Duração:");
            } else {
                campoEspecificoLabel.setText("Número de Episódios:");
            }
        });

        ObservableList<String> generos = FXCollections.observableArrayList();
        for (Genero genero : Genero.values()) {
            generos.add(genero.toString());
        }
        generoSelector.setItems(generos);
        generoSelector.setValue(generos.get(0));
        tipoSelector.setItems(tipoSelectorItems);
        tipoSelector.setValue("Filme");
    }

    private TextFormatter<Integer> textFieldNumerico() {
        return new TextFormatter<>(new IntegerStringConverter(), null, change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change; // Accept the change
            }
            return null; // Reject the change
        });
    }
}
