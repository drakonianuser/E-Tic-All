package com.example.kevindrakonian.eticallv01.Entidades;

public class MensajeEntity {
    private String mensaje;
    private String nombre;
    private String foto;
    private String hora;
    private String type;
    private int id_autor;


    public MensajeEntity() {
    }

    public MensajeEntity(String mensaje, String nombre, String foto, String hora, String type, int id_autor) {
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.foto = foto;
        this.hora = hora;
        this.type = type;
        this.id_autor = id_autor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}
