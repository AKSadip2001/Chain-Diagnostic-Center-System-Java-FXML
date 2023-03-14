/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Nonuser.Commission;
import Nonuser.TestBill;
import Nonuser.VisitBill;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import users.Patient;
import users.Receptionist;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class UpdatepatientduesController implements Initializable {

    @FXML
    private TextField patidtextfield;
    @FXML
    private TableView<TestBill> duestable;
    @FXML
    private TableColumn<TestBill, String> billidtextfield;
    @FXML
    private TableColumn<TestBill, String> testnamecol;
    @FXML
    private TableColumn<TestBill, String> duescol;
    @FXML
    private Label text;
    @FXML
    private TextField updatedduestextfield;
    
    ArrayList<Patient> p;
    Patient pSearched;
    ObservableList<TestBill> tbpending;
    

    private Receptionist r;
    
    public void initData(Receptionist i){
        r=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billidtextfield.setCellValueFactory(new PropertyValueFactory<TestBill, String>("billId"));
        testnamecol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("testName"));
        duescol.setCellValueFactory(new PropertyValueFactory<TestBill, String>("due"));
        
        duestable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        p = new ArrayList<Patient>();
        tbpending = FXCollections.observableArrayList();
        pSearched = new Patient();
    }    

    @FXML
    private void SaveOnclick(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure about updating dues?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
        TestBill tbtemp = duestable.getSelectionModel().getSelectedItem();
        pSearched = r.updatedue(pSearched, tbtemp, updatedduestextfield.getText());
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("PatObjects.bin");
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            for(Patient i: p){
                if(i.getId()==Integer.parseInt(updatedduestextfield.getText())){
                    i=pSearched;
                }
                oos.writeObject(i);
            }

        } catch (IOException ex) {
            //Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                //Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        tbpending.clear();
        for(Patient i: p){
            if(i.getId()==Integer.parseInt(patidtextfield.getText())){
                pSearched = i;
                ArrayList<TestBill> temp = i.getPatTestBills();
                for(TestBill j: temp){
                    if(j.getDue()>0){
                        tbpending.add(j);
                    }
                }
                break;
            }
        }
        duestable.setItems(tbpending);
        }
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Dues Updated!");
        a.showAndWait(); 
    }

    @FXML
    private void Backonclick(ActionEvent event) throws IOException {
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

    @FXML
    private void searchonclick(ActionEvent event) {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        try {
            f = new File("patObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    p.add((Patient)ois.readObject());
                }
            }
            catch(IOException | ClassNotFoundException e){
                //...
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        tbpending.clear();
        for(Patient i: p){
            if(i.getId()==Integer.parseInt(patidtextfield.getText())){
                pSearched = i;
                ArrayList<TestBill> temp = i.getPatTestBills();
                for(TestBill j: temp){
                    if(j.getDue()>0){
                        tbpending.add(j);
                    }
                }
                break;
            }
        }
        duestable.setItems(tbpending);
        
    }
    
}
