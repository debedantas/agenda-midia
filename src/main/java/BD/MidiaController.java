package BD;

import modelo.Filme;
import modelo.Genero;
import modelo.Midia;
import modelo.Serie;

import java.sql.*;
import java.util.Vector;

public class MidiaController {
    static Connection con = BDConnection.getConnection();

    public Midia getMidia(int midiaId) throws SQLException {
        String query = "Select * From \"Midia\" Where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, midiaId);
        ResultSet rs = ps.executeQuery();
        Midia midia = null;

        while(rs.next()) {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int ano = rs.getInt("ano");
            Genero genero = Genero.valueOf(rs.getString("genero"));
            int duracao = rs.getInt("duracao");
            int numEpisodios = rs.getInt("numEpisodios");
            if (rs.getString("tipo").equals("Filme")) {
                midia = new Filme(id, titulo, ano, genero, duracao);
            } else {
                midia = new Serie(id, titulo, ano, genero, numEpisodios);
            }
        }

        return midia;
    }

    public Vector<Midia> getMidias(String tipo) throws SQLException {
        String query = "Select * From \"Midia\" Where tipo = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, tipo);
        ResultSet rs = ps.executeQuery();
        Vector<Midia> midias = new Vector<>();

        while(rs.next()) {
            Midia midia = null;
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int ano = rs.getInt("ano");
            Genero genero = Genero.valueOf(rs.getString("genero"));
            int duracao = rs.getInt("duracao");
            int numEpisodios = rs.getInt("numEpisodios");
            if (rs.getString("tipo").equals("Filme")) {
                midia = new Filme(id, titulo, ano, genero, duracao);
            } else {
                midia = new Serie(id, titulo, ano, genero, numEpisodios);
            }
            midias.add(midia);
        }

        return midias;
    }
}
