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
import users.Laboratorian;


public class ViewpendingreportslaboratoriansceneController implements Initializable {

    @FXML
    private TableView<Test> testtable;
    @FXML
    private TableColumn<Test, String> testidcol;
    @FXML
    private TableColumn<Test, String> testnamecol;
    @FXML
    private TableColumn<Test, String> patidcol;
    @FXML
    private TableColumn<Test, String> datecol;
    @FXML
    private Label branchlabel;
    @FXML
    private Label text;

    private Laboratorian l;
    
    
    public void initData(Laboratorian j){
        l=j;
        branchlabel.setText("Tests of " + l.getLaboratorianBranch() + " Branch");
        
        testidcol.setCellValueFactory(new PropertyValueFactory<Test, String>("testID"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<Test, String>("testName"));
        patidcol.setCellValueFactory(new PropertyValueFactory<Test, String>("patID"));
        datecol.setCellValueFactory(new PropertyValueFactory<Test, String>("date"));
        
        ArrayList<Test> t = new ArrayList<Test>();
        t = l.getReportsPendingList();
        
        if(t.isEmpty()){
            text.setText("No test reports pending Yet");
        }else{
            ObservableList<Test> tlist = FXCollections.observableArrayList();
            for(Test i: t){
                tlist.add(i);
            }
            testtable.setItems(tlist);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        testtable.setSelectionModel(null);
    }

    @FXML
    private void backOnClick(ActionEvent event) throws IOException {
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
