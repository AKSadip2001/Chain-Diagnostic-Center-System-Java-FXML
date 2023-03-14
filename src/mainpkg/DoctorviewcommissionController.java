/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Nonuser.Appointment;
import Nonuser.Commission;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Doctor;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class DoctorviewcommissionController implements Initializable {

    @FXML
    private TableColumn<Commission, String> amountcol;
    @FXML
    private TableColumn<Commission, String> datecol;
    @FXML
    private TableColumn<Commission, String> notecol;
    @FXML
    private TableView<Commission> commissiontable;
    @FXML
    private Label text;

    private Doctor d;
    private ArrayList<Commission> c;
    
    public void initData(Doctor i) {
        d = i;
        
        amountcol.setCellValueFactory(new PropertyValueFactory<Commission, String>("comAmount"));
        datecol.setCellValueFactory(new PropertyValueFactory<Commission, String>("comDate"));
        notecol.setCellValueFactory(new PropertyValueFactory<Commission, String>("note"));
        
        c = d.getComList();
        ObservableList<Commission> ctemp = FXCollections.observableArrayList();
        for(Commission j: c){
            ctemp.add(j);
        }
        
        commissiontable.setItems(ctemp);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commissiontable.setSelectionModel(null);
    }    

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DoctorDashboard.fxml"));
        Parent duasParent = loader.load();

        Scene newScene = new Scene(duasParent);

        DoctorDashboardController controller = loader.getController();
        controller.initData(d);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(newScene);
        window.show();
    }
    
}
