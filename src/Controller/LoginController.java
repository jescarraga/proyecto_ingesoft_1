package Controller;

import Modelo.usuario;
import Repository.LoginRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;



public class LoginController {

    static public usuario cliente = new usuario();




    @FXML
    private TextField emailUsuario;
    @FXML
    private PasswordField passwordUsuario;

    @FXML
    private Hyperlink registrarse;



    public void loginButtonAction(ActionEvent event){
        if (LoginRepository.verficarUsuarioBD(emailUsuario.getText(), passwordUsuario.getText())){
            LoginRepository.instanciarUsuario(emailUsuario.getText(),cliente);
        }
    }

    public void linkRegistrarse(ActionEvent event){

    }
}

