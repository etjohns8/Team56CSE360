package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;


public class HelloController {

    private int currentPortal = 0;

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button selectStaff;
    @FXML
    private Button selectPatient;
    @FXML
    private Label selectionOutput;

    public void updatePortalSelectionStaff(ActionEvent event) throws IOException{
        selectPortal(1);
    }
    public void updatePortalSelectionPatient(ActionEvent event) throws IOException{
        selectPortal(2);
    }

    public void userLogin(ActionEvent event) throws IOException{
        validateLogin();
    }

    private void selectPortal(int selection){
if(selection == 1){
    currentPortal = 1;
  //  selectionOutput.setText("Selection is now Staff");
}else if(selection == 2){
    currentPortal = 2;
   // selectionOutput.setText("Selection is now Patient");
}
    }

    private void validateLogin() throws IOException{
        HelloApplication m = new HelloApplication();
        String correctUsername = "team56";
        String correctPassword = "123";
        if(username.getText().toString().equals(correctUsername) && password.getText().toString().equals(correctPassword) && currentPortal == 1){
            wrongLogin.setText("Success!");

            m.changeScene("staff-landing.fxml");
        }

        else if(username.getText().toString().equals(correctUsername) && password.getText().toString().equals(correctPassword) && currentPortal == 2){
            wrongLogin.setText("Success!");

            m.changeScene("patient-landing.fxml");
        }
        else if(username.getText().toString().equals(correctUsername) && password.getText().toString().equals(correctPassword) && currentPortal == 0){
            wrongLogin.setText("Please select a portal");
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter username / password");
        }

        else{
            wrongLogin.setText("Incorrect username or password");

        }
    }
}