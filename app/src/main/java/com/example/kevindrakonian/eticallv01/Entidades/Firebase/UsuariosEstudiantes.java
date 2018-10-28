package com.example.kevindrakonian.eticallv01.Entidades.Firebase;

import com.google.firebase.database.Exclude;

public class UsuariosEstudiantes {

    private String Nombre,Apellidos,Correo,grado,perfil="Estudiante",UrlFotoPerfil;
    private Object createdTimestamp;

    public UsuariosEstudiantes() {
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

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

    public String getPerfil() {
        return perfil;
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
