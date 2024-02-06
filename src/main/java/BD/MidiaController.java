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

    public Vector<Midia> getMidias() throws SQLException {
        String query = "Select * From \"Midia\" Order By id";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
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

    public Vector<Midia> getMidias(String tipo) throws SQLException {
        String query = "Select * From \"Midia\" Where tipo = ? Order By id";
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

    public int adicionarFilme(String titulo, int ano, Genero genero, int duracao) throws SQLException {
        String query = "Insert into \"Midia\"(titulo, ano, genero, tipo, duracao) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, titulo);
        ps.setInt(2, ano);
        ps.setString(3, genero.name());
        ps.setString(4, "Filme");
        ps.setInt(5, duracao);

        int n = ps.executeUpdate();
        return n;
    }

    public int adicionarSerie(String titulo, int ano, Genero genero, int numEpisodios) throws SQLException {
        String query = "Insert into \"Midia\"(titulo, ano, genero, tipo, numEpisodios) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, titulo);
        ps.setInt(2, ano);
        ps.setString(3, genero.name());
        ps.setString(4, "Serie");
        ps.setInt(5, numEpisodios);

        int n = ps.executeUpdate();
        return n;
    }

    public int deletarMidia(int midiaId) throws SQLException {
        String query = "Delete from \"ListaMidia\" Where midia_id = ?;\n" +
                "Delete from \"Avaliacao\" Where midia_id = ?;" +
                "Delete from \"Midia\" Where id = ?\n";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, midiaId);
        ps.setInt(2, midiaId);
        ps.setInt(3, midiaId);

        int n = ps.executeUpdate();
        return n;
    }
}
