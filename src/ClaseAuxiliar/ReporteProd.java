package ClaseAuxiliar;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.util.ArrayList;

import static Controller.FXMLHomeController.indiceProductoEcogido;
import static Controller.FXMLHomeController.productos;

public class ReporteProd {
    public ReporteProd(){
        String nombre = productos.get(indiceProductoEcogido).getNombre();
        String categoria = productos.get(indiceProductoEcogido).getCategoria();
        String descripcion = productos.get(indiceProductoEcogido).getDescripcion();
        ArrayList<infoProd> links = new ArrayList<>();

        for (int i = 0; i < productos.get(indiceProductoEcogido).getLinks().size(); i++) {
            links.add(new infoProd(productos.get(indiceProductoEcogido).getLinks().get(i).getLink(), productos.get(indiceProductoEcogido).getLinks().get(i).getPrecio(), productos.get(indiceProductoEcogido).getLinks().get(i).getTienda()));
        }
        try{

            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/Prueba_Reporte.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report,null, ProductosDataSource.getDataSource(nombre,categoria,descripcion,links));

            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        }catch(JRException ex){
            ex.getMessage();
        }
    }
}
