package com.example.projectphase2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PatientMessages {
    @FXML
    private Button patientInformation;
    @FXML
    private Button pastVisits;
    @FXML
    private Button patientMessages;

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
