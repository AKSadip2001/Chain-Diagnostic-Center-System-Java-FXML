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


public class UpdateteststatusLaboratorianController implements Initializable {

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
    private Label branchlabel;

    private Laboratorian l;
    @FXML
    private Label infolabel;
    @FXML
    private TextField patidtextfield;
    
    private ObservableList<Test> tlist = FXCollections.observableArrayList();
    private ArrayList<Test> t = new ArrayList<Test>();
    
    
    public void initData(Laboratorian j){
        l=j;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testidcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testID"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<Test, String>("testName"));
        teststatcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testStat"));
        datecol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
        
    }    

    @FXML
    private void updateteststatOnclck(ActionEvent event) {
        Test temp = testtable.getSelectionModel().getSelectedItem();
        l.updateTestStat(temp);
        tlist.clear();
        for(Test i: t){
            if(i.getTestID()==temp.getTestID()){
                i.setTestStat("Test Done");
            }
            tlist.add(i);
        }
        testtable.setItems(tlist);
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

    @FXML
    private void searchonclick(ActionEvent event) {
        tlist.clear();
        t = l.getPatientTestList(patidtextfield.getText());
        if(t.isEmpty()){
            infolabel.setText("No data available to display.");
        }
        else{
            for(Test i: t){
                tlist.add(i);
            }
            testtable.setItems(tlist);
            infolabel.setText("*Select a row and click on Test Done button to update");
        }
    }
    
}
