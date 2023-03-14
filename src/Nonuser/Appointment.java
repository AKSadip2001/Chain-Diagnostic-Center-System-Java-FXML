package Nonuser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author shadi
 */
public class Appointment implements Serializable{
    private int patID;
    private String patName, patContact, date;

    public Appointment() {
    }

    public Appointment(int patID, String patName, String patContact, String date) {
        this.patID = patID;
        this.patName = patName;
        this.patContact = patContact;
        this.date = date;
    }

    public int getPatID() {
        return patID;
    }

    public void setPatID(int patID) {
        this.patID = patID;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getPatContact() {
        return patContact;
    }

    public void setPatContact(String patContact) {
        this.patContact = patContact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAppointment() {
        File f = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            f = new File("appointments.bin");
            if(f.exists()) fos = new FileOutputStream(f,true);
            else fos = new FileOutputStream(f);
            dos = new DataOutputStream(fos);

            dos.writeInt(this.patID);
            dos.writeUTF(this.patName);
            dos.writeUTF(this.patContact);
            dos.writeUTF(this.date);

        } catch (IOException ex) {
            //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
