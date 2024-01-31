package BD;

import modelo.Filme;
import modelo.Genero;
import modelo.Serie;

import java.sql.*;
import java.util.Vector;

public class MidiaController {
    static Connection con = BDConnection.getConnection();

    public Filme getFilme(int filmeId) throws SQLException {
        String query = "Select * From \"Midia\" Where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, filmeId);
        ResultSet rs = ps.executeQuery();
        Filme filme = null;

        while(rs.next()) {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int ano = rs.getInt("ano");
            Genero genero = Genero.valueOf(rs.getString("genero"));
            int duracao = rs.getInt("duracao");
            filme = new Filme(id, titulo, ano, genero, duracao);
        }

        return filme;
    }
    public Vector<Filme> getFilmes() throws SQLException {
        String query = "Select * From \"Midia\" Where tipo = 'Filme'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Vector<Filme> filmes = new Vector<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int ano = rs.getInt("ano");
            Genero genero = Genero.valueOf(rs.getString("genero"));
            int duracao = rs.getInt("duracao");
            Filme f = new Filme(id, titulo, ano, genero, duracao);
            filmes.add(f);
        }
        return filmes;
    }

    public Vector<Serie> getSeries() throws SQLException {
        String query = "Select * From \"Midia\" Where tipo = 'Serie'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Vector<Serie> series = new Vector<>();

        while(rs.next()) {
            int id = rs.getInt("id");
            String titulo = rs.getString("titulo");
            int ano = rs.getInt("ano");
            Genero genero = Genero.valueOf(rs.getString("genero"));
            int numEpisodios = rs.getInt("numEpisodios");
            Serie s = new Serie(id, titulo, ano, genero, numEpisodios);
            series.add(s);
        }
        return series;
    }
}
