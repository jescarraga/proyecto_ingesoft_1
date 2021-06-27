package ClaseAuxiliar;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;

public class CarritoDataSource implements JRDataSource{
    private final ArrayList<infoCarrito> listadoproductos;
    private int index;

    public CarritoDataSource(ArrayList<infoCarrito> listadoproductos) {
        index = -1;
        this.listadoproductos = listadoproductos;
    }

    @Override
    public boolean next() throws JRException {
        index++;
        return (index < listadoproductos.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException{

        Object value = null;

        String fieldname = jrf.getName();

        switch(fieldname){

            case "Producto":
                value = listadoproductos.get(index).getProducto();
                break;

            case "Enlace":
                value = listadoproductos.get(index).getLink();
                break;

            case "Precio":
                value = listadoproductos.get(index).getPrecio();
                break;
        }

        return value;
    }

    public static JRDataSource getDataSource(ArrayList<infoCarrito> listadoproductos){
        return new CarritoDataSource(listadoproductos);
    }

}
