package com.example.projectphase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class PatientLanding {
    @FXML
    private Button patientInformation;
    @FXML
    private Button pastVisits;
    @FXML
    private Button patientMessages;

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
