package mainpkg;

import Nonuser.TestBill;
import Nonuser.VisitBill;
import java.io.IOException;
import java.net.URL;
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
import users.Patient;


public class CheckbillscenepatientController implements Initializable {

    @FXML
    private TableView<VisitBill> vbtable;
    @FXML
    private TableColumn<VisitBill, String> doccol;
    @FXML
    private TableColumn<VisitBill, String> vbbranchcol;
    @FXML
    private TableColumn<VisitBill, String> vbcol;
    @FXML
    private TableColumn<VisitBill, String> vddate;
    @FXML
    private TableView<TestBill> tbtable;
    @FXML
    private TableColumn<TestBill, String> tbtestcol;
    @FXML
    private TableColumn<TestBill, String> tbbranch;
    @FXML
    private TableColumn<TestBill, String> tbbillcol;
    @FXML
    private TableColumn<TestBill, String> tbduecol;
    @FXML
    private TableColumn<TestBill, String> tbdatecol;

    @FXML
    private Label text1;
    @FXML
    private Label text2;
    
    private Patient p;
    
    public void initData(Patient i){
        p=i;
        
        doccol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("docName"));
        vbbranchcol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("branch"));
        vbcol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("billAmount"));
        vddate.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("billDate"));
        
        tbtestcol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("testName"));
        tbbranch.setCellValueFactory(new PropertyValueFactory<TestBill, String>("branch"));
        tbbillcol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billAmount"));
        tbduecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("due"));
        tbdatecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billDate"));
        
        ObservableList<VisitBill> vb = FXCollections.observableArrayList();
        ObservableList<TestBill> tb = FXCollections.observableArrayList();
        vb =  p.getVisitBillsList();
        tb = p.getPatTestBillslist();
        
        vbtable.setItems(vb);
        tbtable.setItems(tb);
        
        if(vb.isEmpty()) text1.setText("Displaying Visit Bill Data");
        if(tb.isEmpty()) text2.setText("Displaying Test BIll Data");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbtable.setSelectionModel(null);
        tbtable.setSelectionModel(null);
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PatientDashboard.fxml"));
        Parent pds = loader.load();

        Scene newScene = new Scene(pds);

        PatientDashboardController controller = loader.getController();
        controller.initData(p);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }
    
}
