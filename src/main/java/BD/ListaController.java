package BD;

import exceptions.MidiaJaNaListaException;
import modelo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    private static boolean jaExiste(int listaId, int midiaId) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM \"ListaMidia\" WHERE lista_id = ? AND midia_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, listaId);
        ps.setInt(2, midiaId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int count = rs.getInt("count");
            return count > 0;
        }
        return false;
    }

    public int adicionarMidiaLista(int listaId, int midiaId) throws SQLException, MidiaJaNaListaException {
        if (jaExiste(listaId, midiaId)) {
            throw new MidiaJaNaListaException("Midia Já Existe na Lista");
        }
        String query = "Insert into \"ListaMidia\"(lista_id, midia_id) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, listaId);
        ps.setInt(2, midiaId);

        int n = ps.executeUpdate();
        return n;
    }

    public Vector<Midia> midiasDaLista(int listaId) throws SQLException {
        String selectQuery = "Select \"Midia\".*, \"ListaMidia\".id as lista_midia_id, \"Lista\".id as lista_id From \"ListaMidia\" ";
        String joinQuery = "Left Join \"Midia\" On \"ListaMidia\".midia_id = \"Midia\".id Left Join \"Lista\" On \"ListaMidia\".lista_id = \"Lista\".id\n";
        String whereQuery = "Where \"Lista\".id = ?";
        String query = selectQuery + joinQuery + whereQuery;
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, listaId);
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

    public int editarNomeLista(int listaId, String nomeLista) throws SQLException {
        String query = "Update \"Lista\" Set nome_lista = ? Where id = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nomeLista);
        ps.setInt(2, listaId);

        int n = ps.executeUpdate();
        return n;
    }

    public int deletarLista(int listaId) throws SQLException {
        String query = "Delete from \"ListaMidia\" Where lista_id = ?;\n" +
                "Delete from \"Lista\" Where id = ?\n";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, listaId);
        ps.setInt(2, listaId);

        int n = ps.executeUpdate();
        return n;
    }

    public int deletarMidiaLista(int listaId, int midiaId) throws SQLException {
        String query = "Delete From \"ListaMidia\" Where lista_id = ? AND midia_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, listaId);
        ps.setInt(2, midiaId);

        int n = ps.executeUpdate();
        return n;
    }
}
