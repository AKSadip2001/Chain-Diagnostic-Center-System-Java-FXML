package mainpkg;

import Nonuser.Appointment;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Doctor;


public class ViewappointmentlistdoctorsceneController implements Initializable {

    @FXML
    private TableView<Appointment> appointmenttable;
    @FXML
    private TableColumn<Appointment, String> patidcol;
    @FXML
    private TableColumn<Appointment, String> patnamecol;
    @FXML
    private TableColumn<Appointment, String> patcontactcol;
    @FXML
    private TableColumn<Appointment, String> datecol;
    @FXML
    private Label text;
    
    private Doctor d;
    
    public void initData(Doctor dpassed) {
        d = dpassed;
        patidcol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patID"));
        patnamecol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patName"));
        patcontactcol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("patContact"));
        datecol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("date"));
        
        ObservableList<Appointment> a = FXCollections.observableArrayList();
        
        for(Appointment i: d.getAppList()){
            a.add(i);
        }
        
        appointmenttable.setItems(a);
        System.out.println(d.getId());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmenttable.setSelectionModel(null);
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
