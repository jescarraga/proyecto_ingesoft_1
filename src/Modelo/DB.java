package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

public Connection dataBaseLink;
    // Conectar BD con el usuario y contraseÃ±a roo
    // a la BD se le configura la zona horaria
    // Se pueden intruducir ñ?

    public Connection getConnection(){
        String dataBaseName = "prueba_proyecto";
        String dataBaseUser = "root";
        String dataBasePassword = "root";
        String url ="jdbc:mysql://localhost/"+dataBaseName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataBaseLink = DriverManager.getConnection(url,dataBaseUser,dataBasePassword);

            }catch (Exception connection_fail){
            connection_fail.printStackTrace();
            connection_fail.getCause();
            }
        return dataBaseLink;
    }
}

