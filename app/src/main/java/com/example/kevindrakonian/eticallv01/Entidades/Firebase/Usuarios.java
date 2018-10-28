package com.example.kevindrakonian.eticallv01.Entidades.Firebase;

import com.google.firebase.database.Exclude;

public class Usuarios {
    private String Nombre,Apellidos,Correo,grado,Unidad,Departamento,Documento,Perfil,UrlFotoPerfil;
    private Object createdTimestamp;
    public String getNombre() {
        return Nombre;
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

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
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

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String perfil) {
        Perfil = perfil;
    }

    public Object getCreatedTimestamp() {
        return createdTimestamp;
    }
    @Exclude
    public long getCreatedTimesTampLong(){
        return (long)createdTimestamp;
    }

    public String getUrlFotoPerfil() {
        return UrlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        UrlFotoPerfil = urlFotoPerfil;
    }
}
