package Nonuser;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Expense implements Serializable{
    private String expName, expBranch, date;
    int staffId;
    private Float expAmount;

    public Expense() {
    }

    public Expense(String expName, int staffId, String expBranch, String date, Float expAmount) {
        this.expName = expName;
        this.staffId = staffId;
        this.expBranch = expBranch;
        this.date = date;
        this.expAmount = expAmount;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getExpBranch() {
        return expBranch;
    }

    public void setExpBranch(String expBranch) {
        this.expBranch = expBranch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(Float expAmount) {
        this.expAmount = expAmount;
    }
    
    public boolean addExpense() {
        File f = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        boolean x;
        
        try {
            f = new File("expenses.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }
            
            dos = new DataOutputStream(fos);
            dos.writeUTF(this.getExpName());
            dos.writeFloat(this.getExpAmount());
            dos.writeInt(this.staffId);
            dos.writeUTF(this.getExpBranch());
            dos.writeUTF(this.getDate());
            x = true;
        } catch (IOException iOException) {
            //....
            x=false;
        }
         finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    public static ArrayList<Expense> getExpenseList() {
        ArrayList<Expense> e = new ArrayList<Expense>();
        Expense temp;
        File f = null;
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            f = new File("expenses.bin");
            if(f.exists()){
                fis = new FileInputStream(f);
                dis = new DataInputStream(fis);
                while(true){
                    temp = new Expense();
                    temp.setExpName(dis.readUTF());
                    temp.setExpAmount(dis.readFloat());
                    temp.setStaffId(dis.readInt());
                    temp.setExpBranch(dis.readUTF());
                    temp.setDate(dis.readUTF());
                    e.add(temp);
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(dis != null) dis.close();
                
            } catch (IOException ex) {
                //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return e;
    }
    
}
