/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.control.Button;

/**
 *
 * @author ASUS
 */
public class producto {
    
    public String nombre;
    public String categoria;
    public Button boton1;
    public Button boton2;
    public Button BotonEliminar;
    

    public producto(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.boton1 = new Button ("añadir al carro");
        this.boton2 = new Button ("ver producto");
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

    public Button getBoton1() {
        return boton1;
    }

    public void setBoton1(Button boton1) {
        this.boton1 = boton1;
    }

    public Button getBoton2() {
        return boton2;
    }

    public void setBoton2(Button boton2) {
        this.boton2 = boton2;
    }
    
    
    
    
    
    
    
    
    
}
