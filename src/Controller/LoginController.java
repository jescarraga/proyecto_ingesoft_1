package Controller;

import Repository.LoginRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;



public class LoginController {




    @FXML
    private TextField emailUsuario;
    @FXML
    private PasswordField passwordUsuario;

    @FXML
    private Button loginBoton;


    public void loginButtonAction(ActionEvent event){
        LoginRepository.verficarUsuarioBD(emailUsuario.getText(), passwordUsuario.getText());
    }
}

