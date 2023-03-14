package mainpkg;

import Nonuser.Prescription;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Patient;


public class ViewPrescriptionPatientController implements Initializable {

    @FXML
    private TableView<Prescription> prestable;
    @FXML
    private TableColumn<Prescription, String> presNoCol;
    @FXML
    private TableColumn<Prescription, String> docViiaitedCol;
    @FXML
    private TableColumn<Prescription, String> dateCol;

    private Patient p;
    ObservableList<Prescription> pres;
    
    public void initData(Patient i){
        p=i;
        
        presNoCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("presNo"));
        docViiaitedCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("docName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("date"));
        
        pres = FXCollections.observableArrayList();
        pres = p.getPrescriptionList();
        
        
        prestable.setItems(pres);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //No code required
    }

    @FXML
    private void viewPrescriptionOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailedPrescriptionViewScene.fxml"));
        Parent personViewParent = loader.load();
    
        Scene personViewScene = new Scene(personViewParent);
        
        DetailedPrescriptionViewSceneController controller = loader.getController();
        controller.initData(prestable.getSelectionModel().getSelectedItem());
        
        Stage newWindow  = new Stage();
        newWindow.setTitle("Detailed Prescription View");
        newWindow.setScene(personViewScene);
        newWindow.show();
    }

    @FXML
    private void backOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PatientDashboard.fxml"));
        Parent pds = loader.load();

        Scene newScene = new Scene(pds);

        PatientDashboardController controller = loader.getController();
        controller.initData(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }
    
}
