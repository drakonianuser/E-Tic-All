package com.example.kevindrakonian.eticallv01.Entidades;

public class UsuariosEstudiantes {

    private String Nombre,Apellidos,Correo,grado,perfil="ActivityEstudiante",Documento;

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

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
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
}
