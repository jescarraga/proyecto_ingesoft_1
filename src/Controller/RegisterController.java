package Controller;

import Modelo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterController {





    //Verificamos si el usuario ya esta registrado
    public boolean verficarUsuarioBD(String email) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "SELECT count(1) from prueba_proyecto.user where email = '" + email + "'";

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

    //Insertar usuario en base de datos
    public boolean agregarUsuarioBD(String nombres,String apellidos,String email,String password) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "INSERT INTO prueba_proyecto.user (EMAIL,FIRST_NAME,LAST_NAME,PASSWORD)"+
                "VALUES ('"+email+"','"+nombres+"','"+apellidos+"','"+password+"')";

        //variable a retornar
        boolean exito = false;
        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sqlSentencia);

            exito = true;

        }catch (Exception validaUsuario){
            exito = false;
            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }

        return exito;
    }

    //Verficar campos

    //Caracteres validos solo nombre
    public boolean verificar_letra(int ascci){
        boolean esLetra = false;

        if ((ascci >= 65 && ascci <= 90) || (ascci >= 97 && ascci <= 122) || (ascci >= 128 && ascci <= 154)
                || (ascci >= 160 && ascci <= 165) || (ascci >= 181 && ascci <= 183) || (ascci >= 210 && ascci <= 216)
                || (ascci >= 97 && ascci <= 122) || (ascci == 222) || (ascci == 224) || (ascci >= 226 && ascci <= 229)
                || (ascci >= 97 && ascci <= 122) || (ascci >= 233 && ascci <= 237)){
            esLetra = true;
        }else{
            esLetra = false;
        }
        return esLetra;
    }

    //Solo permite verificar si posee un nombre o 2 nombres y espacions en blanco
    public boolean verificar_espacios(String nombre_apellido){
        boolean correcto = false;
        String[] b = nombre_apellido.split(" ");
        int c = b.length;
        if (c <= 2 && c > 0){
            System.out.print(c);
            correcto = true;
        }
        return correcto;
    }

    //verficar correo
    public boolean vefCorre(String corre){

    }



    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField apellidoUsuario;

    @FXML
    private TextField emailUsuario;

    @FXML
    private PasswordField passwordUsuario;

    @FXML
    private Button registerBoton;

    @FXML
    private Hyperlink linkLogin;

    public void registerButtonAction (ActionEvent event){
        if (emailUsuario.getText().isEmpty()) {
            System.out.println("por favor rellene el correo");
        }else{
            boolean usuario_valido = verficarUsuarioBD(emailUsuario.getText());
            if (!usuario_valido == true) {
                boolean exito = agregarUsuarioBD(nombreUsuario.getText(),apellidoUsuario.getText(),
                        emailUsuario.getText(),passwordUsuario.getText());
                if (exito == true) {
                    System.out.println("usuario agregado exitosamente");
                }else{
                    System.out.println("error al agregar usuario - imposible");
                }
            }else{
                System.out.println("email ya registrado");
            }
        }
    }

    public void linkAction (ActionEvent event){
        String a = emailUsuario.getText();
        boolean b = verificar_espacios(a);
        System.out.println(b);
    }

}
