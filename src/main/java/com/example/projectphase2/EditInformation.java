package com.example.projectphase2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditInformation {
    ObservableList<String> options =
            FXCollections.observableArrayList(
                    "Option 1",
                    "Option 2",
                    "Option 3"
            );


    @FXML
    private Button newCheckup;
    @FXML
    private Button updateInfo;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;
    @FXML
    private ComboBox patientSelection;
    @FXML
    private ComboBox CheckupSelection;
    @FXML
    private Label outputMessage;
    @FXML
    public void initialize() {
       setUpPatients();
    }

    public void updateButton(ActionEvent event){
        if(patientSelection.getValue() != null){
            outputMessage.setText("Update Attempted");
            setUpCheckups();
        }else {
            outputMessage.setText("Update Failed: Please select Patient");
        }
    }

    private void setUpCheckups(){
        //CheckupSelection.setSelectionModel(null);
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getDates = "SELECT ID FROM checkups WHERE email= \"" + patientSelection.getValue() +"\"";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getDates);

            while(queryResult.next()){
                CheckupSelection.getItems().addAll(queryResult.getInt(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    private void setUpPatients(){
        outputMessage.setText("Options Selected");

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getNames = "SELECT email FROM patient";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getNames);

            while(queryResult.next()){
                patientSelection.getItems().addAll(queryResult.getString(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
public void tellme(ActionEvent event) throws IOException{
        outputMessage.setText("Event Triggered");
}

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
}
