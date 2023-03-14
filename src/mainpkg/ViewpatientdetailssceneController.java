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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Doctor;
import users.Patient;


public class ViewpatientdetailssceneController implements Initializable {

    @FXML
    private Label patnamelabel;
    @FXML
    private Label patagelabel;
    @FXML
    private Label patgenderlabel;
    @FXML
    private Label patcontactlabel;
    @FXML
    private Label pataddlabel;
    @FXML
    private TextField patidtextfield;

    private Doctor d;
    
    public void initData(Doctor i) {
        d = i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchonclick(ActionEvent event) {
        Patient temp = d.getPatInfo(Integer.parseInt(patidtextfield.getText()));
        
        if(temp!=null){
            patnamelabel.setText(temp.getPatName());
            patagelabel.setText(Integer.toString(temp.getPatAge()));
            patgenderlabel.setText(temp.getPatGender());
            patcontactlabel.setText(temp.getPatContact());
            pataddlabel.setText(temp.getPatAdd());
        }
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DoctorDashboard.fxml"));
        Parent duasParent = loader.load();

        Scene newScene = new Scene(duasParent);

        DoctorDashboardController controller = loader.getController();
        controller.initData(d);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }
    
}
