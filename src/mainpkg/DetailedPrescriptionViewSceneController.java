/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpkg;

import Nonuser.Prescription;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author shadi
 */
public class DetailedPrescriptionViewSceneController implements Initializable {
    
    @FXML
    private Label precriptionNolabel;
    @FXML
    private Label docnamelabel;
    @FXML
    private Label diseasenamelabel;
    @FXML
    private Label mednamelabel;
    @FXML
    private Label testnamelabel;
    
    Prescription pres;
    
    void initData(Prescription i) {
        pres=i;
        precriptionNolabel.setText("Precription Number "+i.getPresNo());
        docnamelabel.setText(i.getDocName());
        diseasenamelabel.setText(i.getDiseaseName());
        String str = "";
        int j;
        for(j=0; j<i.getMedName().size()-1; j++){
            str+= i.getMedName().get(j) + ", ";
        }
        str += i.getMedName().get(j);
        mednamelabel.setText(str);
        str = "";
        for(j=0; j<i.getTestName().size()-1; j++){
            str+= i.getTestName().get(j) + ", ";
        }
        str += i.getTestName().get(j);
        testnamelabel.setText(str);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
