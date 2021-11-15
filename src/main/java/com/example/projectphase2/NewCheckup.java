package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class NewCheckup {

    @FXML
    private Button newCheckup;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;
    @FXML
    private TextField name;
    @FXML
    private TextField weight;
    @FXML
    private TextField heightFT;
    @FXML
    private TextField heightIN;
    @FXML
    private TextField bodyTemp;
    @FXML
    private TextField bloodPressureUp;
    @FXML
    private TextField bloodPressureLow;
    @FXML
    private TextField email;
    @FXML
    private TextField knownAllergies;
    @FXML
    private TextField otherHealthConcerns;
    @FXML
    private Label outputMessage;

    public void goNewPatient(ActionEvent event) throws IOException {
        goNewScene("staff-landing.fxml");
    }
    public void goNewCheckup(ActionEvent event) throws IOException{
        goNewScene("new-checkup.fxml");
    }
    public void goEditInformation(ActionEvent event) throws IOException{
        goNewScene("edit-information.fxml");
    }
    public void goMessages(ActionEvent event) throws IOException{
        goNewScene("messages.fxml");
    }

    private void goNewScene(String fxml) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene(fxml);
    }

    public void createCheckup (ActionEvent event)throws IOException{
if(name.getText().isEmpty() == false && weight.getText().isEmpty() == false && heightFT.getText().isEmpty() == false && heightIN.getText().isEmpty() == false && bodyTemp.getText().isEmpty() == false && bloodPressureUp.getText().isEmpty() == false && bloodPressureLow.getText().isEmpty() == false && email.getText().isEmpty() == false && knownAllergies.getText().isEmpty() == false && otherHealthConcerns.getText().isEmpty() == false ){
    insertIntoDB();
    outputMessage.setText("Checkup Created Successfully");
}else {
    outputMessage.setText("All fields are mandatory");
}

    }

    private void insertIntoDB(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String inputDBString = "INSERT INTO checkups(name, weight, heightFT, heightIN, bodyTemp, bloodpup, bloodplow, knownalg, otherhealth) VALUES (\"" +name.getText() +"\", " +weight.getText() +", " +heightFT.getText() +", "+ heightIN.getText() +", "+bodyTemp.getText() +", "+bloodPressureUp.getText() +", "+bloodPressureLow.getText() +", \"" +knownAllergies.getText() +"\", \"" + otherHealthConcerns.getText() +"\")";

        try{
            Statement statement = connectDB.createStatement();
            statement.execute(inputDBString);
            outputMessage.setText("Query Executed Successfully");


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
