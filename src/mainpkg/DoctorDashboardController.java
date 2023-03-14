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
import users.Doctor;


public class DoctorDashboardController implements Initializable {

    private Doctor d;
    
    
    public void initData(Doctor i) {
        d = i;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(d.getId());
    }    

    @FXML
    private void checkappointmentsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewappointmentlistdoctorscene.fxml"));
        Parent viewapplistdoc = loader.load();

        Scene userdashboardScene = new Scene(viewapplistdoc);

        ViewappointmentlistdoctorsceneController controller = loader.getController();
        controller.initData(d);
        System.out.println(d.getAge());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(userdashboardScene);
        window.show();
                    
    }

    @FXML
    private void updateavailabilitystatusbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Doctorupdateavailabilityscene.fxml"));
        Parent duas = loader.load();

        Scene newscene = new Scene(duas);

        DoctorupdateavailabilitysceneController controller = loader.getController();
        controller.initData(d);
        System.out.println(d.getAge());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newscene);
        window.show();
    }

    @FXML
    private void checkcommisionsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Doctorviewcommission.fxml"));
        Parent dvcs = loader.load();

        Scene newscene = new Scene(dvcs);

        DoctorviewcommissionController controller = loader.getController();
        controller.initData(d);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newscene);
        window.show();
    }

    @FXML
    private void checkpatientdetailsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewpatientdetailsscene.fxml"));
        Parent personViewParent = loader.load();

        Scene checkpatdetScene = new Scene(personViewParent);

        ViewpatientdetailssceneController controller = loader.getController();
        controller.initData(d);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(checkpatdetScene);
        window.show();
    }

    @FXML
    private void generateprescriptionbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreatePatientprescriptionDoctorscene.fxml"));
        Parent cppds = loader.load();

        Scene newScene = new Scene(cppds);

        CreatePatientprescriptionDoctorsceneController controller = loader.getController();
        controller.initData(d);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }

    
    
}
