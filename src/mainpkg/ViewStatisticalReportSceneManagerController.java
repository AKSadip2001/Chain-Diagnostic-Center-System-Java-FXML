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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import users.Manager;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class ViewStatisticalReportSceneManagerController implements Initializable {

    @FXML
    private ComboBox<String> branchcombobox;
    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private NumberAxis yaxis;
    @FXML
    private CategoryAxis xaxis;
    
    /*private ArrayList<Doctor> dtemp;
    private ArrayList<VisitBill> vblist;
    private ArrayList<TestBill> tblist;*/
    private float vb, tb, exp, doccom;
    private Manager m;
    private XYChart.Series<String,Number> series;
    
    public void initData(Manager i){
        m=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombobox.getItems().addAll("Mirpur", "Gulshan");
    }    

    @FXML
    private void viewOnclick(ActionEvent event) {
        vb=tb=exp=doccom=0;
        Float[] data= new Float[4];
        data = m.getStatisticalData(branchcombobox.getValue());
        doccom = data[0];
        vb = data[1];
        tb = data[2];
        exp = data[3];
        
        barchart.getData().clear();
        series = new XYChart.Series<String,Number>();
        series.setName("Till Date Records");
        series.getData().add(new XYChart.Data<String,Number>("Visit Bills", vb));
        series.getData().add(new XYChart.Data<String,Number>("Test Bills", tb));
        series.getData().add(new XYChart.Data<String,Number>("Expense", exp));
        series.getData().add(new XYChart.Data<String,Number>("Doctor Commission", doccom));
        barchart.getData().add(series);
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
