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
import javafx.stage.Stage;
import users.Manager;


public class ManagerDashboardController implements Initializable {

    private Manager m;
    void initData(Manager i) {
        m=i;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddExpensesbuttion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Manageraddexpensescene.fxml"));
        Parent maes = loader.load();
        
        Scene newScene = new Scene(maes);
        
        ManageraddexpensesceneController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }


    @FXML
    private void AddDoctorsCommissionbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Manageraddcommissionscene.fxml"));
        Parent macs = loader.load();
        
        Scene newScene = new Scene(macs);
        
        ManageraddcommissionsceneController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void ViewExpenseHistorybutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewexpensesceneManager.fxml"));
        Parent vesm = loader.load();
        
        Scene newScene = new Scene(vesm);
        
        ViewexpensesceneManagerController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void viewAllbillsSceneOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAllBillsManagerScene.fxml"));
        Parent vabas = loader.load();
        
        Scene newScene = new Scene(vabas);
        
        ViewAllBillsManagerSceneController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void viewstatisticsreportbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewStatisticalReportSceneManager.fxml"));
        Parent vsrsm = loader.load();
        
        Scene newScene = new Scene(vsrsm);
        
        ViewStatisticalReportSceneManagerController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }
}