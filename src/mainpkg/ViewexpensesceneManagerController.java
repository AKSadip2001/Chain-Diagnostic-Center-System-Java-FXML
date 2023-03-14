
package mainpkg;

import Nonuser.Expense;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Manager;


public class ViewexpensesceneManagerController implements Initializable {

    @FXML
    private ComboBox<String> branchcombobox;
    @FXML
    private TableView<Expense> exptable;
    @FXML
    private TableColumn<Expense, String> expnamecol;
    @FXML
    private TableColumn<Expense, String> expamountcol;
    @FXML
    private TableColumn<Expense, String> staffidcol;
    @FXML
    private TableColumn<Expense, String> datecol;
    @FXML
    private Label text;
    
    private Manager m;
    
    public void initData(Manager i){
        m=i;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombobox.getItems().addAll("Mirpur", "Gulshan");
        
        expnamecol.setCellValueFactory(new PropertyValueFactory<Expense, String>("expName"));
        expamountcol.setCellValueFactory(new PropertyValueFactory<Expense, String>("expAmount"));
        staffidcol.setCellValueFactory(new PropertyValueFactory<Expense, String>("staffId"));
        datecol.setCellValueFactory(new PropertyValueFactory<Expense, String>("date"));
    }    

    @FXML
    private void branchexpview(ActionEvent event) {
        ArrayList<Expense> exp = m.getExpList(branchcombobox.getValue());
        ObservableList<Expense> e = FXCollections.observableArrayList();
        
        for(Expense i: exp){
            e.add(i);
        }
        
        if(e.isEmpty()){
            exptable.setItems(e);
            text.setText("No Expenses Added Yet");
        }
        else{
            exptable.setItems(e);
            text.setText("Displaying Expenses of " + branchcombobox.getValue() +" Branch");
        }
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerDashboard.fxml"));
        Parent ads = loader.load();
        
        Scene newScene = new Scene(ads);
        
        ManagerDashboardController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }
    
}
