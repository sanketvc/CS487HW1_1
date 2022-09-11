package com.example.cs487hw1_1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    String username = "root";
    String Password = "root";

    @FXML
    void Login(ActionEvent event) {

        if(txtUsername.getText().equals(username)&&txtUsername.getText().equals(Password)){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 728, 541);
                Stage stage = new Stage();
                stage.setTitle("Sunlab Entry Records");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
        }
    }
}
