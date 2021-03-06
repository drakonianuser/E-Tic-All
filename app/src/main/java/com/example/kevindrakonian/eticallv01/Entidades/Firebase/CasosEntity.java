package com.example.kevindrakonian.eticallv01.Entidades.Firebase;

public class CasosEntity {
    private String keyEstudiante;
    private String keyDocente;
    private String titulo;
    private String Descripcion;
    private String SalaChat;

    public CasosEntity() {
    }

    public CasosEntity(String keyEsutudinate, String keyDocente, String titulo, String descripcion, String salaChat) {
        this.keyEstudiante = keyEsutudinate;
        this.keyDocente = keyDocente;
        this.titulo = titulo;
        Descripcion = descripcion;
        SalaChat = salaChat;
    }

    public String getKeyEstudiante() {
        return keyEstudiante;
    }

    public void setKeyEstudiante(String keyEsutudinate) {
        this.keyEstudiante = keyEsutudinate;
    }

    public String getKeyDocente() {
        return keyDocente;
    }

    public void setKeyDocente(String keyDocente) {
        this.keyDocente = keyDocente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getSalaChat() {
        return SalaChat;
    }

    public void setSalaChat(String salaChat) {
        SalaChat = salaChat;
    }
}
