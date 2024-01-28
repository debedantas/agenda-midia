package BD;

import exceptions.UsuarioJaExisteException;
import modelo.Usuario;

import java.sql.SQLException;

public interface IUsuarioController {
    Usuario getUsuario(String username) throws SQLException;
    int addUsuario(Usuario usuario) throws SQLException, UsuarioJaExisteException;
    Usuario login(String usuario, String senha) throws SQLException;
}
