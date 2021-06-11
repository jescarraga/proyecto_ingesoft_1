package Repository;

import DataBase.DB;
import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginRepository {

    public static void verficarUsuarioBD(String email, String password) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "SELECT count(1) from user where email = '" + email + "' and password = '" + password + "'";

        try {
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
                }else{
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText(null);
                    alerta.setTitle("ADVERTENCIA");
                    alerta.setContentText("El correo y la contraseña no se encuentran en la base de datos");
                    alerta.showAndWait();
                }
            }
        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }

    }

}
