package ClaseAuxiliar;

public class infoProd {
    public String link;
    public String precio;
    public String tienda;

    public infoProd(String link, double precio, String tienda) {
        this.link = link;
        this.precio = String.valueOf(precio);
        this.tienda = tienda;
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

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }
}
