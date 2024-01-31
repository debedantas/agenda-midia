package BD;

import modelo.Filme;
import modelo.Genero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
