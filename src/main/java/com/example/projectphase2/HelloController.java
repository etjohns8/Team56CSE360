package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class HelloController {

    private int currentPortal = 0;
    public static String currentUser;

    @FXML
    private Button button;
    @FXML
    private Button newAccount;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label selectionOutput;

    public void createAccount(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene("RegistrationPage.fxml");

    }
    public void updatePortal() throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getDoctorStatus = "SELECT isDoctor FROM user_account WHERE username = '" +username.getText() +"'";
//wrongLogin.setText(getDoctorStatus);

        try{
            Statement statement = connectDB.createStatement();
            ResultSet doctorStatus = statement.executeQuery(getDoctorStatus);
          while(doctorStatus.next()){
              currentPortal = doctorStatus.getInt(1);
              //wrongLogin.setText("Doctor Status: " + currentPortal);
          }

           /* if(doctorStatus.getInt(1) == 0){
                wrongLogin.setText("This is A Patient");
            }else if(doctorStatus.getInt(1) == 0){
                wrongLogin.setText("This is a Doctor");
            }
            */

            //currentPortal = doctorStatus.getInt(1);
            //wrongLogin.setText("doctor status set to " + doctorStatus.getInt(1));
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void updatePortalSelectionStaff(ActionEvent event) throws IOException{
        selectPortal(1);
    }
    public void updatePortalSelectionPatient(ActionEvent event) throws IOException{
        selectPortal(2);
    }

    public void userLogin(ActionEvent event) throws IOException{
        if(username.getText().isEmpty() == false && password.getText().isEmpty() == false){
            wrongLogin.setText("You are attempting to Log In");
            validateLogin();
        }
        else{
            wrongLogin.setText("Please enter username / password");
        }
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
DatabaseConnection connectNow = new DatabaseConnection();
Connection connectDB = connectNow.getConnection();


String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                updatePortal();
                if(queryResult.getInt(1) == 1){
                    if(currentPortal == -1){
                        wrongLogin.setText("Account Corrupt");
                    }else if(currentPortal == 1){
                        //wrongLogin.setText("Success! Routing to Staff Portal");
                        currentUser = username.getText();
                        m.changeScene("staff-landing.fxml");
                    }else if(currentPortal == 0){
                        //wrongLogin.setText("Success! Routing to Patient Portal");
                        currentUser = username.getText();
                        m.changeScene("patient-landing.fxml");
                    }else{
                        wrongLogin.setText("Something went wrong, please restart application");
                    }
                    //wrongLogin.setText("Success!");
                }else{
                    wrongLogin.setText("Incorrect username or password");
                }

            }


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        /*
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = " + username.getText() + "' AND password = '" + password.getText() + " ' AND";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    wrongLogin.setText("Success!");
                }else{
                    wrongLogin.setText("Incorrect username or password");
                }

            }


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
*/
/*
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
 */
    }
}