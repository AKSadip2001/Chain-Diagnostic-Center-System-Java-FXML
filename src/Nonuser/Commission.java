package Nonuser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class Commission implements Serializable{
    private Float comAmount;
    private String comDate, note;

    public Commission() {
    }

    public Commission(Float comAmount, String comDate, String note) {
        this.comAmount = comAmount;
        this.comDate = comDate;
        this.note = note;
    }

    public Float getComAmount() {
        return comAmount;
    }

    public void setComAmount(Float comAmount) {
        this.comAmount = comAmount;
    }

    public String getComDate() {
        return comDate;
    }

    public void setComDate(String comDate) {
        this.comDate = comDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void addCommission() {
        File f = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        
        try {
            f = new File("docCommission.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            
            dos = new DataOutputStream(fos);
            dos.writeFloat(this.getComAmount());
            dos.writeUTF(this.getComDate());
            dos.writeUTF(this.getNote());

        } catch (IOException ex) {
            //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
                //confirmlabel.setText("Commission Added");
            } catch (IOException ex) {
                //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
