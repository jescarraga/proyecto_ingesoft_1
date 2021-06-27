package ClaseAuxiliar;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.util.ArrayList;

import static Controller.FXMLcarritoController.productosAMostrar;

public class ReporteCarrito {
    public ReporteCarrito(){
        
        ArrayList<infoCarrito> info = new ArrayList<>();

        for (int i = 0; i < productosAMostrar.size(); i++) {
            info.add(new infoCarrito(productosAMostrar.get(i).getLink(), productosAMostrar.get(i).getPrecio(), productosAMostrar.get(i).getNombreProducto()));
        }
        try{

            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/Prueba_Reporte2.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(report,null, CarritoDataSource.getDataSource(info));

            JasperViewer view = new JasperViewer(jprint,false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setVisible(true);

        }catch(JRException ex){
            ex.getMessage();
        }
    }
}
