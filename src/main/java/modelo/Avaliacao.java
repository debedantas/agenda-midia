package modelo;

import java.time.LocalDate;

public class Avaliacao {
    private int id;
    private String comentario;
    private double nota;
    private LocalDate createdAt;
    private String usuario;
    private int midiaId;

    public Avaliacao(int id, String comentario, double nota, LocalDate createdAt, String usuario, int midiaId) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.createdAt = createdAt;
        this.usuario = usuario;
        this.midiaId = midiaId;
    }

    // CRUD();

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getUsuario() {
        return usuario;
    }
}
