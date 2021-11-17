package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.projectphase2.HelloController.currentUser;

public class NewMessagePatient {

    String currentUserEmail;

    @FXML
    private Button patientInformation;
    @FXML
    private Button pastVisits;
    @FXML
    private Button patientMessages;
    @FXML
    private ComboBox selectReciever;
    @FXML
    private TextField subjectBox;
    @FXML
    private TextArea bodyBox;
    @FXML
    private Button sendButton;
    @FXML
    public void initialize() {
        setUpEmails();
    }

    private void setUpEmails(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String getEmails = "SELECT email FROM user_account WHERE username <> '" + currentUser+"'";
        String getCurrentUserEmail = "SELECT email FROM user_account WHERE username = '" + currentUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getEmails);

            while(queryResult.next()){
                selectReciever.getItems().addAll(queryResult.getString(1));

            }
            queryResult = statement.executeQuery(getCurrentUserEmail);

            while(queryResult.next()){
                currentUserEmail = queryResult.getString(1);

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void sendMessage(ActionEvent event) throws IOException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String updateQuery = "INSERT INTO messages(sender, reciever, subject, body) VALUES ('" + currentUserEmail+"','" +selectReciever.getValue() +"','" +subjectBox.getText() +"','" +bodyBox.getText() +"')";

        try{
            Statement statement = connectDB.createStatement();
            statement.execute(updateQuery);

        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        goPatientMessages(event);
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
