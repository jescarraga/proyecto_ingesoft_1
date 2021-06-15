package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Repository.HomeRepository;
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
import javafx.scene.control.MenuButton;
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

import static Controller.LoginController.cliente;
import static Repository.HomeRepository.*;


/**
 *
 * @author ASUS
 */
public class FXMLHomeController implements Initializable {

    static public ArrayList<producto> productos = new ArrayList<producto>();
    static public ObservableList<producto> datos = FXCollections.observableArrayList(productos);
    static public int indiceProductoEcogido;
    
    @FXML
    private Button homelogout;

     @FXML
    private Button homecarrito;

    @FXML
    private MenuButton homecategoria;

    @FXML
    private TableView<producto> htabla;

    @FXML
    private TableColumn<producto, String> nombre;


    @FXML
    private  TableColumn<producto, String> categoria;


    @FXML
    private  TableColumn<producto, String> boton2;

     
    // evento de botones ya creados en la vista 
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(homelogout)){
            SceneRepository.loadStage("/vista/Login.fxml", event,
                    getClass().getResource("/vista/Login.fxml"));
            cerrarSesion();
            productos.clear();
        }

        if (evt.equals(homecarrito)){
            SceneRepository.loadStage("/vista/FXMLcarrito.fxml", event,
                    getClass().getResource("/vista/FXMLcarrito.fxml"));

            cargarProductosAlCarrito();

            for (int i = 0; i < productos.size(); i++) {
                if(evt.equals(productos.get(i).boton2)){
                    SceneRepository.loadStage("/vista/FXMLproducto.fxml", event,
                            getClass().getResource("/vista/FXMLproducto.fxml"));
                }
            }
        }
    }



    //evento de el boton ver prooducto
    public static class ButtonHandler implements EventHandler<ActionEvent>{

        public void handle(ActionEvent event) {

            try {

                for (int i = 0; i < productos.size(); i++) {
                    if (productos.get(i).boton2== event.getSource()){
                        indiceProductoEcogido = i;
                        llenadoLinks(indiceProductoEcogido,productos.get(i).getRef());
                    }
                }

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
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < productos.size(); i++) {
            productos.get(i).boton2.setOnAction(new FXMLHomeController.ButtonHandler());

        }

        //creacion de la tabla con los produtos
        ObservableList<producto> datos = FXCollections.observableArrayList(productos);
        boton2.setCellValueFactory(new PropertyValueFactory<producto, String>("boton2"));
        categoria.setCellValueFactory(new PropertyValueFactory<producto, String>("categoria"));
        nombre.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        htabla.setItems(datos);
        
    }  
    
      
    
}