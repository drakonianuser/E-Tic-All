package com.example.kevindrakonian.eticallv01.Entidades;

import java.util.Map;

public class MensajeEnviarEntity extends MensajeEntity {

    private Map hora;

    public MensajeEnviarEntity() {
    }

    public MensajeEnviarEntity(Map hora) {
        this.hora = hora;
    }

    public MensajeEnviarEntity(String mensaje, String nombre, String foto, String type, int id_autor, Map hora) {
        super(mensaje, nombre, foto, type, id_autor);
        this.hora = hora;
    }

    public MensajeEnviarEntity(String mensaje, String foto_envia, String nombre, String foto, String type, int id_autor, Map hora) {
        super(mensaje, foto_envia, nombre, foto, type, id_autor);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}
