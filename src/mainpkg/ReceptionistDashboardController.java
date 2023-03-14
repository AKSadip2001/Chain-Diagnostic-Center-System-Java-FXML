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
import users.Receptionist;


public class ReceptionistDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Receptionist r;
    
    void initData(Receptionist i) {
        r=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addpatientbuttion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Addpatientscene.fxml"));
        Parent aps = loader.load();

        Scene newScene = new Scene(aps);

        AddpatientsceneController controller = loader.getController();
        controller.initData(r);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void updateduesbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Updatepatientdues.fxml"));
        Parent upd = loader.load();
        
        Scene newScene = new Scene(upd);
        
        UpdatepatientduesController controller = loader.getController();
        
        controller.initData(r);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }


    @FXML
    private void scheduleapppointmentbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Scheduleappointmentscene.fxml"));
        Parent sasParent = loader.load();

        Scene newScene = new Scene(sasParent);

        ScheduleappointmentsceneController controller = loader.getController();
        controller.initData(r);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void addtestbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddpatientTestscene.fxml"));
        Parent apts = loader.load();

        Scene newScene = new Scene(apts);

        AddpatientTestsceneController controller = loader.getController();
        controller.initData(r);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void addexpbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Receptionistaddexpensescene.fxml"));
        Parent raes = loader.load();

        Scene newScene = new Scene(raes);

        ReceptionistaddexpensesceneController controller = loader.getController();
        controller.initData(r);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }

    
    
}
