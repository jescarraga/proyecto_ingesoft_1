/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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

   @FXML
    private Button BotonVolver;

    @FXML
    private TableView<producto> Htabla;

    @FXML
    private TableColumn<producto, String> C_nombre;

    @FXML
    private TableColumn<producto, String> C_categoria;

    @FXML
    private TableColumn<producto, String> C_ver;

    @FXML
    private TableColumn<producto, String> C_eliminar;

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
            loadStage("/vista/FXMLDocument.fxml", event);

        }
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        jordan.add(hola);
        jordan.add(hola2);
        
         for (int i = 0; i < jordan.size(); i++) {
           jordan.get(i).boton2.setOnAction(new ButtonHandler());
        }
   
        
        ObservableList<producto> datos = FXCollections.observableArrayList(jordan);
        C_ver.setCellValueFactory(new PropertyValueFactory<producto, String>("boton2"));
        C_eliminar.setCellValueFactory(new PropertyValueFactory<producto, String>("BotonEliminar"));
        C_categoria.setCellValueFactory(new PropertyValueFactory<producto, String>("categoria"));
        C_nombre.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        Htabla.setItems(datos);

         */
    }    
    
}
    

