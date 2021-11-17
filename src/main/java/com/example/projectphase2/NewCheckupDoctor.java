package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewCheckupDoctor {

    @FXML
    private TextField docFinds;
    @FXML
    private TextField preMeds;
    @FXML
    private Button newCheckup;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;
    @FXML
    private Label outputMessage;
    @FXML
    private Button submitChanges;
    @FXML
    private ComboBox email;
    @FXML
    public void initialize() {
        setUpEmails();
    }

    private void setUpEmails(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getNames = "SELECT email FROM patient";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getNames);

            while(queryResult.next()){
                email.getItems().addAll(queryResult.getString(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

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

    public void processButton(ActionEvent event){
        if(docFinds.getText().isEmpty() == false && preMeds.getText().isEmpty() == false && email.getValue() != null){
            insertIntoDB();
            outputMessage.setText("Checkup Edited Successfully");

        }else {
            outputMessage.setText("All fields are mandatory");
        }
    }
    private void insertIntoDB(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getmaxID = "SELECT MAX(ID) FROM checkups ";
        int maxId = 0;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getmaxID);

            while(queryResult.next()){
                maxId = queryResult.getInt(1);

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }



        String inputDBString = "UPDATE checkups SET docfinds = \"" + docFinds.getText() +"\", prescmeds = \"" + preMeds.getText() +"\" WHERE ID = \"" + maxId +"\"";
        try{
            Statement statement = connectDB.createStatement();
            statement.execute(inputDBString);
            //outputMessage.setText("Query Executed Successfully");


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }




}