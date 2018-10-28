package com.example.kevindrakonian.eticallv01.Entidades.Logica;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.MensajeEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LMensaje {

    private String key;
    private MensajeEntity mensajeEntity;
    private LUsuario lUsuario;

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

    public LUsuario getlUsuario() {
        return lUsuario;
    }

    public void setlUsuario(LUsuario lUsuario) {
        this.lUsuario = lUsuario;
    }


    public String FechaEnvioMensaje(){
        Date date = new Date(getCeatedTimestampLong());
        SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss a",Locale.getDefault());
        return hora.format(date);
    }

}
