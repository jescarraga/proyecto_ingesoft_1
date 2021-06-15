package Repository;

import ClaseAuxiliar.DB;
import Modelo.usuario;
import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginRepository {


    public static boolean verficarUsuarioBD(String email, String password) {

        boolean exito = false;

        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //SQL sentencia a ejecutar
            String sqlSentencia = "SELECT count(1) from user where email = '" + email +
                    "' and password = '" + password + "'";

            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                if (resultadoSQL.getInt(1) == 1) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("EXCELENTE");
                    alerta.setContentText("Ingreso exitoso a la aplicación");
                    alerta.showAndWait();
                    exito = true;

                }else{
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("ADVERTENCIA");
                    alerta.setContentText("El correo y la contraseña no se encuentran en la base de datos");
                    alerta.showAndWait();
                    exito = false;
                }
            }
            connectionDB.close();
        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }

        return exito;

    }

    public static void instanciarUsuario(String email, usuario cliente){
        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //SQL sentencia a ejecutar
            String sqlSentencia = "SELECT * from user where email = '" + email + "'";

            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            //leo el primer y unico resultado
            resultadoSQL.next();

            //Establezco valores a los atributos de mi usuario
            cliente.setId_user(resultadoSQL.getInt(1));
            cliente.setEmail(resultadoSQL.getString(2));
            cliente.setFirts_name(resultadoSQL.getString(3));
            cliente.setLast_name(resultadoSQL.getString(4));

            connectionDB.close();

        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }
    }





}
