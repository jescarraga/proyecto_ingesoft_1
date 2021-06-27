package ClaseAuxiliar;

public class infoCarrito {
    public String link;
    public String precio;
    public String producto;

    public infoCarrito(String link, double precio, String producto) {
        this.link = link;
        this.precio = String.valueOf(precio);
        this.producto = producto;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
