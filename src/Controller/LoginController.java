package Controller;

import Modelo.usuario;
import Repository.LoginRepository;
import Repository.SceneRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;


public class LoginController {

    static public usuario cliente = new usuario();


    @FXML
    private TextField emailUsuario;
    @FXML
    private PasswordField passwordUsuario;

    @FXML
    private Hyperlink linkRegistrarse;
    @FXML
    private Button botonIngresar;



    @FXML
    private void eventAction(ActionEvent event){

        Object evt = event.getSource();

        if (evt.equals(botonIngresar)){
            if (LoginRepository.verficarUsuarioBD(emailUsuario.getText(), passwordUsuario.getText())){
                LoginRepository.instanciarUsuario(emailUsuario.getText(),cliente);
                //falta llevar a home
                Repository.SceneRepository.loadStage("/vista/Register.fxml", event, getClass().getResource("/vista/Register.fxml") );
            }
        }else if(evt.equals(linkRegistrarse)){
            Repository.SceneRepository.loadStage("/vista/Register.fxml", event, getClass().getResource("/vista/Register.fxml"));
        }

    }


}

