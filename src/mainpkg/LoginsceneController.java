package mainpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Manager;
import users.Doctor;
import users.Laboratorian;
import users.Patient;
import users.Receptionist;

public class LoginsceneController implements Initializable {

    @FXML private PasswordField userpasswordtextfield;
    @FXML private ComboBox<String> usercombobox;
    @FXML private TextField useridtextfield;
    @FXML
    private Label loginfailtextfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usercombobox.getItems().addAll("Receptionist", "Doctor", "Patient", "Laboratorian", "Manager");
    }    

    @FXML
    private void comboxboxupdated(ActionEvent event) {
        
    }

    @FXML
    private void loginonclick(ActionEvent event) throws IOException {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        if(usercombobox.getValue().equals("Doctor")){
            ArrayList<Doctor> d = new ArrayList<Doctor>();
            try {
                f = new File("docObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        d.add((Doctor)ois.readObject());        
                    }
                }
            catch(IOException | ClassNotFoundException e){}          
            } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { 
                }
            }
            
            for(Doctor i: d){
                if(i.verifyUser(Integer.parseInt(useridtextfield.getText()), userpasswordtextfield.getText())){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("DoctorDashboard.fxml"));
                    Parent dds = loader.load();

                    Scene docdash = new Scene(dds);

                    DoctorDashboardController controller = loader.getController();
                    controller.initData(i);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(docdash);
                    window.show();
                    break;
                }
                else{
                    loginfailtextfield.setText("Incorrect ID or Password");
                }
            }
        }
        
        else if(usercombobox.getValue().equals("Patient")){
            ArrayList<Patient> p = new ArrayList<Patient>();
            try {
                f = new File("patObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        p.add((Patient)ois.readObject());        
                    }
                }
            catch(IOException | ClassNotFoundException e){}          
            } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { 
                }
            }
            
            for(Patient i: p){
                if(i.verifyUser(Integer.parseInt(useridtextfield.getText()), userpasswordtextfield.getText())){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("PatientDashboard.fxml"));
                    Parent pds = loader.load();

                    Scene newScene = new Scene(pds);

                    PatientDashboardController controller = loader.getController();
                    controller.initData(i);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(newScene);
                    window.show();
                    break;
                }
                else{
                    loginfailtextfield.setText("Incorrect ID or Password");
                }
            }
        }
        
        else if(usercombobox.getValue().equals("Receptionist")){
            ArrayList<Receptionist> r = new ArrayList<Receptionist>();
            try {
                f = new File("receptionistObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        r.add((Receptionist)ois.readObject());        
                    }
                }
            catch(IOException | ClassNotFoundException e){}          
            } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { 
                }
            }
            
            for(Receptionist i: r){
                if(i.verifyUser(Integer.parseInt(useridtextfield.getText()), userpasswordtextfield.getText())){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ReceptionistDashboard.fxml"));
                    Parent rds = loader.load();

                    Scene newScene = new Scene(rds);

                    ReceptionistDashboardController controller = loader.getController();
                    controller.initData(i);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(newScene);
                    window.show();
                    break;
                }
                else{
                    loginfailtextfield.setText("Incorrect ID or Password");
                }
            }
        }
        
        else if(usercombobox.getValue().equals("Laboratorian")){
            ArrayList<Laboratorian> l = new ArrayList<Laboratorian>();
            try {
                f = new File("laboratorianObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        l.add((Laboratorian)ois.readObject());        
                    }
                }
            catch(IOException | ClassNotFoundException e){}          
            } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { 
                }
            }
            
            for(Laboratorian i: l){
                if(i.verifyUser(Integer.parseInt(useridtextfield.getText()), userpasswordtextfield.getText())){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("LaboratorianDashboard.fxml"));
                    Parent lds = loader.load();

                    Scene newScene = new Scene(lds);

                    LaboratorianDashboardController controller = loader.getController();
                    controller.initData(i);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(newScene);
                    window.show();
                    break;
                }
                else{
                    loginfailtextfield.setText("Incorrect ID or Password");
                }
            }
        }
        
        else if(usercombobox.getValue().equals("Manager")){
            ArrayList<Manager> m = new ArrayList<Manager>();
            try {
                f = new File("managerObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        m.add((Manager)ois.readObject());        
                    }
                }
            catch(IOException | ClassNotFoundException e){}          
            } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { 
                }
            }
            
            for(Manager i: m){
                if(i.verifyUser(Integer.parseInt(useridtextfield.getText()), userpasswordtextfield.getText())){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ManagerDashboard.fxml"));
                    Parent lds = loader.load();

                    Scene newScene = new Scene(lds);

                    ManagerDashboardController controller = loader.getController();
                    controller.initData(i);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(newScene);
                    window.show();
                    break;
                }
                else{
                    loginfailtextfield.setText("Incorrect ID or Password");
                }
            }
        }
        
    }
    
}
