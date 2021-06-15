package Repository;

import ClaseAuxiliar.DB;
import ClaseAuxiliar.productoCarrito;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.Statement;

import static Controller.FXMLHomeController.indiceProductoEcogido;
import static Controller.FXMLHomeController.productos;
import static Controller.FXMLProductoController.idLinkAgregar;
import static Controller.FXMLcarritoController.productosAMostrar;


public class ProductoRepository {

    public static void agregarProductoAlCarrito(int ref, int ref_link){
        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //obtiene el indice mas alto en el producto

            String sqlSentencia1= "UPDATE link SET `ID_USER` = '" + ref + "' WHERE (`ID_LINK` =" +
                    " '" + ref_link + "')";

            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sqlSentencia1);


            connectionDB.close();

        }catch (Exception obtenerLink){
            obtenerLink.printStackTrace();
            obtenerLink.getCause();

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("ADVERTENCIA");
            alerta.setContentText("Error al conectar con la BD");
            alerta.showAndWait();
        }
    }

    public static void agregarPorductosAMostrar(){
        productosAMostrar.add(new productoCarrito(productos.get(indiceProductoEcogido).getNombre(),
                productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getLink(),
                productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getPrecio()));
    }

}
