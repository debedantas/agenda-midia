package modelo;

import java.time.LocalDate;

public class Avaliacao {
    private String comentario;
    private float nota;
    private LocalDate createdAt;
    private IUsuario usuario;

    public Avaliacao(String comentario, float nota, LocalDate createdAt, IUsuario usuario) {
        this.comentario = comentario;
        this.nota = nota;
        this.createdAt = createdAt;
        this.usuario = usuario;
    }

    // CRUD();

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public IUsuario getUsuario() {
        return usuario;
    }
}
