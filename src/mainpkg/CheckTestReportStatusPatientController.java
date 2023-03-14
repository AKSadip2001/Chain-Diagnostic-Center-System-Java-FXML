package mainpkg;

import Nonuser.Test;
import java.io.IOException;
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
import users.Patient;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class CheckTestReportStatusPatientController implements Initializable {

    @FXML
    private TableView<Test> testtable;
    @FXML
    private TableColumn<Test, String> testidcol;
    @FXML
    private TableColumn<Test, String> testnamecol;
    @FXML
    private TableColumn<Test, String> datecol;
    @FXML
    private TableColumn<Test, String> teststatcol;
    @FXML
    private TableColumn<Test, String> branchcol;

    private Patient p;
    @FXML
    private Label text;
    
    
    public void initData(Patient i){
        p=i;
        
        testidcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testID"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<Test, String>("testName"));
        teststatcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testStat"));
        datecol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
        branchcol.setCellValueFactory(new PropertyValueFactory<Test, String>("branch"));
        
        ArrayList<Test> t = new ArrayList<Test>();
        t = p.getTestReportStatusList();
        
        if(t.isEmpty()){
            text.setText("No test data to display.");
        }else{
            ObservableList<Test> tlist = FXCollections.observableArrayList();
            for(Test j: t){
                tlist.add(j);
            }
            testtable.setItems(tlist);
            text.setText("Displaying all test data.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testtable.setSelectionModel(null);
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
