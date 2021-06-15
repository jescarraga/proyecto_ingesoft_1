/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClaseAuxiliar.productoCarrito;
import Modelo.link;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import Modelo.producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLcarritoController implements Initializable {

    static public ArrayList<producto> productosCarrito = new ArrayList<producto>();
    static public ArrayList<productoCarrito> productosAMostrar = new ArrayList<>();
    ObservableList<productoCarrito> datosCarrito = FXCollections.observableArrayList(productosAMostrar);

   @FXML
    private Button BotonVolver;

    @FXML
    private TableView<productoCarrito> Htabla;

    @FXML
    private TableColumn<productoCarrito, String> C_nombre;

    @FXML
    private TableColumn<productoCarrito, String> Link;

    @FXML
    private TableColumn<productoCarrito, Double> Precio;


    @FXML
    private TableColumn<productoCarrito, String> C_eliminar;

    @FXML
    private Button BotonReporte;

    
     private void loadStage(String url, Event event){
            
        try {
            
    
           //((Node)(event.getSource())).getScene().getWindow().hide();    
            
            
            Object eventSource = event.getSource(); 
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();
            
           
                        
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);              
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();  
                                    
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });  

        } catch (IOException ex) {
            Logger.getLogger(Controller.FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
     
     static class ButtonHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            
            try {
    
           //((Node)(event.getSource())).getScene().getWindow().hide();    
            
            
            Object eventSource = event.getSource(); 
            Node sourceAsNode = (Node) eventSource ;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window ;
            stage.hide();
            
           
                        
            Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLproducto.fxml"));
            Scene scene = new Scene(root);              
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();  
                                    
            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });  

        } catch (IOException ex) {
            Logger.getLogger(Controller.FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
     
     @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();

         if (evt.equals(BotonVolver)){
             SceneRepository.loadStage("/vista/FXMLDocument.fxml", event,
                     getClass().getResource("/vista/FXMLDocument.fxml"));
         }

         if (evt.equals(BotonReporte)){
             System.out.println("Reporte activo");
         }
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         for (int i = 0; i < productosAMostrar.size(); i++) {
             productosAMostrar.get(i).eliminarCarrito.setOnAction(new ButtonHandler());
         }

         C_nombre.setCellValueFactory(new PropertyValueFactory<productoCarrito, String>("nombreProducto"));
         Link.setCellValueFactory(new PropertyValueFactory<productoCarrito, String>("link"));
         Precio.setCellValueFactory(new PropertyValueFactory<productoCarrito, Double>("precio"));
         C_eliminar.setCellValueFactory(new PropertyValueFactory<productoCarrito, String>("eliminarCarrito"));

         Htabla.setItems(datosCarrito);


    }    
    
}
    

