package mainpkg;

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
import users.Doctor;
import users.Patient;


public class ViewdoctorlistsceneController implements Initializable {

    @FXML
    private TableView<Doctor> doctableview;
    @FXML
    private TableColumn<Doctor, String> docnamecol;
    @FXML
    private TableColumn<Doctor, String> docspecialitycol;
    @FXML
    private TableColumn<Doctor, String> docbranchcol;
    @FXML
    private TableColumn<Doctor, String> docvhcol;
    @FXML
    private TableColumn<Doctor, String> docvdcol;

    private Patient p;
    
    public void initData(Patient i){
        p=i;
        docnamecol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docName"));
        docspecialitycol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docSpeciality"));
        docbranchcol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("docBranch"));
        docvhcol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("visitingHour"));
        docvdcol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("visitingDays"));
    
        doctableview.setItems(getpeople());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        doctableview.setSelectionModel(null);
    }    
    
    
    public ObservableList<Doctor> getpeople(){
        ObservableList<Doctor> d = FXCollections.observableArrayList();
        d = p.viewDoclist();
        
        return d;
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
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
