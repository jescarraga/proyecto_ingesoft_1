/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class producto {

    public int ref;
    public String nombre;
    public String descripcion;
    public String categoria;

    public ArrayList<link> links;


    public Button boton2;
    public Button BotonEliminar;


    public producto(int ref, String nombre, String descripcion, String categoria) {
        this.ref = ref;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.boton2 = new Button ("ver producto");;
        this.BotonEliminar = new Button ("Eliminar Producto");
    }

    public Button getBotonEliminar() {
        return BotonEliminar;
    }

    public void setBotonEliminar(Button BotonEliminar) {
        this.BotonEliminar = BotonEliminar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Button getBoton2() {
        return boton2;
    }

    public void setBoton2(Button boton2) {
        this.boton2 = boton2;
    }

    public ArrayList<link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<link> links) {
        this.links = links;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
