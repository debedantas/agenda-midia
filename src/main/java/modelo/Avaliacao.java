package modelo;

import java.time.LocalDate;

public class Avaliacao {
    private int id;
    private String comentario;
    private double nota;
    private LocalDate createdAt;
    private String usuario;
    private String midiaTitulo;

    public Avaliacao(int id, String comentario, double nota, LocalDate createdAt, String usuario, String midiaTitutlo) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.createdAt = createdAt;
        this.usuario = usuario;
        this.midiaTitulo = midiaTitutlo;
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

    public String getMidiaTitulo() {
        return midiaTitulo;
    }

    public void setMidiTitulo(String midiaTitulo) {
        this.midiaTitulo = midiaTitulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
