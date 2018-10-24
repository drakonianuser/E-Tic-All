package com.example.kevindrakonian.eticallv01.Entidades;

public class FiltroDocenteEntity {

    private String Nombre;
    private String Departamento;
    private String Unidad;
    private String UrlImagen;

    public FiltroDocenteEntity() {
    }

    public FiltroDocenteEntity(String nombre, String departamento, String unidad, String urlImagen) {
        Nombre = nombre;
        Departamento = departamento;
        Unidad = unidad;
        UrlImagen = urlImagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getUnidad() {
        return Unidad;
    }

    public void setUnidad(String unidad) {
        Unidad = unidad;
    }

    public String getUrlImagen() {
        return UrlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        UrlImagen = urlImagen;
    }
}
