package com.example.kevindrakonian.eticallv01.Entidades;

public class MensajeRecibirEntity extends MensajeEntity{

    private  long hora;

    public MensajeRecibirEntity() {
    }

    public MensajeRecibirEntity(long hora) {
        this.hora = hora;
    }

    public MensajeRecibirEntity(String mensaje, String foto_envia, String nombre, String foto, String type, int id_autor, long hora) {
        super(mensaje, foto_envia, nombre, foto, type, id_autor);
        this.hora = hora;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }
}
