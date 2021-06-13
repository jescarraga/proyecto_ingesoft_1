/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Repository.SceneRepository;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLProductoController implements Initializable {

     @FXML
    private Label L_Categoria;

    @FXML
    private Label L_NProducto;

    @FXML
    private Label L_Descripcion;

    @FXML
    private TableView<?> TablaProducto;

    @FXML
    private TableColumn<?, ?> C_Tienda;

    @FXML
    private TableColumn<?, ?> C_Link;

    @FXML
    private TableColumn<?, ?> C_Precio;

    @FXML
    private Button BotonCarrito;

    @FXML
    private Button BotonReporte;

    @FXML
    private Button BotonVolver;
    // funcioon

     // eneto de los botones creados en la vista
     @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        
        if (evt.equals(BotonVolver)){
            SceneRepository.loadStage("/vista/FXMLDocument.fxml", event,
                    getClass().getResource("/vista/FXMLDocument.fxml"));
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
