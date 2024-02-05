package gui.agendamidia;

import BD.AvaliacaoController;
import BD.ListaController;
import BD.MidiaController;
import BD.UsuarioController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import modelo.Avaliacao;
import modelo.Lista;
import modelo.Midia;
import modelo.Usuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class Deletar implements Initializable {
    private MidiaController mc = new MidiaController();
    private UsuarioController uc = new UsuarioController();
    private ListaController lc = new ListaController();
    private AvaliacaoController ac = new AvaliacaoController();
    private Vector<Midia> midias;
    private Vector<Usuario> usuarios;
    public VBox midiasScrollPane;

    private void setButtonsMidia(Vector<Midia> m) throws SQLException {
        for (Midia midia : m) {
            Button b = new Button();
            b.setStyle("-fx-border-color: #777; -fx-border-radius: 2; -fx-background-color: #e63946; -fx-text-fill: #FCF6F5");
            b.setText(midia.getTitulo());
            b.setOnAction(actionEvent -> {
                try {
                    deletarMidia(midia);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            midiasScrollPane.getChildren().add(b);
        }
    }

    private void setButtonsUsuarios(Vector<Usuario> u) throws SQLException {
        for (Usuario usuario : u) {
            Button b = new Button();
            b.setStyle("-fx-border-color: #777; -fx-border-radius: 2; -fx-background-color: #e63946; -fx-text-fill: #FCF6F5");
            b.setText(usuario.getUsuario());
            b.setOnAction(actionEvent -> {
                try {
                    deletarUsuario(usuario);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            midiasScrollPane.getChildren().add(b);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String tipo = ApplicationController.getInstance().getDeletarTipo();
            if (tipo.equals("Midia")) {
                midias = mc.getMidias();
                setButtonsMidia(midias);
            } else {
                Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
                usuarios = uc.getUsuarios(usuario.getUsuario());
                setButtonsUsuarios(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void voltar(ActionEvent actionEvent) {
        TelasController.getInstance().mostraAdmin();
    }

    private void recarregarBotoes() throws SQLException {
        midiasScrollPane.getChildren().clear();

        String tipo = ApplicationController.getInstance().getDeletarTipo();
        if (tipo.equals("Midia")) {
            midias = mc.getMidias();
            setButtonsMidia(midias);
        } else {
            Usuario usuario = ApplicationController.getInstance().getUsuarioLogado();
            usuarios = uc.getUsuarios(usuario.getUsuario());
            setButtonsUsuarios(usuarios);
        }
    }

    private void deletarMidia(Midia midia) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja deletar a mídia?");

        ButtonType botaoDeletar = new ButtonType("Deletar");
        ButtonType botaoCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(botaoDeletar, botaoCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == botaoDeletar){
            mc.deletarMidia(midia.getId());

            recarregarBotoes();
        } else {
            System.out.println("cancelou");
        }
    }

    private void deletarUsuario(Usuario usuario) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("Tem certeza que deseja deletar o usuario?");

        ButtonType botaoDeletar = new ButtonType("Deletar");
        ButtonType botaoCancelar = new ButtonType("Cancelar");

        alert.getButtonTypes().setAll(botaoDeletar, botaoCancelar);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == botaoDeletar){
            Vector<Lista> listasUsuario = lc.getListas(usuario.getUsuario());
            Vector<Avaliacao> avaliacoesUsuario = ac.getAvaliacoes(usuario.getUsuario());

            for (Avaliacao avaliacao: avaliacoesUsuario) {
                ac.deletaAvaliacao(avaliacao.getId());
            }
            for (Lista lista : listasUsuario) {
                lc.deletarLista(lista.getId());
            }
            uc.deletarUsuario(usuario.getUsuario());

            recarregarBotoes();
        } else {
            System.out.println("cancelou");
        }
    }
}
