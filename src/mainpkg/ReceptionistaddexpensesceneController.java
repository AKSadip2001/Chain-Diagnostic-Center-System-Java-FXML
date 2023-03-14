package mainpkg;

import Nonuser.Expense;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import users.Receptionist;

public class ReceptionistaddexpensesceneController implements Initializable {

    @FXML
    private TextField expnametextfield;
    @FXML
    private TextField expamounttextfield;
    @FXML
    private DatePicker date;
    @FXML
    private Label addexplabel;
    
    private Receptionist r;
    private Expense exp;
    
    void initData(Receptionist i) {
        r=i;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void addexponclick(ActionEvent event) {
        exp = new Expense(expnametextfield.getText(), r.getId(), r.getRecptionistBranch(), date.getValue().toString(), Float.parseFloat(expamounttextfield.getText()));
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
    private void backOnclick(ActionEvent event) throws IOException {
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
