package Repository;

import ClaseAuxiliar.DB;
import ClaseAuxiliar.ProductosDataSource;
import ClaseAuxiliar.productoCarrito;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
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

    /*public static void ReporteJasper(){
        try{

            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/Prueba_Reporte.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report,null, ProductosDataSource.getDataSource("Monitor","periferico","pantalla lcd"));

            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        }catch(JRException ex){
            ex.getMessage();
        }
    }*/

    public static void agregarPorductosAMostrar(){
        productosAMostrar.add(new productoCarrito(productos.get(indiceProductoEcogido).getNombre(),
                productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getLink(),
                productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getPrecio()));
    }

}
