package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class usuario {

    private int id_user;
    private String email;
    private String firts_name;
    private String last_name;
    private String password;

    public usuario(String email, String firts_name, String last_name, String password) {
        this.email = email;
        this.firts_name = firts_name;
        this.last_name = last_name;
        this.password = password;
    }

    public usuario(){ }

    public int getId_user() {
        return id_user;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirts_name() {
        return firts_name;
    }

    public void setFirts_name(String firts_name) {
        this.firts_name = firts_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verficarUsuarioBD(String email, String contraseña) {

    //Conecta con la DB
    DB connetionNow = new DB();
    Connection connectionDB = connetionNow.getConnection();

    //SQL sentencia a ejecutar
    String sqlSentencia = "SELECT count(1) from prueba_proyecto.user where email = '" + email + "' and password = '" + contraseña + "'";

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
}