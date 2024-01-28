package modelo;

import java.util.Vector;

public interface IUsuario {
    // CRUD();
    Vector<Lista> getListas();

    Lista getLista(String nomeDaLista);
}
