package Controller;

import Repository.LoginRepository;
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



    }

    @FXML
    private void eventAction(ActionEvent event){

        Object evt = event.getSource();

        if (evt.equals(registerBoton)){

            if(RegisterRepository.registrarUsuario(nombreUsuario.getText(),apellidoUsuario.getText(),
                    emailUsuario.getText(), passwordUsuario.getText())){

                nombreUsuario.setText("");
                apellidoUsuario.setText("");
                emailUsuario.setText("");
                passwordUsuario.setText("");

            }


        }else if(evt.equals(linkLogin)){
            //falta llevar a home
            Repository.SceneRepository.loadStage("/vista/Login.fxml", event,
                    getClass().getResource("/vista/Login.fxml") );
        }

    }

}
