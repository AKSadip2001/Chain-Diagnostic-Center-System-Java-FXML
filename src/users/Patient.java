package users;


import Nonuser.Prescription;
import Nonuser.Test;
import Nonuser.TestBill;
import Nonuser.VisitBill;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Patient extends userclass implements Serializable{
    private String patName, patContact, patAdd, patGender;
    private int patAge;
    private ArrayList<Prescription> patPrescription;
    private ArrayList<VisitBill> patVisitBills;
    private ArrayList<TestBill> patTestBills;
    
    
    public Patient() {
    }

    public Patient(int id, String password, String patName, String patContact, String patAdd, String patGender, int patAge) {
        super(id, password);
        this.patName = patName;
        this.patContact = patContact;
        this.patAdd = patAdd;
        this.patGender = patGender;
        this.patAge = patAge;
        this.patVisitBills = new ArrayList<VisitBill>();
        this.patTestBills = new ArrayList<TestBill>();
        this.patPrescription = new ArrayList<Prescription>();
    }

    public ArrayList<VisitBill> getPatVisitBills() {
        return patVisitBills;
    }

    public ArrayList<Prescription> getPatPrescription() {
        return patPrescription;
    }

    public void setPatPrescription(ArrayList<Prescription> patPrescription) {
        this.patPrescription = patPrescription;
    }

    

    
    
    public void setPatVisitBills(ArrayList<VisitBill> patVisitBills) {
        this.patVisitBills = patVisitBills;
    }

    public ArrayList<TestBill> getPatTestBills() {
        return patTestBills;
    }

    public void setPatTestBills(ArrayList<TestBill> patTestBills) {
        this.patTestBills = patTestBills;
    }

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
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

    public String getPatAdd() {
        return patAdd;
    }

    public void setPatAdd(String patAdd) {
        this.patAdd = patAdd;
    }

    public int getPatAge() {
        return patAge;
    }

    public void setPatAge(int patAge) {
        this.patAge = patAge;
    }

    @Override
    public String toString() {
        return "Patient{" + "patName=" + patName + ", patContact=" + patContact + ", patAdd=" + patAdd + ", patAge=" + patAge + '}';
    }

    //Implemented Methods

    public ObservableList<Doctor> viewDoclist() {
        ObservableList<Doctor> d = FXCollections.observableArrayList();
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
                //...
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { 
            }
        }
        return d;
    }

    public ObservableList<VisitBill> getVisitBillsList() {
        ObservableList<VisitBill> vb = FXCollections.observableArrayList();
        for(VisitBill i: patVisitBills){
            vb.add(i);
        }
        
        return vb;
    }

    public ObservableList<TestBill> getPatTestBillslist() {
        ObservableList<TestBill> tb = FXCollections.observableArrayList();
        for(TestBill i: patTestBills){
            tb.add(i);
        }
        
        return tb;
    }

    public ArrayList<Test> getTestReportStatusList() {
        ArrayList<Test> t = new ArrayList<Test>();
        File f = null;
        FileInputStream fis = null;
        Test temp = new Test();
        
        ObjectInputStream ois = null;
        try {
            f = new File("testObject.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    temp = (Test)ois.readObject();
                    if(temp.getPatID()==this.id){
                        t.add(temp);
                    }
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
        
        return t;
    }

    public ObservableList<Prescription> getPrescriptionList() {
        ObservableList<Prescription> pres = FXCollections.observableArrayList();
        for(Prescription j: patPrescription){
            pres.add(j);
        }
        return pres;
    }
    
    
}
