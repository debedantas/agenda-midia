package BD;

import modelo.Genero;
import modelo.Lista;
import modelo.Serie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListaController {
    static Connection con = BDConnection.getConnection();

    public int addLista(String usuario, String nomeLista) throws SQLException {
        String query = "Insert into \"Lista\"(usuario, nome_lista) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario);
        ps.setString(2, nomeLista);

        int n = ps.executeUpdate();
        return n;
    }

    public Vector<Lista> getListas(String usuario) throws SQLException {
        String query = "Select * From \"Lista\" Where usuario = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario);
        ResultSet rs = ps.executeQuery();
        Vector<Lista> listas = new Vector<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String nomeLista = rs.getString("nome_lista");
            Lista l = new Lista(id, nomeLista, usuario);
            listas.add(l);
        }

        return listas;
    }
}
