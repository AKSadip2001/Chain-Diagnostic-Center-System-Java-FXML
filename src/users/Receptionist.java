package users;

import Nonuser.Appointment;
import Nonuser.Expense;
import Nonuser.Test;
import Nonuser.TestBill;
import Nonuser.VisitBill;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mainpkg.AddpatientsceneController;
import mainpkg.AppendableObjectOutputStream;
import mainpkg.ScheduleappointmentsceneController;

public class Receptionist extends Staff implements Serializable{
    private String recptionistBranch;
    
    public Receptionist() {
    }

    public Receptionist(int id, String password, String recptionistBranch, String staffName, String staffAge, String staffContact, String satffAdd) {
        super(staffName, staffAge, staffContact, satffAdd, id, password);
        this.recptionistBranch = recptionistBranch;
    }

    public String getRecptionistBranch() {
        return recptionistBranch;
    }

    public void setRecptionistBranch(String recptionistBranch) {
        this.recptionistBranch = recptionistBranch;
    }
    
    
    //IMPLEMENTED METHODS...

    public Patient addPatient(String name, String contact, String add, String gender, int age) {
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        ArrayList<Patient>plist = new ArrayList<Patient>();
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        
        try {
            f = new File("patObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    plist.add((Patient)ois.readObject());
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
        
        Patient p;
        if(!plist.isEmpty()){
            p = new Patient(plist.get(plist.size()-1).getId()+1, "123", name, contact, add, gender, age);
        }
        else{
            p = new Patient(101, "123", name, contact, add, gender, age);
        }
        
        try {
            f = new File("PatObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
                
            oos.writeObject(p);

        } catch (IOException ex) {
            Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                Logger.getLogger(AddpatientsceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return p;
    }
    
    public void addAppointment(Doctor d, int parseInt, String patName, String patContact, String text) {
        d.getAppList().add(new Appointment(parseInt, patName, patContact, text));
        
    }
    
    public  TestBill addTestBill(int billid, Float due, String testName, Float billAmount, String billDate, String branch) {
        TestBill tb = new TestBill().setTestBill(billid, due, testName, billAmount, billDate, branch);
        return tb;
    }

    public VisitBill addVisitBill(int billId, String docname, Float amount, String date, String branch) {
        VisitBill vb = new VisitBill().setVisitBill(billId, docname, amount, date, branch);
        return vb;
    }

    @Override
    public Expense addexp(String expname, float amount, String date) {
        return new Expense(expname, this.id, this.recptionistBranch, date, amount);
    }

    public Patient updatedue(Patient p, TestBill temp, String updatedDue) {
        for(TestBill i: p.getPatTestBills()){
            if(i.getBillId()==temp.getBillId()){
                i.setDue(Float.parseFloat(updatedDue));
                break;
            }
        }
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        ArrayList<TestBill> tb = TestBill.getTestBills();
        
        try {
            f = new File("testbillsobject.bin");
            
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            for(TestBill i: tb){
                if(i.getBillId()==i.getBillId()){
                    i.setDue(Float.parseFloat(updatedDue));
                }
                oos.writeObject(i);
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
        
        return p;
    }

    public void addtest(int patid, String date, String testname, String teststat, String branch) {
        Test.setTest(patid, date, testname, teststat, branch);
    }
    
}
