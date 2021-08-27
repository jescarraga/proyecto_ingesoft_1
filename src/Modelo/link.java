package Modelo;

import javafx.scene.control.Button;

public class link {

    public int id_link;
    public int ref;
    public int id_user;
    public String link;
    public double precio;
    public String tienda;

    public Button agregarCarrito;

    public link(int id_link, int ref, String link, double precio, String tienda) {
        this.id_link = id_link;
        this.ref = ref;
        this.link = link;
        this.precio = precio;
        this.tienda = tienda;

        this.agregarCarrito = new Button("Agregar al carrito");
        this.agregarCarrito.setVisible(true);
    }


    public int getId_link() {
        return id_link;
    }

    public void setId_link(int id_link) {
        this.id_link = id_link;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double isPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public Button getAgregarCarrito() {
        return agregarCarrito;
    }

    public void setAgregarCarrito(Button agregarCarrito) {
        this.agregarCarrito = agregarCarrito;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }
}
