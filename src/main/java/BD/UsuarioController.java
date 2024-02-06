package BD;

import exceptions.UsuarioJaExisteException;
import modelo.TipoUsuario;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioController implements IUsuarioController {
    static Connection con = BDConnection.getConnection();

    @Override
    public Usuario getUsuario(String username) throws SQLException {
        String query = "Select * From \"Users\" Where usuario = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        Usuario user = new Usuario();

        while(rs.next()) {
            check = true;
            user.setUsuario(rs.getString("usuario"));
            user.setSenha(rs.getString("senha"));
            user.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
        }
        if (!check) {
            return null;
        }
        return user;
    }

    // Retorna todos os usuários menos o seu
    public Vector<Usuario> getUsuarios(String username) throws SQLException {
        String query = "Select * From \"Users\" Where not usuario = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        Vector<Usuario> users = new Vector<>();

        while(rs.next()) {
            Usuario user = new Usuario();
            user.setUsuario(rs.getString("usuario"));
            user.setSenha(rs.getString("senha"));
            user.setTipo(TipoUsuario.valueOf(rs.getString("tipo")));
            users.add(user);
        }

        return users;
    }

    @Override
    public int addUsuario(Usuario usuario) throws SQLException, UsuarioJaExisteException {
        if (usuario == null || getUsuario(usuario.getUsuario()) != null) {
            throw new UsuarioJaExisteException("Usuario Já existe");
        }

        String query = "Insert into \"Users\"(usuario, senha, tipo) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario.getUsuario());
        ps.setString(2, usuario.getSenha());
        ps.setString(3, usuario.getTipo().name());

        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public Usuario login(String usuario, String senha) throws SQLException {
        Usuario u = getUsuario(usuario);
        if (u == null || !senha.equals(u.getSenha())) return null;

        return u;
    }

    public int deletarUsuario(String usuario) throws SQLException {
        String query = "Delete from \"Users\" Where usuario = ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, usuario);

        int n = ps.executeUpdate();
        return n;
    }

    public static void main(String[] args) {
        try {
            UsuarioController uc = new UsuarioController();
            Usuario u1 = uc.getUsuario("user1");

            System.out.println(u1.getUsuario() + ", " + u1.getSenha() + ", " + u1.getTipo());
            Usuario novoUser = new Usuario("user2", "senha2", TipoUsuario.Padrao);

            int n = uc.addUsuario(novoUser);
            System.out.println(n);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UsuarioJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }
}
