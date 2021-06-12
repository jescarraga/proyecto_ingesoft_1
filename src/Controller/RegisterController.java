package Controller;

import Repository.RegisterRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class RegisterController {



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

        if(RegisterRepository.registrarUsuario(nombreUsuario.getText(),apellidoUsuario.getText(),emailUsuario.getText(),
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
