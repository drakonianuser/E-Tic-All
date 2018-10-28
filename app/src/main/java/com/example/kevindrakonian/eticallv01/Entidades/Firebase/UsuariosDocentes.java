package com.example.kevindrakonian.eticallv01.Entidades.Firebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

public class UsuariosDocentes {
    private String Nombre,Apellidos,Correo,Unidad,Departamento,Documento,Perfil="Directivo",UrlFotoPerfil;
    private Object createdTimestamp;


    public UsuariosDocentes() {
        createdTimestamp = ServerValue.TIMESTAMP;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String perfil) {
        Perfil = perfil;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String unidad) {
        Unidad = unidad;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public String getUrlFotoPerfil() {
        return UrlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        UrlFotoPerfil = urlFotoPerfil;
    }

    public Object getCreatedTimestamp() {
        return createdTimestamp;
    }
    @Exclude
    public long getCreatedTimesTampLong(){
        return (long)createdTimestamp;
    }

}
