package com.example.kevindrakonian.eticallv01.Entidades.Firebase;

import com.google.firebase.database.ServerValue;

public class MensajeEntity {

    private String mensaje;
    private String  UrlFoto;
    private boolean EnviaFoto;
    private String keyEmisor;
    private Object createdTimestamp;

    public MensajeEntity() {
        createdTimestamp = ServerValue.TIMESTAMP;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlFoto() {
        return UrlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        UrlFoto = urlFoto;
    }

    public boolean isEnviaFoto() {
        return EnviaFoto;
    }

    public void setEnviaFoto(boolean enviaFoto) {
        EnviaFoto = enviaFoto;
    }

    public String getKeyEmisor() {
        return keyEmisor;
    }

    public void setKeyEmisor(String keyEmisor) {
        this.keyEmisor = keyEmisor;
    }

    public Object getCreatedTimestamp() {
        return createdTimestamp;
    }
}
