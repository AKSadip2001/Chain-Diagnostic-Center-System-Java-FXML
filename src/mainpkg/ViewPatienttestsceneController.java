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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Laboratorian;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class ViewPatienttestsceneController implements Initializable {

    @FXML
    private TableView<Test> testtable;
    @FXML
    private TableColumn<Test, String> testidcol;
    @FXML
    private TableColumn<Test, String> testnamecol;
    @FXML
    private TableColumn<Test, String> teststatcol;
    @FXML
    private TableColumn<Test, String> datecol;
    @FXML
    private TextField patidtextfield;

    private Laboratorian l;
    
    private ObservableList<Test> tlist = FXCollections.observableArrayList();
    private ArrayList<Test> t = new ArrayList<Test>();
    @FXML
    private Label text;
    
    public void initData(Laboratorian i){
        l=i;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testidcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testID"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<Test, String>("testname"));
        teststatcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testStat"));
        datecol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
        
        
    }    

    @FXML
    private void searchOnclick(ActionEvent event) {
        t = l.getPatientTestList(patidtextfield.getText());
        if(t.isEmpty()){
            text.setText("No data available to display.");
        }
        else{
            for(Test i: t){
                tlist.add(i);
            }
            testtable.setItems(tlist);
        }
        
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LaboratorianDashboard.fxml"));
            Parent lds = loader.load();
            Scene newScene = new Scene(lds);
            LaboratorianDashboardController controller = loader.getController();
            controller.initData(l);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
    }
    
}
