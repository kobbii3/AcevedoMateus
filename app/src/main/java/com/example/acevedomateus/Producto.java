package com.example.acevedomateus;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private Double precio;
    private String url;

    public Producto(String nombre, Double precio, String url) {
        this.nombre = nombre;
        this.precio = precio;
        this.url = url;
    }

    public Producto() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
