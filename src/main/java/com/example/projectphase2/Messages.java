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

public class Messages {
    String currentUserEmail;

    @FXML
    private Button newCheckup;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;
    @FXML
    private Button newMessageButton;
    @FXML
    private ComboBox subjectSelection;
    @FXML
    private TextArea bodyDisplay;
    @FXML
    private TextField senderEmail;
    @FXML
    public void initialize() {
        setUpSubjects();
    }
    @FXML
    private Button logOut;

    public void goLogOut(ActionEvent event) throws IOException{
        goNewScene("hello-view.fxml");
    }

    private void setUpSubjects(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //String getEmails = "SELECT subject FROM messages WHERE reciever <> '" + currentUser+"'";
        String getCurrentUserEmail = "SELECT email FROM user_account WHERE username = '" + currentUser + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getCurrentUserEmail);

            while(queryResult.next()){
                //selectReciever.getItems().addAll(queryResult.getString(1));
                currentUserEmail = queryResult.getString(1);

            }
            String getSubjects = "SELECT subject FROM messages WHERE reciever = '" + currentUserEmail+"'";
            queryResult = statement.executeQuery(getSubjects);

            while(queryResult.next()){
                subjectSelection.getItems().addAll(queryResult.getString(1));

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void updateMessageBox(ActionEvent event) throws IOException{

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        //String getEmails = "SELECT subject FROM messages WHERE reciever <> '" + currentUser+"'";
        String getSelectedMessage = "SELECT body FROM messages WHERE reciever = '" + currentUserEmail + "' AND subject = '" +subjectSelection.getValue() + "'";
        String getSelectedMessageSender = "SELECT sender FROM messages WHERE reciever = '" + currentUserEmail + "' AND subject = '" +subjectSelection.getValue() + "'";


        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getSelectedMessage);

            while(queryResult.next()){
                //selectReciever.getItems().addAll(queryResult.getString(1));
                bodyDisplay.setText(queryResult.getString(1));

            }
            queryResult = statement.executeQuery(getSelectedMessageSender);
            while(queryResult.next()){
                //selectReciever.getItems().addAll(queryResult.getString(1));
                senderEmail.setText(queryResult.getString(1));

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
    public void goNewMessage(ActionEvent event) throws IOException{
        goNewScene("new-message.fxml");
    }

    private void goNewScene(String fxml) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene(fxml);
    }
}
