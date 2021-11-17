package com.example.projectphase2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextField nameBox;
    @FXML
    private TextField weightBox;
    @FXML
    private TextField bTempBox;
    @FXML
    private TextField heightFtBox;
    @FXML
    private TextField heightInBox;
    @FXML
    private TextField bpUpBox;
    @FXML
    private TextField bpLowBox;
    @FXML
    private TextField knownAlg;
    @FXML
    private TextField othIssue;
    @FXML
    private TextField docFinds;
    @FXML
    private TextField prescMeds;
    @FXML
    private Label dateAppt;
    @FXML
    private Label outputMessage;
    @FXML
    private Button submitChanges;
    @FXML
    public void initialize() {
       setUpPatients();
    }


    public void editDBwithChanges(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String updateQuery = "UPDATE checkups SET name = '" +nameBox.getText() +"', weight = " +weightBox.getText() +", heightFT=" +heightFtBox.getText() +", heightIN=" +heightInBox.getText() +", bodyTemp=" +bTempBox.getText() +", bloodpup=" + bpUpBox.getText()+", bloodplow=" +bpLowBox.getText() +", knownalg='" +knownAlg.getText() +"', otherhealth='" +othIssue.getText() +"', docfinds='" +docFinds.getText() +"', prescmeds='" +prescMeds.getText() +"' WHERE ID = " +CheckupSelection.getValue() +" ";


        try{
            Statement statement = connectDB.createStatement();
            statement.execute(updateQuery);
           // outputMessage.setText("Query Executed Successfully");


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    private void pollAndSet(String Query, TextField box){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //String dbQuery = "SELECT name FROM checkups WHERE email= \"" + patientSelection.getValue() +"\"";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(Query);

            while(queryResult.next()){
                box.setText(queryResult.getString(1));
                //CheckupSelection.getItems().addAll(queryResult.getInt(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void updateFields(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String dbQuery = "SELECT name FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, nameBox);
        dbQuery = "SELECT weight FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, weightBox);
        dbQuery = "SELECT bodyTemp FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bTempBox);
        dbQuery = "SELECT heightFT FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, heightFtBox);
        dbQuery = "SELECT heightIN FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, heightInBox);
        dbQuery = "SELECT bloodpup FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bpUpBox);
        dbQuery = "SELECT bloodplow FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bpLowBox);
        dbQuery = "SELECT knownalg FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, knownAlg);
        dbQuery = "SELECT otherhealth FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, othIssue);
        dbQuery = "SELECT docfinds FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, docFinds);
        dbQuery = "SELECT prescmeds FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, prescMeds);

        dbQuery = "SELECT date FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(dbQuery);

            while(queryResult.next()){
                dateAppt.setText(queryResult.getString(1));
                //box.setText(queryResult.getString(1));
                //CheckupSelection.getItems().addAll(queryResult.getInt(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }



    }
    public void updateButton(ActionEvent event){
        if(patientSelection.getValue() != null){
            CheckupSelection.getItems().removeAll(CheckupSelection.getItems());
            //outputMessage.setText("Update Attempted");
            setUpCheckups();
        }else {
            //outputMessage.setText("Update Failed: Please select Patient");
        }
    }

    private void setUpCheckups(){
        //CheckupSelection.setSelectionModel(null);
        //CheckupSelection.getItems().

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
        //outputMessage.setText("Options Selected");

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
