package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static com.example.projectphase2.HelloController.currentUser;

public class PatientLanding {

    String InitEmail;
    @FXML
    private Button patientInformation;
    @FXML
    private Button pastVisits;
    @FXML
    private Button submitEdits;
    @FXML
    private Button patientMessages;
    @FXML
    private TextField firstnameBox;
    @FXML
    private TextField lastnameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private DatePicker DOBField;
    @FXML
    private Label outputMessage;
    @FXML
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String dbQuery = "SELECT email FROM user_account WHERE username = '" +currentUser +"'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(dbQuery);

            while(queryResult.next()){
                InitEmail = queryResult.getString(1);
                //CheckupSelection.getItems().addAll(queryResult.getInt(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        initializeFields();
    }
    @FXML
    private Button logOut;

    public void goLogOut(ActionEvent event) throws IOException{
        goNewScene("hello-view.fxml");
    }


    public void submitChangesToDB(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String dbQuery = "UPDATE user_account SET firstname='" + firstnameBox.getText() +"', lastname='" +lastnameBox.getText() +"', dateofbirth= \"" +DOBField.getValue() +"\", email='" +emailBox.getText() +"' WHERE username='" + currentUser +"'";
        String updateCheckups = "UPDATE checkups SET email = '" + emailBox.getText()+"' WHERE email = '" + InitEmail+"'";


        try{
            Statement statement = connectDB.createStatement();
            statement.execute(updateCheckups);
            statement.execute(dbQuery);
            outputMessage.setText("Information Updated Successfully");


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
    private void initializeFields(){
        String dbQuery = "SELECT firstname FROM user_account WHERE username= \"" + currentUser +"\"";
        pollAndSet(dbQuery, firstnameBox);
        dbQuery = "SELECT lastname FROM user_account WHERE username= \"" + currentUser +"\"";
        pollAndSet(dbQuery, lastnameBox);
        dbQuery = "SELECT email FROM user_account WHERE username= \"" + currentUser +"\"";
        pollAndSet(dbQuery, emailBox);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        dbQuery = "SELECT dateofbirth FROM user_account WHERE username= \"" + currentUser +"\"";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(dbQuery);

            while(queryResult.next()){
                DOBField.setValue(queryResult.getDate(1).toLocalDate());
               // DOBField.setText(queryResult.getString(1));
                //CheckupSelection.getItems().addAll(queryResult.getInt(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void goPatientInformation(ActionEvent event)throws IOException{
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
