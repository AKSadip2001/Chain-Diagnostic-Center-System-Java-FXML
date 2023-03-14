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
import users.Laboratorian;



public class LaboratorianDashboardController implements Initializable {

    private Laboratorian l;
    
    public void initData(Laboratorian i){
        l=i;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GenerateReportbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GenerateReportLaboratorian.fxml"));
        Parent grls = loader.load();
        Scene newScene = new Scene(grls);
        GenerateReportLaboratorianController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void CheckPendingTestReportsbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Viewpendingreportslaboratorianscene.fxml"));
        Parent vprls = loader.load();
        Scene newScene = new Scene(vprls);
        ViewpendingreportslaboratoriansceneController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void AddLaboratoryExpensesbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Laboratorianaddexpensescene.fxml"));
        Parent laes = loader.load();
        Scene newScene = new Scene(laes);
        LaboratorianaddexpensesceneController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void AcknowledgeSampleCollectionbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateteststatusLaboratorian.fxml"));
        Parent upd = loader.load();
        Scene newScene = new Scene(upd);
        UpdateteststatusLaboratorianController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }

    @FXML
    private void ViewTestsListbutton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewTestListLaboratorian.fxml"));
        Parent vtll = loader.load();
        Scene newScene = new Scene(vtll);
        ViewTestListLaboratorianController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
}
