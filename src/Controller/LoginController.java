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
        String sqlSentencia = "SELECT * from lizzard.user where email = '" + email + "' and password = '" + contraseña + "'";

        //variable a retornar
        boolean exito = false;
        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                if (resultadoSQL.getInt(1) == 1) {
                    exito = true;
                    String var1 = resultadoSQL.getString(2);
                    String var2 = resultadoSQL.getString(3);
                    String var3 = resultadoSQL.getString(4);
                    String var4 = resultadoSQL.getString(5);
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

    public ArrayList<String> crearUsuario(String email){
        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "SELECT * from lizzard.user where email = '" + email + "'";

        //datos
        ArrayList<String> data = new ArrayList<String>();
        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                    String var1 = resultadoSQL.getString(2);
                    String var2 = resultadoSQL.getString(3);
                    String var3 = resultadoSQL.getString(4);
                    data.add(var1);
                    data.add(var2);
                    data.add(var3);
            }
        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }
        return data;
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
            ArrayList<String> data = new ArrayList<String>();
            data = crearUsuario(emailUsuario.getText());
            usuario user = new usuario(data.get(0), data.get(1), data.get(2));
            user.createCSV();
            user = null;
            System.out.println("correcto");
        }else if (respuesta == false){
            System.out.println("usuario no encontrado");
        }
    }
}

