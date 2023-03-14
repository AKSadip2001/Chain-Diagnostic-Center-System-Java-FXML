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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Laboratorian;


public class LaboratorianaddexpensesceneController implements Initializable {

    @FXML private TextField expnametextfield;
    @FXML private TextField expamounttextfield;
    @FXML private DatePicker expdatetextfield;
    
    private Expense exp;
    private Laboratorian l;
    
    
    public void initData(Laboratorian i){
        l=i;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }     

    @FXML
    private void addexponclick(ActionEvent event) {
        exp = new Expense(expnametextfield.getText(), l.getId(), l.getLaboratorianBranch(), expdatetextfield.getValue().toString(), Float.parseFloat(expamounttextfield.getText()));
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        if(exp.addExpense()){
            a.setContentText("Expense Added!");
            a.showAndWait(); 
        }
        else {
            a.setContentText("Expense not added due to error");
            a.showAndWait(); 
        }
    }

    @FXML
    private void backOnclick(ActionEvent event) throws IOException {
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
