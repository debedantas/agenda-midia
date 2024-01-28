package BD;

import exceptions.UsuarioJaExisteException;
import modelo.Usuario;

import java.sql.SQLException;

public interface IUsuarioController {
    public Usuario getUsuario(String username) throws SQLException;
    public int addUsuario(Usuario usuario) throws SQLException, UsuarioJaExisteException;
}
