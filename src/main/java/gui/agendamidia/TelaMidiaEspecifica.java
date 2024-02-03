package gui.agendamidia;

import BD.AvaliacaoController;
import BD.ListaController;
import exceptions.MidiaJaNaListaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelo.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class TelaMidiaEspecifica implements Initializable {
    private AvaliacaoController ac = new AvaliacaoController();
    private Midia midia;
    public VBox avaliacaoScrollPane;
    public Label midiaLabel;
    public Label anoMidia;
    public Label generoMidia;
    public Label campoEspecificoMidia;
    public Label notaMidia;
    public TextArea comentario;
    public Slider notaSlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            midia = ApplicationController.getInstance().getMidia();
            midia.setAvaliacoes(ac.getAvaliacoes(midia.getId()));
            this.carregarAvaliacoes();
            this.carregarNota();

            midiaLabel.setText(midia.getTitulo());
            anoMidia.setText("Ano: " + midia.getAno());
            generoMidia.setText("Gênero: " + midia.getGenero().toString());
            if (midia instanceof Filme) {
                campoEspecificoMidia.setText("Duração: " + ((Filme) midia).getDuracao() + " minutos");
            } else if (midia instanceof Serie) {
                campoEspecificoMidia.setText("Número de episódios: " + ((Serie) midia).getNumEpisodios());
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void carregarAvaliacoes() throws IOException {
        for (Avaliacao av : midia.getAvaliacoes()) {
            FXMLLoader avalicaoLoader = new FXMLLoader(Main.class.getResource("AvaliacaoComponente.fxml"));
            avalicaoLoader.setController(new AvaliacaoComponente(av));
            Node an = avalicaoLoader.load();
            avaliacaoScrollPane.getChildren().add(an);
        }
    }

    private void carregarNota() {
        notaMidia.setText("Nota: " + (!midia.getAvaliacoes().isEmpty() ? String.format(Locale.US, "%.2f", midia.getMedia()) : "Sem nota"));
    }

    private void recarregarDados() throws SQLException, IOException {
        midia.setAvaliacoes(ac.getAvaliacoes(midia.getId()));
        avaliacaoScrollPane.getChildren().clear();
        carregarAvaliacoes();
        carregarNota();
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraTelaMidias();
    }

    public void criarAvaliacao(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);

        if (comentario.getText().isEmpty() || notaSlider.getValue() == 0) {
            alerta.setContentText("Preencha nota ou comentário");
            alerta.showAndWait();
        } else {
            Midia midia = ApplicationController.getInstance().getMidia();
            Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
            try {
                ac.addAvaliacao(usuario.getUsuario(), midia.getId(), comentario.getText(), notaSlider.getValue());

                alerta.setContentText("Avaliação Criada");
                alerta.showAndWait();

                this.recarregarDados();
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adicionarLista(ActionEvent actionEvent) throws SQLException {
        ListaController lc = new ListaController();
        Vector<Lista> listas = lc.getListas(ApplicationController.getInstance().getUsuarioLogado().getUsuario());

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alerta");
        alerta.setHeaderText(null);
        if (listas.isEmpty()) {

            alerta.setContentText("É preciso criar uma lista primeiro");
            alerta.showAndWait();
            return;
        }

        Vector<String> choices = new Vector<>();

        for (Lista lista : listas) {
            choices.add(lista.getNomeDaLista());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Alerta");
        dialog.setHeaderText(null);
        dialog.setContentText("Escolha a Lista:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            int index = choices.indexOf(result.get());
            Lista listaEscolhida = listas.get(index);
            try {
                lc.adicionarMidiaLista(listaEscolhida.getId(), midia.getId());
                alerta.setContentText(midia.getTitulo() + " foi adicionado a lista: " + listaEscolhida.getNomeDaLista());
                alerta.showAndWait();
            } catch (MidiaJaNaListaException e) {
                alerta.setContentText(midia.getTitulo() + " já está na lista: " + listaEscolhida.getNomeDaLista());
                alerta.showAndWait();
            }
        }
    }
}
