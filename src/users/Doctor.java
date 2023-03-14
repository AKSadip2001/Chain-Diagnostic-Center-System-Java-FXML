package users;

import Nonuser.Appointment;
import Nonuser.Commission;
import Nonuser.Prescription;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Doctor extends userclass implements Serializable{
    private String docName, docSpeciality, docBranch, availabiltiyStat, docContact, visitingHour, visitingDays;
    private int age;
    private ArrayList<Appointment> appList;
    private ArrayList<Commission> comList;

    public Doctor() {
    }

    

    public Doctor(int id, String password, String docName, String docSpeciality, String docBranch, String availabiltiyStat, String docContact, String visitingHour, String visitingDays, int age) {
        super(id, password);
        this.docName = docName;
        this.docSpeciality = docSpeciality;
        this.docBranch = docBranch;
        this.availabiltiyStat = availabiltiyStat;
        this.docContact = docContact;
        this.visitingHour = visitingHour;
        this.visitingDays = visitingDays;
        this.age = age;
        this.appList = new ArrayList<Appointment>();
        this.comList = new ArrayList<Commission>();
    }

    public void setComList(ArrayList<Commission> comList) {
        this.comList = comList;
    }
    
    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocSpeciality() {
        return docSpeciality;
    }

    public void setDocSpeciality(String docSpeciality) {
        this.docSpeciality = docSpeciality;
    }

    public String getDocBranch() {
        return docBranch;
    }

    public void setDocBranch(String docBranch) {
        this.docBranch = docBranch;
    }

    public String getAvailabiltiyStat() {
        return availabiltiyStat;
    }

    public void setAvailabiltiyStat(String availabiltiyStat) {
        this.availabiltiyStat = availabiltiyStat;
    }

    public String getDocContact() {
        return docContact;
    }

    public void setDocContact(String docContact) {
        this.docContact = docContact;
    }

    public String getVisitingHour() {
        return visitingHour;
    }

    public void setVisitingHour(String visitingHour) {
        this.visitingHour = visitingHour;
    }

    public String getVisitingDays() {
        return visitingDays;
    }

    public void setVisitingDays(String visitingDays) {
        this.visitingDays = visitingDays;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    
    public ArrayList<Appointment> getAppList() {
        return appList;
    }

    public void setAppList(ArrayList<Appointment> appList) {
        this.appList = appList;
    }

    //Implemented Methods...
    
    public ArrayList<Commission> getComList() {
        return comList;
    }
    
    public Patient getPatInfo(int patid) {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        ArrayList<Patient> p = new ArrayList<Patient>();
        
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
                //
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        for(Patient i: p){
            if(i.getId()==patid){
                return i;
            }
        }
        return null;
    }

    public void updateAvaibilityStat(ArrayList<Doctor> dtemp, String s) {
        
        for(Doctor i: dtemp){
            if(i.getId()==this.getId()){
                i.availabiltiyStat=s;
            }
        }
        
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("docObjects.bin");
            
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            for(Doctor i: dtemp){
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
    }

    public void addprescription(Patient ptemp, String dName, ArrayList<String> medName, ArrayList<String> testName, String date) {
        Prescription pres = new Prescription();
        pres.setprescription(ptemp.getPatPrescription().size()+1, this.docName, dName, medName,  testName, date);
        ptemp.getPatPrescription().add(pres);
    }

    
   
    
    
}

    
