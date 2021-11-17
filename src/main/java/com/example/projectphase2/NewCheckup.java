package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewCheckup {

    Date date = new Date();
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
    private TextArea knownAllergies;
    @FXML
    private TextArea otherHealthConcerns;
    @FXML
    private Label outputMessage;
    @FXML
    private ComboBox email;
    @FXML
    public void initialize() {
        setUpEmails();
    }
    @FXML
    private Button logOut;

    public void goLogOut(ActionEvent event) throws IOException{
        goNewScene("hello-view.fxml");
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
    public void goDocEdit() throws IOException {
        goNewScene("new-checkup-doctor.fxml");
    }

    private void goNewScene(String fxml) throws IOException{
        HelloApplication m = new HelloApplication();
        m.changeScene(fxml);
    }

    public void createCheckup (ActionEvent event)throws IOException{
if(name.getText().isEmpty() == false && weight.getText().isEmpty() == false && heightFT.getText().isEmpty() == false && heightIN.getText().isEmpty() == false && bodyTemp.getText().isEmpty() == false && bloodPressureUp.getText().isEmpty() == false && bloodPressureLow.getText().isEmpty() == false && email.getValue() != null && knownAllergies.getText().isEmpty() == false && otherHealthConcerns.getText().isEmpty() == false ){
    insertIntoDB();
    outputMessage.setText("Checkup Created Successfully");
    goDocEdit();
}else {
    outputMessage.setText("All fields are mandatory");
}

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
    private void insertIntoDB(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String inputDBString = "INSERT INTO checkups(name, weight, heightFT, heightIN, bodyTemp, bloodpup, bloodplow, knownalg, otherhealth, email, date) VALUES (\"" +name.getText() +"\", " +weight.getText() +", " +heightFT.getText() +", "+ heightIN.getText() +", "+bodyTemp.getText() +", "+bloodPressureUp.getText() +", "+bloodPressureLow.getText() +", \"" +knownAllergies.getText() +"\", \"" + otherHealthConcerns.getText() +"\", \"" +email.getValue() + "\", \""+ formatter.format(date)+ "\")";

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
