/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClaseAuxiliar.ProductosDataSource;
import ClaseAuxiliar.ReporteProd;
import ClaseAuxiliar.infoProd;
import Modelo.link;
import Repository.ProductoRepository;
import Repository.SceneRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controller.FXMLHomeController.indiceProductoEcogido;
import static Controller.FXMLHomeController.productos;
import static Controller.LoginController.cliente;
import static Repository.ProductoRepository.agregarPorductosAMostrar;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLProductoController implements Initializable {

    ObservableList<link> datos = FXCollections.observableArrayList(productos.get(indiceProductoEcogido).getLinks());
    public static int idLinkAgregar;
    public static ArrayList<Integer> indicesProductosCarrito = new ArrayList<>();

     @FXML
    private Label L_Categoria;

    @FXML
    private Label L_NProducto;

    @FXML
    private Label L_Descripcion;

    @FXML
    private TableView<link> TablaProducto;

    @FXML
    private TableColumn<link, String> C_Tienda;

    @FXML
    private TableColumn<link, String> C_Link;

    @FXML
    private TableColumn<link, String> C_Precio;

    @FXML
    private Button BotonReporte;

    @FXML
    private Button BotonVolver;

    @FXML
    private TableColumn<link, String> columnaCarrito;



    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        
        if (evt.equals(BotonVolver)){
            SceneRepository.loadStage("/vista/FXMLDocument.fxml", event,
                    getClass().getResource("/vista/FXMLDocument.fxml"));
        }

        if (evt.equals(BotonReporte)){

            ReporteProd reporteProd = new ReporteProd();
        }
        
    }

    static class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {


            try {
                int x = 0;
                for (int j = 0; j < productos.get(indiceProductoEcogido).getLinks().size(); j++) {
                    if(productos.get(indiceProductoEcogido).getLinks().get(j).getAgregarCarrito()== event.getSource() && productos.get(indiceProductoEcogido).getLinks().get(j).getAgregarCarrito().getText().equals("Producto ya agregado")){
                        System.out.println("mal");
                        x = 1;

                        break;
                    }else {


                        for (int i = 0; i < productos.get(indiceProductoEcogido).getLinks().size(); i++) {
                            if (productos.get(indiceProductoEcogido).getLinks().get(i).getAgregarCarrito() == event.getSource() && x == 0) {
                                System.out.println(x);
                                //productos.get(indiceProductoEcogido).getLinks().get(i).getAgregarCarrito().setText("Producto ya agregado");
                                productos.get(indiceProductoEcogido).getLinks().get(i).getAgregarCarrito().setVisible(false);
                                idLinkAgregar = i;
                            }
                        }

                        if (x == 0) {
                            ProductoRepository.agregarProductoAlCarrito(cliente.getId_user(),
                                    productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getId_link());

                            indicesProductosCarrito.add(indiceProductoEcogido);
                        }
                        for (int i = 0; i < productos.get(indiceProductoEcogido).getLinks().size(); i++) {
                            if (productos.get(indiceProductoEcogido).getLinks().get(i).getAgregarCarrito() == event.getSource() && x == 0) {
                                productos.get(indiceProductoEcogido).getLinks().get(i).setId_user(cliente.getId_user());
                            }
                        }


                    }
                }

                if(x == 0) {
                    agregarPorductosAMostrar();
                }

            } catch (Exception ex) {
                Logger.getLogger(Controller.FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < productos.get(indiceProductoEcogido).getLinks().size(); i++) {

                System.out.println("hola");

                productos.get(indiceProductoEcogido).getLinks().get(i).agregarCarrito.setOnAction(new ButtonHandler());
                columnaCarrito.setCellValueFactory(new PropertyValueFactory<link, String>("agregarCarrito"));


        }

        //creacion de la tabla con los produtos

        C_Tienda.setCellValueFactory(new PropertyValueFactory<link, String>("tienda"));
        C_Link.setCellValueFactory(new PropertyValueFactory<link, String>("link"));
        C_Precio.setCellValueFactory(new PropertyValueFactory<link, String>("precio"));

        TablaProducto.setItems(datos);
        L_Descripcion.setText(productos.get(indiceProductoEcogido).getDescripcion());
        L_NProducto.setText(productos.get(indiceProductoEcogido).getNombre());
        L_Categoria.setText(productos.get(indiceProductoEcogido).getCategoria());

    }
    
}
