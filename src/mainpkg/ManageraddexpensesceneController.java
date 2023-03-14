package mainpkg;

import Nonuser.Expense;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Manager;


public class ManageraddexpensesceneController implements Initializable {

    @FXML private TextField expnametextfield;
    @FXML private TextField expamounttextfield;
    @FXML private DatePicker expdatetextfield;
    @FXML private ComboBox<String> branchcombobox;
    
    private Expense exp;
    private Manager m;
    
    public void initData(Manager i){
        m=i;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombobox.getItems().addAll("Mirpur", "Gulshan");
    }    

    @FXML
    private void addexponclick(ActionEvent event) {
        //String expName, int staffId, String expBranch, String date, Float expAmount
        exp = new Expense(expnametextfield.getText(), m.getId(), branchcombobox.getValue(), expdatetextfield.getValue().toString(), Float.parseFloat(expamounttextfield.getText()));
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(exp.addExpense()) {
                a.setContentText("Expense Added!");
                a.showAndWait(); 
        }
        else {
            a.setContentText("Expense Not added due to error");
            a.showAndWait();
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
