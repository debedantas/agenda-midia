package BD;

import modelo.Avaliacao;
import modelo.Filme;
import modelo.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class AvaliacaoController {
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

    public Vector<Avaliacao> getAvaliacoes(int midiaId) throws SQLException {
        String query = "Select * From \"Avaliacao\" Where midia_id = ?";
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
            Avaliacao a = new Avaliacao(id, comentario, nota, createdAt, usuario, midiaId);
            avaliacoes.add(a);
        }
        return avaliacoes;
    }
}
