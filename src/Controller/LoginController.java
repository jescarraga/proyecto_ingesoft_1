package Controller;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import Modelo.usuario;

public class LoginController {
    @FXML
    private TextField emailUsuario;
    @FXML
    private TextField passwordUsuario;

    @FXML
    private Button loginBoton;


    public void loginButtonAction(ActionEvent event){
        usuario nuevoU = new usuario();
        Boolean respuesta = nuevoU.verficarUsuarioBD(emailUsuario.getText(),passwordUsuario.getText());
        System.out.println(respuesta);
        nuevoU = null;
    }
}

