package com.example.kevindrakonian.eticallv01.Entidades.Logica;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.MensajeEntity;

public class LMensaje {

    private String key;
    private MensajeEntity mensajeEntity;

    public LMensaje(String key, MensajeEntity mensajeEntity) {
        this.key = key;
        this.mensajeEntity = mensajeEntity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MensajeEntity getMensajeEntity() {
        return mensajeEntity;
    }

    public void setMensajeEntity(MensajeEntity mensajeEntity) {
        this.mensajeEntity = mensajeEntity;
    }

    public long getCeatedTimestampLong(){
        return (long) mensajeEntity.getCreatedTimestamp();
    }
}
