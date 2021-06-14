/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.link;
import Modelo.producto;
import Repository.ProductoRepository;
import Repository.SceneRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controller.FXMLHomeController.indiceProductoEcogido;
import static Controller.FXMLHomeController.productos;
import static Controller.LoginController.cliente;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLProductoController implements Initializable {

    ObservableList<link> datos = FXCollections.observableArrayList(productos.get(indiceProductoEcogido).getLinks());
    public static int idLinkAgregar;

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
            System.out.println("Seleccionado reporte inidividual");
        }
        
    }

    static class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {


            try {

                ProductoRepository.agregarProductoAlCarrito(cliente.getId_user(),
                        productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).getId_link());

            } catch (Exception ex) {
                Logger.getLogger(Controller.FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < productos.get(indiceProductoEcogido).getLinks().size(); i++) {
            idLinkAgregar= i;
            productos.get(indiceProductoEcogido).getLinks().get(idLinkAgregar).agregarCarrito.setOnAction(new ButtonHandler());
        }

        //creacion de la tabla con los produtos

        C_Tienda.setCellValueFactory(new PropertyValueFactory<link, String>("tienda"));
        C_Link.setCellValueFactory(new PropertyValueFactory<link, String>("link"));
        C_Precio.setCellValueFactory(new PropertyValueFactory<link, String>("precio"));
        columnaCarrito.setCellValueFactory(new PropertyValueFactory<link, String>("agregarCarrito"));
        TablaProducto.setItems(datos);
        L_Descripcion.setText(productos.get(indiceProductoEcogido).getDescripcion());
        L_NProducto.setText(productos.get(indiceProductoEcogido).getNombre());
        L_Categoria.setText(productos.get(indiceProductoEcogido).getCategoria());

    }
    
}
