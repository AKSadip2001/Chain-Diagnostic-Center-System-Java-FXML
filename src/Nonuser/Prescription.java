package Nonuser;

import java.io.Serializable;
import java.util.ArrayList;


public class Prescription implements Serializable{
    private int presNo;
    private String diseaseName;
    private String docName, date;
    private ArrayList<String> medName;
    private ArrayList<String> testName;

    public Prescription() {
        this.medName = new ArrayList<String>();
        this.testName = new ArrayList<String>();
    }

    public Prescription(int presNo, String diseaseName, String docName, String date, ArrayList<String> medName, ArrayList<String> testName) {
        this.presNo=presNo;
        this.diseaseName = diseaseName;
        this.docName = docName;
        this.date = date;
        this.medName = medName;
        this.testName = testName;
    }

    public int getPresNo() {
        return presNo;
    }

    public void setPresNo(int presNo) {
        this.presNo = presNo;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    public String getDiseaseName() {
        return diseaseName;
    }

    public ArrayList<String> getMedName() {
        return medName;
    }

    public ArrayList<String> getTestName() {
        return testName;
    }

    public Prescription setprescription(int presNo, String docName, String dName, ArrayList<String> medName, ArrayList<String> testName, String date) {
        this.presNo=presNo;
        this.docName=docName;
        this.diseaseName=dName;
        this.date = date;
        this.medName = medName;
        this.testName = testName;
        
        return this;
        
    }

    
    
    
    
}
