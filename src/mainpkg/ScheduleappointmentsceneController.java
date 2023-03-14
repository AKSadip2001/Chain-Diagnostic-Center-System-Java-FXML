package mainpkg;

import Nonuser.VisitBill;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Doctor;
import users.Patient;
import users.Receptionist;


public class ScheduleappointmentsceneController implements Initializable {

    @FXML
    private ComboBox<String> branchcombobox;
    @FXML
    private TextField billamounttextfield;
    @FXML
    private TextField patidtextfield;
    @FXML
    private ComboBox<String> doctorcombobox;
    @FXML
    private DatePicker date;
    
    private ArrayList<Doctor> d;
    private ArrayList<Patient> p;
    private Receptionist r;
    
    public void initData(Receptionist i){
        r=i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        branchcombobox.getItems().addAll("Mirpur", "Gulshan");
        d = new ArrayList<Doctor>();
        p = new ArrayList<Patient>();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        try {
            f = new File("docObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    d.add((Doctor)ois.readObject());
                }
            }
            catch(IOException | ClassNotFoundException e){
                //Logger.getLogger(ScheduleappointmentsceneController.class.getName()).log(Level.SEVERE, null, e);
            }          
        } catch (IOException ex) { 
            Logger.getLogger(ScheduleappointmentsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
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
                //Logger.getLogger(ScheduleappointmentsceneController.class.getName()).log(Level.SEVERE, null, e);
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ScheduleappointmentsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   

    @FXML
    private void branchcomboboxupdated(ActionEvent event) {
        doctorcombobox.getItems().clear();
        for(Doctor i: d){
            if(i.getDocBranch().equals(branchcombobox.getValue()) && i.getAvailabiltiyStat().equals("Available")){
                doctorcombobox.getItems().add(i.getDocName());
            }
        }
    }
    

    @FXML
    private void doctorcomboxupdated(ActionEvent event) {
        //NO CODE Required
    }

    @FXML
    private void schedulebutton(ActionEvent event) throws FileNotFoundException {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        VisitBill vb = null;
        try {
            f = new File("visitbillsobject.bin");
            if(f.exists()){
                VisitBill vbtemp=null;
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
                try {
                    fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    try{
                        while(true){
                            vbtemp = (VisitBill)ois.readObject();
                        }
                    }
                    catch(IOException | ClassNotFoundException e){
                        //..
                        vb = r.addVisitBill(vbtemp.getBillId()+1, doctorcombobox.getValue(), Float.parseFloat(billamounttextfield.getText()), date.getValue().toString(), branchcombobox.getValue());
                        oos.writeObject(vb);
                    }          
                } catch (IOException ex) { } 
                finally {
                    try {
                        if(ois != null) ois.close();
                    } catch (IOException ex) { }
                }
                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                vb = r.addVisitBill(1000, doctorcombobox.getValue(), Float.parseFloat(billamounttextfield.getText()), date.getValue().toString(), branchcombobox.getValue());
                oos.writeObject(vb);
            }

        } catch (IOException ex) {
            Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for(Patient i: p){
            if(Integer.toString(i.getId()).equals(patidtextfield.getText())){
                i.getPatVisitBills().add(vb);
                for(Doctor j: d){
                    if(j.getDocBranch().equals(branchcombobox.getValue()) && j.getDocName().equals(doctorcombobox.getValue())){
                        r.addAppointment(j, Integer.parseInt(patidtextfield.getText()), i.getPatName(), i.getPatContact(), date.getValue().toString());
                        break;
                    }
                }
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
        
        try {
            f = new File("docObjects.bin");
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            for(Doctor i: d){
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
        a.setContentText("Appointment Scheduled");
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
