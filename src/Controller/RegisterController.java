package Controller;

import Modelo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class RegisterController {


    //Verificamos si el usuario ya esta registrado
    public boolean verificarUsuarioBD(String email) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "SELECT count(1) from user where email = '" + email + "'";

        //variable a retornar
        boolean exito = true;
        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                if (resultadoSQL.getInt(1) == 1) {
                    exito = false;
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setHeaderText(null);
                    alerta.setTitle("ADVERTENCIA");
                    alerta.setContentText("El email ya se encuentra registrado en nuestra BD, por favor inicie sesión o regístrese con un email diferente ");
                    alerta.showAndWait();
                }else{
                    exito = true;
                }
            }
        }catch (Exception validaUsuario){
            validaUsuario.printStackTrace();
            validaUsuario.getCause();

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("ADVERTENCIA");
            alerta.setContentText("Error al conectar con la BD");
            alerta.showAndWait();
        }

        return exito;
    }

    //Insertar usuario en base de datos
    public void agregarUsuarioBD(String nombres,String apellidos,String email,String password) {

        //Conecta con la DB
        DB connetionNow = new DB();
        Connection connectionDB = connetionNow.getConnection();

        //SQL sentencia a ejecutar
        String sqlSentencia = "INSERT INTO user (EMAIL,FIRST_NAME,LAST_NAME,PASSWORD)"+
                "VALUES ('"+email+"','"+nombres+"','"+apellidos+"','"+password+"')";

        //variable a retornar

        try {
            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sqlSentencia);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXITO");
            alert.setContentText("Usuario añadido correctamente a la Base de datos");
            alert.showAndWait();


        }catch (Exception validaUsuario){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Error al ingresar usuario nuevo en la base de datos");
            alert.showAndWait();

            validaUsuario.printStackTrace();
            validaUsuario.getCause();
        }

    }

    //Verficar campos

    //Caracteres validos solo nombre y apellidos
    public boolean verificarLetraNombresApellidos(int ascci){
        boolean esLetra = false;

        if ((ascci >= 65 && ascci <= 90) || (ascci >= 97 && ascci <= 122)){
            esLetra = true;
        }else{
            esLetra = false;

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Se detectaron caracteres no permitidos en los campos de nombres o apellidos." + "\n"+
                    "recuerdo solamente incluir caracteres permitidos (a-z) y espacios.");
            alert.showAndWait();
        }
        return esLetra;
    }

    public boolean verificarEspacios(String nombre){

        boolean banderaFinal = true;

        char[] palabras = nombre.toCharArray();

        if (((int)palabras[0] == 32) || ((int)palabras[palabras.length-1] == 32)) {
            banderaFinal = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Se detectaron espacios no permitidos en los campos de nombres o apellidos");
            alert.showAndWait();
        }

        if (banderaFinal == true){
            for (int i = 0; i < nombre.length()-1; i++) {
                if (((int)palabras[i] == 32) && ((int)palabras[i+1] == 32)) {
                    banderaFinal = false;
                    break;
                }
            }
        }


        return banderaFinal;

    }
    //verficar correo
    public boolean verificarEmail(String email){

        //Bandera a retornar
        boolean bandera = true;
        int numArrobas = 0;
        int numPuntos = 0;

        //Creo un arreglo cuyos elementos sean cada una de las letras del correo ingresado
        char[] emailArreglo = email.toCharArray();

        for (int i = 0; i < email.length(); i++) {

            int ascci = emailArreglo[i];

            //verifica que solo tenga minusculas
            if ((ascci >= 97 && ascci <= 122) || (ascci >= 48 && ascci <= 57)){
                // No hace nada pues el número resulto ser una letra minuscula
            }else if(ascci == 64){
                //Como detecto un @ sumamos 1 al contador de arrobas
                numArrobas++;
            }else if (ascci == 46){
                //Como detecto un . sumamos 1 al contador de puntos
                numPuntos++;
            }else{
                bandera = false;
                break;
            }

        }

        if ((numArrobas != 1) || (numPuntos != 1) || (bandera!= true) || (email.length() < 5) ||
                ((int)emailArreglo[emailArreglo.length-1] == 64) || ((int)emailArreglo[emailArreglo.length-1] == 46)){
            bandera = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Se detecto un correo no valido, recuerde:" +"\n"+
                    "-Solamente incluir caracteres validos (a-z), (.), (@)."+"\n"+
                    "-Solamente incluir un simbolo de punto (.) y uno de arroba (@)."+
                    "\n"+"-El correo debe contener minimo 5 caracteres.");
            alert.showAndWait();
        }
        return bandera;

    }

    //verdificar contraseña
    public boolean verificarContraseña(String contraseña){
        boolean banderaFinal = true;

        char[] palabras = contraseña.toCharArray();

        if (contraseña.length() >= 3){
            for (int i = 0; i < contraseña.length(); i++) {
                if ((int)palabras[i] == 32){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Advertencia");
                    alert.setContentText("Se detectaron espacios no permitidos en al campo de la contraseña");
                    alert.showAndWait();
                    banderaFinal = false;
                    break;
                }
            }
        }else{
            banderaFinal = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("La contraseña debe contener minimo 3 caracteres");
            alert.showAndWait();
        }





        return banderaFinal;


    }
    //verificar registro
    public boolean verificarNombreApellido(String nombreOApellido){

        boolean bandera = true;
        bandera = verificarEspacios(nombreOApellido);

        //verifico espacios
        if (bandera == true) {
            //verifico caracteres validos

            String nombreSinAcentos = StringUtils.stripAccents(nombreOApellido);

            char[] nombreArreglo = nombreSinAcentos.toCharArray();

            for (int i = 0; i < nombreOApellido.length(); i++) {

                int ascci = (int)nombreArreglo[i];
                bandera = verificarLetraNombresApellidos(ascci);

                if (bandera == false) {
                    break;
                }
            }
        }

        return bandera;

        
    }


    //fuinción final versificar login
    public boolean verificarRegistro(String nombre, String apellido, String email, String password){

        boolean bandera = false;

        if ((StringUtils.isBlank(nombre)) || (StringUtils.isBlank(apellido))
                || (StringUtils.isBlank(email)) || (StringUtils.isBlank(password))) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("ADVERTENCIA");
            alerta.setContentText("Se han detectado uno o más campos en blanco, por favor ingrese los datos solicitados.");
            alerta.showAndWait();

        }else{
            boolean banderaNombre = verificarNombreApellido(nombre);
            boolean banderaApellido = verificarNombreApellido(apellido);

            boolean banderaContraseña = verificarContraseña(password);

            boolean banderaCorreo = verificarEmail(email);

            if (banderaCorreo == true) {
                banderaCorreo = verificarUsuarioBD(email);
            }

            if (banderaCorreo == true && banderaNombre == true && banderaApellido == true && banderaContraseña == true){
                bandera = true;
            }


        }
        return bandera;
    }

    public boolean registrarUsuario(String nombre, String apellido, String email, String password){
        boolean exito = false;
        boolean exitoVerificaciones = verificarRegistro(nombre, apellido, email, password);
        if (exitoVerificaciones == true) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmacion");
            alert.setContentText("¿Deseas realmente registrar el usuario con los siguientes datos?" +
                    "\n Nombres: "+ nombre + "\n Apellidos: "+ apellido +
                    "\n Email: "+email + "\n Password: "+ password);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                agregarUsuarioBD(nombre, apellido, email, password);
                exito = true;
            }
        }else{
            exito = false;
        }
        return exito;
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

        if(registrarUsuario(nombreUsuario.getText(),apellidoUsuario.getText(),emailUsuario.getText(),
                passwordUsuario.getText())){

            nombreUsuario.setText("");
            apellidoUsuario.setText("");
            emailUsuario.setText("");
            passwordUsuario.setText("");

        }

    }

    public void linkAction (ActionEvent event){




    }

}
