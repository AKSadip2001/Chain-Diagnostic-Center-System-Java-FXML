package mainpkg;


import Nonuser.TestBill;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Patient;
import users.Receptionist;


public class AddpatientTestsceneController implements Initializable {

    @FXML
    private TextField billduetextfield;
    @FXML
    private ComboBox<String> branchcombobox;
    @FXML
    private DatePicker date;
    @FXML
    private TextField billamounttextfield;
    @FXML
    private TextField patidtextfield;
    @FXML
    private ComboBox<String> testcombobox;
    @FXML
    private Button addtestbutton;
    @FXML
    private Label confirmationlabel;

    
    
    private Receptionist r;
    
    
    @FXML
    private Button addtestbutton1;
    
    public void initData(Receptionist i){
        r=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombobox.getItems().addAll("Mirpur", "Gulshan");
        testcombobox.getItems().addAll("Angiography", "Ultrasonography", "MRI", "Cardiac Test");
    }    

    @FXML
    private void addtestonclick(ActionEvent event) {
        ArrayList<Patient> p;
        ArrayList<TestBill> tblist;
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        p = new ArrayList<Patient>();
        
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
                // This ensures program will not crash and no code will be excuted in case of error
            }          
        } catch (IOException ex) {
            // This ensures program will not crash and no code will be excuted in case of error
        } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        tblist = TestBill.getTestBills();
        
        TestBill temptb = new TestBill();
        try {
            f = new File("testbillsobject.bin");
            if(!tblist.isEmpty()){
                temptb = r.addTestBill(tblist.get(tblist.size()-1).getBillId()+1, Float.parseFloat(billduetextfield.getText()),testcombobox.getValue(), Float.parseFloat(billamounttextfield.getText()), date.getValue().toString(), branchcombobox.getValue());
                tblist.add(temptb);
            }
            else{
                temptb = r.addTestBill(1100, Float.parseFloat(billduetextfield.getText()),testcombobox.getValue(), Float.parseFloat(billamounttextfield.getText()), date.getValue().toString(), branchcombobox.getValue());
                tblist.add(temptb);
            }
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for(TestBill i: tblist){
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
        
        r.addtest(Integer.parseInt(patidtextfield.getText()), date.getValue().toString(), testcombobox.getValue(), "Test Pending", branchcombobox.getValue());
        
        for(Patient i: p){
            if(Integer.toString(i.getId()).equals(patidtextfield.getText())){
                i.getPatTestBills().add(temptb);
                break;
            }
        }
        
        try {
            f = new File("PatObjects.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);               
            for(Patient i: p){
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
        
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Test Added");
        a.showAndWait(); 
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
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
