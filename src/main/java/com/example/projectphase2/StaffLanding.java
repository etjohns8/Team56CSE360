package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class StaffLanding {

    @FXML
    private Button newCheckup;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;
    @FXML
    private DatePicker dobPicker;
    @FXML
    private TextField nameField;
    @FXML
    private TextField contactField;
    @FXML
    private TextArea insuranceField;
    @FXML
    private TextArea pharmacyField;
    @FXML
    private Label errorMessage;
    @FXML
    private Button logOut;

    public void goLogOut(ActionEvent event) throws IOException{
        goNewScene("hello-view.fxml");
    }

public void goNewPatient(ActionEvent event) throws  IOException{
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

    public void createPatient(ActionEvent event) throws IOException{
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
if(nameField.getText().isEmpty() == false && dobPicker.getValue() != null && contactField.getText().isEmpty() == false && insuranceField.getText().isEmpty() == false && pharmacyField.getText().isEmpty() == false){
    try{
        String inputDBString = "INSERT INTO patient(name, dateofbirth, email, insuranceinfo, pharminfo) VALUES (\"" + nameField.getText() +"\", '" + dobPicker.getValue() +"',\"" + contactField.getText() +"\", \"" + insuranceField.getText() +"\", \"" + pharmacyField.getText() + "\")";

        Statement statement = connectDB.createStatement();
        statement.execute(inputDBString);
        //errorMessage.setText("Query Executed Successfully");


    }
    catch(Exception e){
        e.printStackTrace();
        e.getCause();
    }

    errorMessage.setText("Customer Created Successfully");
}else{
    errorMessage.setText("All fields are mandatory");
}

    }
}
