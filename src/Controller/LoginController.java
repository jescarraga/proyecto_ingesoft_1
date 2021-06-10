package Controller;


import Modelo.DB;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import Modelo.usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class LoginController {

    public boolean verficarUsuarioBD(String email, String contraseña) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "SELECT count(1) from user where email = '" + email + "' and password = '" + contraseña + "'";

        //variable a retornar
        boolean exito = false;
        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                if (resultadoSQL.getInt(1) == 1) {
                    exito = true;
                }else{
                    exito = false;
                }
            }
        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }

        return exito;
    }



    @FXML
    private TextField emailUsuario;
    @FXML
    private TextField passwordUsuario;

    @FXML
    private Button loginBoton;


    public void loginButtonAction(ActionEvent event){
        Boolean respuesta = verficarUsuarioBD(emailUsuario.getText(),passwordUsuario.getText());
        if (respuesta == true) {
            System.out.println("correcto");
        }else if (respuesta == false){
            System.out.println("usuario no encontrado");
        }
    }
}

