package BD;

import modelo.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

public class AvaliacaoController {
    private static String joinQuery =
            "Select \"Avaliacao\".*, \"Midia\".titulo From \"Avaliacao\" Left Join \"Midia\" On \"Avaliacao\".midia_id = \"Midia\".id ";
    static Connection con = BDConnection.getConnection();

    public int addAvaliacao(String usuario, int midiaId, String comentario, double nota) throws SQLException {
        String query = "Insert into \"Avaliacao\"(comentario, nota, usuario, midia_id) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, comentario);
        ps.setDouble(2, nota);
        ps.setString(3, usuario);
        ps.setInt(4, midiaId);

        int n = ps.executeUpdate();
        return n;
    }

    public Avaliacao getAvaliacao(int avaliacaoId) throws SQLException {
        String query = joinQuery + "Where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, avaliacaoId);
        ResultSet rs = ps.executeQuery();
        Avaliacao avaliacao = null;

        while(rs.next()) {
            int id = rs.getInt("id");
            LocalDate createdAt = rs.getTimestamp("created_at").toLocalDateTime().toLocalDate();
            String comentario = rs.getString("comentario");
            double nota = rs.getDouble("nota");
            String usuario = rs.getString("usuario");
            String midiaTitulo = rs.getString("titulo");
            avaliacao = new Avaliacao(id, comentario, nota, createdAt, usuario, midiaTitulo);
        }

        return avaliacao;
    }

    public Vector<Avaliacao> getAvaliacoes(int midiaId) throws SQLException {
        String query = joinQuery + "Where midia_id = ? Order By created_at";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, midiaId);
        ResultSet rs = ps.executeQuery();
        Vector<Avaliacao> avaliacoes = new Vector<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            LocalDate createdAt = rs.getTimestamp("created_at").toLocalDateTime().toLocalDate();
            String comentario = rs.getString("comentario");
            double nota = rs.getDouble("nota");
            String usuario = rs.getString("usuario");
            String midiaTitulo = rs.getString("titulo");
            Avaliacao a = new Avaliacao(id, comentario, nota, createdAt, usuario, midiaTitulo);
            avaliacoes.add(a);
        }
        return avaliacoes;
    }

    public Vector<Avaliacao> getAvaliacoes(String usuario) throws SQLException {
        String query = joinQuery + "Where usuario = ? Order By created_at";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        Vector<Avaliacao> avaliacoes = new Vector<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            LocalDate createdAt = rs.getTimestamp("created_at").toLocalDateTime().toLocalDate();
            String comentario = rs.getString("comentario");
            double nota = rs.getDouble("nota");
            String midiaTitulo = rs.getString("titulo");
            Avaliacao a = new Avaliacao(id, comentario, nota, createdAt, usuario, midiaTitulo);
            avaliacoes.add(a);
        }
        return avaliacoes;
    }

    public Avaliacao atualizaAvaliacao(int avaliacaoId, String comentario, double nota) throws SQLException {
        String updateQuery = "Update \"Avaliacao\" Set comentario = ?, nota = ? ";
        String fromQuery = "From \"Midia\" Where \"Avaliacao\".id = ? And \"Avaliacao\".midia_id = \"Midia\".id ";
        String returnQuery = "Returning \"Avaliacao\".*, \"Midia\".titulo";
        String query = updateQuery + fromQuery + returnQuery;

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, comentario);
        ps.setDouble(2, nota);
        ps.setInt(3, avaliacaoId);

        ResultSet rs = ps.executeQuery();
        Avaliacao avaliacao = null;

        while(rs.next()) {
            int id = rs.getInt("id");
            LocalDate createdAt = rs.getTimestamp("created_at").toLocalDateTime().toLocalDate();
            String comentarioAtualizado = rs.getString("comentario");
            double notaAtualizada = rs.getDouble("nota");
            String usuario = rs.getString("usuario");
            String midiaTitulo = rs.getString("titulo");
            avaliacao = new Avaliacao(id, comentarioAtualizado, notaAtualizada, createdAt, usuario, midiaTitulo);
        }

        return avaliacao;
    }

    public int deletaAvaliacao(int avaliacaoId) throws SQLException {
        String query = "Delete From \"Avaliacao\" Where id = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, avaliacaoId);

        int n = ps.executeUpdate();
        return n;
    }
}
