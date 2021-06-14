package Repository;

import DataBase.DB;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class RegisterRepository {


    private static boolean verificarUsuarioBD(String email) {

        //variable a retornar
        boolean exito = true;
        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //SQL sentencia a ejecutar
            String sqlSentencia = "SELECT count(1) from user where email = '" + email + "'";

            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            ResultSet resultadoSQL = statement.executeQuery(sqlSentencia);

            while (resultadoSQL.next()) {
                if (resultadoSQL.getInt(1) == 1) {
                    exito = false;
                }else{
                    exito = true;
                }
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

        return exito;
    }

    private static void agregarUsuarioBD(String nombres,String apellidos,String email,String password) {


        try {

            //Conecta con la DB
            DB connetionNow = new DB();
            Connection connectionDB = connetionNow.getConnection();

            //SQL sentencia a ejecutar
            String sqlSentencia = "INSERT INTO user (ID_ROLE,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD)"+
                    "VALUES ('"+2+"','"+email+"','"+nombres+"','"+apellidos+"','"+password+"')";

            // Ejecuta el comando SQL
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sqlSentencia);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("EXITO");
            alert.setContentText("Usuario añadido correctamente a la Base de datos");
            alert.showAndWait();

            connectionDB.close();


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


    private static boolean verificarLetraNombresApellidos(int ascci){
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

    private static boolean verificarEspacios(String nombre){

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

    private static boolean verificarEmail(String email){

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

        if ((numArrobas != 1) || (numPuntos > 3) || (bandera!= true) || (email.length() < 5) ||
                ((int)emailArreglo[emailArreglo.length-1] == 64) || ((int)emailArreglo[emailArreglo.length-1] == 46)){
            bandera = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Se detecto un correo no valido, recuerde:" +"\n"+
                    "-Solamente incluir caracteres validos (a-z), (0-9), (.), (@)."+"\n"+
                    "-Solamente incluir dos caracteres de punto (.) y un arroba (@)."+
                    "\n"+"-El correo debe contener minimo 5 caracteres.");
            alert.showAndWait();
        }
        return bandera;

    }

    private static boolean verificarPassword(String password){
        boolean banderaFinal = true;

        char[] palabras = password.toCharArray();

        if (password.length() >= 3){
            for (int i = 0; i < password.length(); i++) {
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
            alert.setContentText("La password debe contener minimo 3 caracteres");
            alert.showAndWait();
        }





        return banderaFinal;


    }

    private static boolean verificarNombreApellido(String nombreOApellido){

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

    private static boolean verificarRegistro(String nombre, String apellido, String email, String password){

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

            boolean banderaPassword = verificarPassword(password);

            boolean banderaCorreo = verificarEmail(email);

            if (banderaCorreo == true) {
                banderaCorreo = verificarUsuarioBD(email);
                if (banderaCorreo == false) {
                    Alert alerta = new Alert(Alert.AlertType.WARNING);
                    alerta.setHeaderText(null);
                    alerta.setTitle("ADVERTENCIA");
                    alerta.setContentText("El email ya se encuentra registrado en nuestra BD," +
                            " por favor inicie sesión o regístrese con un email diferente ");
                    alerta.showAndWait();
                }
            }

            if (banderaCorreo == true && banderaNombre == true && banderaApellido == true && banderaPassword == true){
                bandera = true;
            }


        }
        return bandera;
    }

    public static boolean registrarUsuario(String nombre, String apellido, String email, String password){
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

}
