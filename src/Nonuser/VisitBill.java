package Nonuser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class VisitBill extends Bill implements Serializable{
    private String docName;

    public VisitBill() {
    }

    public VisitBill(String docName) {
        this.docName = docName;
    }

    public VisitBill(int billId, String docName, Float billAmount, String billDate, String branch) {
        super(billId, billAmount, billDate, branch);
        this.docName = docName;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    //Implemented Methods
    
    public VisitBill setVisitBill(int billId, String docname, Float amount, String date, String branch) {
        this.setBill(billId, amount, date, branch);
        this.docName = docname;
        
        return this;
    }
    
    public static ArrayList<VisitBill> getVisitBills() {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        ArrayList<VisitBill> vblist = new ArrayList<VisitBill>();
        
        try {
                f = new File("visitbillsobject.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        vblist.add((VisitBill)ois.readObject());
                    }
                }
                catch(IOException | ClassNotFoundException e){
                    //..
                }          
            } catch (IOException ex) {
                    //.. 
            }
            finally {
                try {
                    if(ois != null) ois.close();
                } catch (IOException ex) { 
                    //..
                }
            }
        return vblist;
    }
    
}
