package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.projectphase2.HelloController.currentUser;

public class PastVisits {
    @FXML
    private Button patientInformation;
    @FXML
    private Button pastVisits;
    @FXML
    private Button patientMessages;
    @FXML
    private ComboBox CheckupSelection;
    @FXML
    private TextField weightBox;
    @FXML
    private TextField heightFTBox;
    @FXML
    private TextField heightINBox;
    @FXML
    private TextField bTempBox;
    @FXML
    private TextField bPresUpBox;
    @FXML
    private TextField bPresLowBox;
    @FXML
    private TextField docFindings;
    @FXML
    private TextField prescMeds;

    @FXML
    public void initialize() {setUpCheckups();}

    private void setUpCheckups(){
        //CheckupSelection.setSelectionModel(null);
        //CheckupSelection.getItems().

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getUsername = "SELECT email from user_account WHERE username = '" + currentUser+"'";
        String currentUserEmail = "";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getUsername);


            while(queryResult.next()){
                currentUserEmail = queryResult.getString(1);

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String getDates = "SELECT ID FROM checkups WHERE email= \"" + currentUserEmail +"\"";
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
    public void fillBoxes(ActionEvent event){
        String dbQuery = "SELECT weight FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, weightBox);
         dbQuery = "SELECT heightFT FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, heightFTBox);
         dbQuery = "SELECT heightIN FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, heightINBox);
         dbQuery = "SELECT bodyTemp FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bTempBox);
         dbQuery = "SELECT bloodpup FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bPresUpBox);
         dbQuery = "SELECT bloodplow FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, bPresLowBox);
         dbQuery = "SELECT docfinds FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, docFindings);
         dbQuery = "SELECT prescmeds FROM checkups WHERE ID= \"" + CheckupSelection.getValue() +"\"";
        pollAndSet(dbQuery, prescMeds);


    }

    public void goPatientInformation(ActionEvent event)throws IOException {
        goNewScene("patient-landing.fxml");
    }
    public void goPastVisits(ActionEvent event)throws IOException{
        goNewScene("past-visits.fxml");
    }
    public void goPatientMessages(ActionEvent event)throws IOException{
        goNewScene("patient-messages.fxml");
    }


    private void goNewScene(String fxml) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene(fxml);
    }
}
