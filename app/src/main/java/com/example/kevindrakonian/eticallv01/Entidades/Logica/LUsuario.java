package com.example.kevindrakonian.eticallv01.Entidades.Logica;

import com.example.kevindrakonian.eticallv01.Entidades.Firebase.Usuarios;
import com.example.kevindrakonian.eticallv01.persistencia.UsuarioDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LUsuario {
    private Usuarios usuarios;
    private String key;

    public LUsuario(Usuarios usuarios, String key) {
        this.usuarios = usuarios;
        this.key = key;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String obtenerFechaDeCreacion(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM,yyyy", Locale.getDefault());
        Date date = new Date(UsuarioDao.getInstancia().fechaDeCreacionLong());
        return simpleDateFormat.format(date);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
