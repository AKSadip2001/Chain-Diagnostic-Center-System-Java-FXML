/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import users.Doctor;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class DoctorupdateavailabilitysceneController implements Initializable {

    @FXML
    private RadioButton availableradbut;
    @FXML
    private RadioButton notavailableradbut;

    private ToggleGroup tg;
    private String s;
    private Doctor d;
    
    
    public void initData(Doctor i) {
        d = i;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();
        availableradbut.setToggleGroup(tg);
        notavailableradbut.setToggleGroup(tg);
        
        availableradbut.setSelected(true);
    }    

    @FXML
    private void saveOnclick(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure about the changes?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){
            if(availableradbut.isSelected()) s="Available";
            else if(notavailableradbut.isSelected()) s="Not Avaiable";

            ArrayList<Doctor> dtemp = new ArrayList<Doctor>();
            File f = null;
            FileInputStream fis = null;      
            ObjectInputStream ois = null;
            try {
                f = new File("docObjects.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        dtemp.add((Doctor)ois.readObject());
                    }
                }
                catch(IOException | ClassNotFoundException e){
                    //
                }          
            } catch (IOException ex) { } 
            finally {
                try {
                    if(ois != null) ois.close();
                } catch (IOException ex) { }
            }

            d.updateAvaibilityStat(dtemp, s);
            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Changes Saved!");
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
