package mainpkg;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Doctor;
import users.Patient;


public class CreatePatientprescriptionDoctorsceneController implements Initializable {

    @FXML
    private Label searchresultinfo;
    @FXML
    private TextField diseasenamefield;
    @FXML
    private TextField mednamefield;
    @FXML
    private TextField testnamefield;
    @FXML
    private Label mednamelabel;
    @FXML
    private Label testnamelabel;
    @FXML
    private TextField patidfield;
    
    private Doctor d;
    @FXML
    private DatePicker presdate;
    
    
    
    public void initData(Doctor i) {
        d = i;
    }
    
    private ArrayList<String> medName;
    private ArrayList<String> testName;
    private ArrayList<Patient> p;
    private Patient ptemp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchresultinfo.setVisible(false);
        mednamelabel.setVisible(false);
        testnamelabel.setVisible(false);
        this.medName = new ArrayList<String>();
        this.testName = new ArrayList<String>();
        p = new ArrayList<Patient>();
        ptemp = new Patient();
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
            } catch (IOException ex) {
                //...
            }
        }
        
        
    }    


    @FXML
    private void searchPatonClick(ActionEvent event) {
        for(Patient i: p){
            if(i.getId()==Integer.parseInt(patidfield.getText())){
                ptemp = i;
                searchresultinfo.setText("Patient Name: " + i.getPatName()
                                        + ",      Patient Age: "+ i.getPatAge()
                                        + ",      Patient gender: "+ i.getPatGender());
                searchresultinfo.setVisible(true);
                return;
            }
        }
        searchresultinfo.setText("Patient Not Found");
        searchresultinfo.setVisible(true);
        patidfield.clear();
    }

    @FXML
    private void addmedonclick(ActionEvent event) {
        medName.add(mednamefield.getText());
        mednamefield.clear();
        String s="";
        int i;
        for(i=0; i<medName.size()-1; i++){
            s += medName.get(i)+", ";
        }
        s+= medName.get(i);
        if(s!=null) mednamelabel.setText(s);
        mednamelabel.setVisible(true);
    }

    @FXML
    private void addtestonclick(ActionEvent event) {
        testName.add(testnamefield.getText());
        testnamefield.clear();
        String s="";
        int i;
        for(i=0; i<testName.size()-1; i++){
            s += testName.get(i)+", ";
        }
        s+= testName.get(i);
        if(s!=null) testnamelabel.setText(s);
        testnamelabel.setVisible(true);
    }

    @FXML
    private void createpresOnclick(ActionEvent event) {
        if(!patidfield.getText().isEmpty()){
            d.addprescription(ptemp, diseasenamefield.getText(), medName, testName, presdate.getValue().toString());

            File f = null;
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
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
            a.setContentText("Prescription Created!");
            a.showAndWait(); 
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Patient is not Selected");
            a.showAndWait(); 
        }
    }

    @FXML
    private void backOnclick(ActionEvent event) throws IOException {
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
