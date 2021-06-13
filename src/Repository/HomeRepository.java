package Repository;

import Controller.FXMLHomeController;
import Controller.FXMLcarritoController;
import DataBase.DB;
import Modelo.producto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Controller.FXMLHomeController.datos;
import static Controller.FXMLHomeController.productos;
import static Controller.LoginController.cliente;

public class HomeRepository {

    public static void cerrarSesion(){

        try {

            cliente.setBlank();

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //obtiene el indice mas alto en el producto

            String sqlSentencia1= "SELECT * FROM productos ORDER BY ref DESC LIMIT 1";

            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia1);

            resultadoSQL.next();

            int indice = resultadoSQL.getInt(1);

            for (int i = 1; i <= indice; i++) {
                sqlSentencia1 = "UPDATE `prueba_proyecto`.`productos` SET `ID_USER` = '1' WHERE (`REF` =" +
                        " '" + String.valueOf(i) + "')";

                statement.executeUpdate(sqlSentencia1);
            }

            connectionDB.close();

        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }
    }

    public static void productoListaCategoria(String categoria){

        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //SQL sentencia a ejecutar
            String sqlSentencia = "SELECT * from productos where categoria = ´" + categoria +"´";

            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            int i = 0;
            while (resultadoSQL.next()) {
                productos.add(new producto(resultadoSQL.getString(3),
                        resultadoSQL.getString(5)));

                System.out.println(productos.get(i).getCategoria());
                i++;
            }

            connectionDB.close();

        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("ADVERTENCIA");
            alerta.setContentText("Error al conectar con la BD");
            alerta.showAndWait();
        }

    }




}
