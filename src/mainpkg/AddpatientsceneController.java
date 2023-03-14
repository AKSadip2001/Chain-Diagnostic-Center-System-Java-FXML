package mainpkg;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Patient;
import users.Receptionist;


public class AddpatientsceneController implements Initializable {
    
    @FXML
    private TextField patcontacttextfield;
    @FXML
    private TextField pataddresstextfield;
    @FXML
    private TextField patagetextfield;
    @FXML
    private TextField patnametextfield;
    @FXML
    private TableView<Patient> pattableview;
    @FXML
    private TableColumn<Patient, String> patidcol;
    @FXML
    private TableColumn<Patient, String> patnamecol;
    @FXML
    private TableColumn<Patient, String> patcontactcol;
    @FXML
    private ComboBox<String> patgendercombobox;

    private Receptionist r;
    
    public void initData(Receptionist i){
        r=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        patidcol.setCellValueFactory(new PropertyValueFactory<Patient, String>("id"));
        patnamecol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patName"));
        patcontactcol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patContact"));
        
        patgendercombobox.getItems().addAll("Male", "Female");
    
    }    

    @FXML
    private void addpatientonclick(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Do you want to add the Patient?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            Patient p = r.addPatient(patnametextfield.getText(),
                        patcontacttextfield.getText(),
                        pataddresstextfield.getText(),
                        patgendercombobox.getValue(),
                        Integer.parseInt(patagetextfield.getText()));
            pattableview.getItems().add(p);
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Patient ADDED!\n\nPatient ID: "+p.getId());
            a.showAndWait(); 
        }
        
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReceptionistDashboard.fxml"));
        Parent rds = loader.load();

        Scene newScene = new Scene(rds);

        ReceptionistDashboardController controller = loader.getController();
        controller.initData(r);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }
    
}
