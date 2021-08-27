package ClaseAuxiliar;

import javafx.scene.control.Button;

public class productoCarrito{

    public  String nombreProducto;
    public  String link;
    public  double precio;
    public Button eliminarCarrito;

    public productoCarrito(String nombreProducto, String link, double precio) {
        this.nombreProducto = nombreProducto;
        this.link = link;
        this.precio = precio;
        this.eliminarCarrito = new Button("Eliminar del carrito");
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Button getEliminarCarrito() {
        return eliminarCarrito;
    }

    public void setEliminarCarrito(Button eliminarCarrito) {
        this.eliminarCarrito = eliminarCarrito;
    }
}
