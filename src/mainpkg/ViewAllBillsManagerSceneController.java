/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Nonuser.TestBill;
import Nonuser.VisitBill;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Manager;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class ViewAllBillsManagerSceneController implements Initializable {

    @FXML
    private ComboBox<String> branchcombox;
    @FXML
    private ComboBox<String> billtypecombobox;
    @FXML
    private TableView<VisitBill> vbtable;
    @FXML
    private TableColumn<VisitBill, String> vbbillidcol;
    @FXML
    private TableColumn<VisitBill, String> doccol;
    @FXML
    private TableColumn<VisitBill, String> vbbillcol;
    @FXML
    private TableColumn<VisitBill, String> vddatecol;
    @FXML
    private TableView<TestBill> tbtable;
    @FXML
    private TableColumn<TestBill, String> tbbillidcol;
    @FXML
    private TableColumn<TestBill, String> tbtestnamecol;
    @FXML
    private TableColumn<TestBill, String> tbbillcol;
    @FXML
    private TableColumn<TestBill, String> tbduecol;
    @FXML
    private TableColumn<TestBill, String> tbdatecol;
    
    private ObservableList<VisitBill> vb = FXCollections.observableArrayList();
    private ObservableList<TestBill> tb = FXCollections.observableArrayList();
    private ArrayList<TestBill> tblist;
    private ArrayList<VisitBill> vblist;
    
    private Manager m;
    
    public void initData(Manager i){
        m=i;
        
        tblist = new ArrayList<TestBill>();
        vblist = new ArrayList<VisitBill>();
        
        tblist = TestBill.getTestBills();
        vblist = VisitBill.getVisitBills();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombox.getItems().addAll("Mirpur", "Gulshan");
        billtypecombobox.getItems().addAll("Visit Bill", "Test Bill");
        
        vbbillidcol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("billId"));
        doccol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("docName"));
        vbbillcol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("billAmount"));
        vddatecol.setCellValueFactory(new PropertyValueFactory<VisitBill, String>("billDate"));
        
        tbtestnamecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("testName"));
        tbbillidcol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billId"));
        tbbillcol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billAmount"));
        tbduecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("due"));
        tbdatecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billDate"));
        
    }    

    @FXML
    private void viewtblonclick(ActionEvent event) {
        vbtable.setVisible(false);
        tbtable.setVisible(false);
        tb.clear();
        vb.clear();
        
        if(billtypecombobox.getValue().equals("Test Bill")){
            if(branchcombox.getValue().equals("Mirpur")){
                for(TestBill i: tblist){
                    if(i.getBranch().equals("Mirpur")){
                        tb.add(i);
                    }
                }
            }
            else if(branchcombox.getValue().equals("Gulshan")){
                for(TestBill i: tblist){
                    if(i.getBranch().equals("Gulshan")){
                        tb.add(i);
                    }
                }
            }
            tbtable.setItems(tb);
            tbtable.setVisible(true);
            
        }
        else if(billtypecombobox.getValue().equals("Visit Bill")){
            if(branchcombox.getValue().equals("Mirpur")){
                for(VisitBill i: vblist){
                    if(i.getBranch().equals("Mirpur")){
                        vb.add(i);
                    }
                }
            }
            else if(branchcombox.getValue().equals("Gulshan")){
                for(VisitBill i: vblist){
                    if(i.getBranch().equals("Gulshan")){
                        vb.add(i);
                    }
                }
            }
            vbtable.setItems(vb);
            vbtable.setVisible(true);
        }
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerDashboard.fxml"));
        Parent rds = loader.load();
        
        Scene newScene = new Scene(rds);
        
        ManagerDashboardController controller = loader.getController();
        controller.initData(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(newScene);
        window.show();
    }
    
}
