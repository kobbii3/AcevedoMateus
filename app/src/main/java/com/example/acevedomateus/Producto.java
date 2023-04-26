package com.example.acevedomateus;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private Double precio;
    private String url;
    private String id;

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public Producto(String nombre, Double precio, String url) {
        this.nombre = nombre;
        this.precio = precio;
        this.url = url;
    }

    public Producto() {

    }

    //@Exclude
    public String getNombre() {
        return nombre;
    }
    //@Exclude

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    @PropertyName("url_image")

    public String getUrl() {
        return url;
    }
    @PropertyName("url_image")

    public void setUrl(String url) {
        this.url = url;
    }
}
