/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Patient;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class PatientDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Patient p;
    
    public void initData(Patient i){
        p=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CheckDoctorsListbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewdoctorlistscene.fxml"));
        Parent vds = loader.load();
        
        Scene newScene = new Scene(vds);
        
        ViewdoctorlistsceneController controller = loader.getController();
        controller.initData(p);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CheckBillsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Checkbillscenepatient.fxml"));
        Parent cbsp = loader.load();
        
        Scene newScene = new Scene(cbsp);
        
        CheckbillscenepatientController controller = loader.getController();
        controller.initData(p);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }


    @FXML
    private void TakeAppointmentbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Appointmentprocedurescene.fxml"));
        Parent aps = loader.load();
        
        Scene newScene = new Scene(aps);
        
        AppointmentproceduresceneController controller = loader.getController();
        controller.initData(p);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CheckPrecriptionbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewPrescriptionPatient.fxml"));
        Parent aps = loader.load();
        
        Scene newScene = new Scene(aps);
        
        ViewPrescriptionPatientController controller = loader.getController();
        controller.initData(p);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void checkReportStatusbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CheckTestReportStatusPatient.fxml"));
        Parent ctrsp = loader.load();
        
        Scene newScene = new Scene(ctrsp);
        
        CheckTestReportStatusPatientController controller = loader.getController();
        controller.initData(p);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    
}
