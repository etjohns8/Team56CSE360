package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Messages {

    @FXML
    private Button newCheckup;
    @FXML
    private Button editInformation;
    @FXML
    private Button messages;
    @FXML
    private Button newPatient;

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
