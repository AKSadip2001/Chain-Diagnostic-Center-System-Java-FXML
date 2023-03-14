package mainpkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Manager;
import users.Doctor;
import users.Laboratorian;
import users.Receptionist;

public class MainApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Loginscene.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Diagnostic Center");
        stage.setScene(scene);
        stage.show();
        
        //Below are some user data for demo
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        
        try {
            f = new File("docObjects.bin");
            if(!f.exists()){
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);  
                Doctor d1 = new Doctor(201, "123", "DR. RASHID", "Orthopedics", "Mirpur", "Available", "0123456789", "14:00-20:00", "Sun-Sat", 45);
                oos.writeObject(d1);
                Doctor d2 = new Doctor(202, "123", "DR. RAHIM", "Orthopedics", "Gulshan", "Available", "014789415", "12:00-18:00", "Sun-Sat", 50);
                oos.writeObject(d2);
                Doctor d3 = new Doctor(203, "123", "DR. MAHBUB", "Neurology", "Mirpur", "Available", "015841687", "14:00-18:00", "Sun-Sat", 35);
                oos.writeObject(d3);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            f = new File("receptionistObjects.bin");
            if(!f.exists()){
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos); 
                //int id, String password, String recptionistBranch, String staffName, String staffAge, String staffContact, String satffAdd
                Receptionist r1 = new Receptionist(301, "123", "Mirpur", "Mr A", "30", "01845461346", "xyz");
                oos.writeObject(r1);
                Receptionist r2 = new Receptionist(302, "123", "Mirpur", "Mr B", "35", "0176643464", "xyz");
                oos.writeObject(r2);
                Receptionist r3 = new Receptionist(303, "123", "Gulshan", "Mr C", "40", "01848241346", "xyz");
                oos.writeObject(r3);
                Receptionist r4 = new Receptionist(304, "123", "Gulshan", "Mr D", "30", "01944645666", "xyz");
                oos.writeObject(r4);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        try {
            f = new File("laboratorianObjects.bin");
            if(!f.exists()){
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                Laboratorian l1 = new Laboratorian(401, "123", "Mirpur", "Mr X", "30", "01845461346", "xyz");
                oos.writeObject(l1);
                Laboratorian l2 = new Laboratorian(402, "123", "Mirpur", "Mr Y", "35", "0176643464", "xyz");
                oos.writeObject(l2);
                Laboratorian l3 = new Laboratorian(403, "123", "Gulshan", "Mr Z", "40", "01848241346", "xyz");
                oos.writeObject(l3);
                Laboratorian l4 = new Laboratorian(404, "123", "Gulshan", "Mr W", "30", "01944645666", "xyz");
                oos.writeObject(l4);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            f = new File("managerObjects.bin");
            if(!f.exists()){
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos); 
                //int id, String password, String laboratorianBranch, String staffName, String staffAge, String staffContact, String satffAdd
                Manager m1 = new Manager(501, "123", "Mr Alpha", "30", "01845461346", "xyz", "Senior Manager");
                oos.writeObject(m1);
                Manager m2 = new Manager(502, "123", "Mr Beta", "35", "0176643464", "xyz", "Junior Manager");
                oos.writeObject(m2);
            }

        } catch (IOException ex) {
            Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(LoginsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
