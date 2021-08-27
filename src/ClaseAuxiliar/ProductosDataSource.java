package ClaseAuxiliar;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;

public class ProductosDataSource implements JRDataSource {

    private final ArrayList<infoProd> listadoproductos;
    private final String producto;
    private final String categoria;
    private final String descripcion;
    private int index;

    public ProductosDataSource(String producto,String categoria,String descripcion, ArrayList<infoProd> listadoproductos) {
        index = -1;
        this.producto = producto;
        this.categoria = categoria;
        this.descripcion = descripcion;
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
                    value = this.producto;
                    break;

                case "Categoria":
                    value = this.categoria;
                    break;

                case "Descripción":
                    value = this.descripcion;
                    break;

                case "Tienda":
                    value = listadoproductos.get(index).getTienda();
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

        public static JRDataSource getDataSource(String producto,String categoria,String descripcion, ArrayList<infoProd> listadoproductos){
            return new ProductosDataSource(producto, categoria, descripcion, listadoproductos);
        }

    }
