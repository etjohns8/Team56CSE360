package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistrationPage {

    int isDoctor = -1;

    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField newPassword;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private Button createAccount;
    @FXML
    private Label errorText;
    @FXML
    private Button selectDoctor;
    @FXML
    private Button selectPatient;

public void setPatient (ActionEvent event) throws IOException{
isDoctor = 0;
}
    public void setDoctor(ActionEvent event) throws IOException{
isDoctor = 1;

    }
    public void submitInfo(ActionEvent event) throws IOException{
        HelloApplication m = new HelloApplication();

        if(newUsername.getText().isEmpty() == false && newPassword.getText().isEmpty() == false && firstName.getText().isEmpty() == false && lastName.getText().isEmpty() == false && email.getText().isEmpty() == false && dateOfBirth.getValue() != null && isDoctor != -1){
    enterAccountDB();
    m.changeScene("hello-view.fxml");
    //errorText.setText("Successful Account Creation");
}else{
    errorText.setText("All fields are mandatory.");
}
    }

    public void enterAccountDB() throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String inputDBString = "INSERT INTO user_account(firstname, lastname, username, password, dateofbirth, email, isDoctor) VALUES (\"" + firstName.getText() +"\", \"" + lastName.getText() +"\", \"" + newUsername.getText() +"\", \"" + newPassword.getText() +"\", '" + dateOfBirth.getValue() + "', \"" + email.getText() +"\", " + isDoctor +")";

        try{
            Statement statement = connectDB.createStatement();
            statement.execute(inputDBString);
            errorText.setText("Query Executed Successfully");


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}

