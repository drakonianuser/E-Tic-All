package com.example.kevindrakonian.eticallv01.Entidades;

public class MensajeRecibirEntity extends MensajeEntity{

    private  Long hora;

    public MensajeRecibirEntity() {
    }

    public MensajeRecibirEntity(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibirEntity(String mensaje, String foto_envia, String nombre, String foto, String type, int id_autor, Long hora) {
        super(mensaje, foto_envia, nombre, foto, type, id_autor);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
