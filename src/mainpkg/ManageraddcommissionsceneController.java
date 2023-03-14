package mainpkg;

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
import users.Manager;

public class ManageraddcommissionsceneController implements Initializable {

    @FXML
    private TextField comamounttextfield;
    @FXML
    private DatePicker datetextfield;
    @FXML
    private TextField notetextfield;
    @FXML
    private Label confirmlabel;
    @FXML
    private TextField docidtextfield;

    private Manager m;
    
    public void initData(Manager i){
        m=i;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcommissiononclick(ActionEvent event) {
        m.addDocCommission(docidtextfield.getText(), Float.parseFloat(comamounttextfield.getText()), datetextfield.getValue().toString(), notetextfield.getText());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Commission Added!");
        a.showAndWait(); 
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
