package users;

import Nonuser.Expense;
import Nonuser.Test;
import Nonuser.TestReport;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Laboratorian extends Staff implements Serializable {
    private String laboratorianBranch;

    public Laboratorian() {
    }
    
    public Laboratorian(int id, String password, String laboratorianBranch, String staffName, String staffAge, String staffContact, String satffAdd) {
        super(staffName, staffAge, staffContact, satffAdd, id, password);
        this.laboratorianBranch = laboratorianBranch;
    }
    
    public String getLaboratorianBranch() {
        return laboratorianBranch;
    }

    public void setLaboratorianBranch(String laboratorianBranch) {
        this.laboratorianBranch = laboratorianBranch;
    }
    
    /**
     *
     * @param expname
     * @param amount
     * @param date
     * @return
     */
    
    //Implemented Methods...
    
    @Override
    public Expense addexp(String expname, float amount, String date) {
        return new Expense(expname, this.id, this.laboratorianBranch, date, amount);
    }

    public ArrayList<Test> getReportsPendingList() {
        ArrayList<Test> t = new ArrayList<Test>();
        ArrayList<Test> tr = new ArrayList<Test>();
        t = Test.getTestList();
        
        for(Test i: t){
            if(i.getTestStat().equals("Test Done") && i.getBranch().equals(this.laboratorianBranch)){
                tr.add(i);
            }
        }
        
        return tr;
    }

    public ArrayList<Test> getPatientTestList(String patid) {
        ArrayList<Test> t = new ArrayList<Test>();
        ArrayList<Test> tp = new ArrayList<Test>();
        t = Test.getTestList();
        for(Test i: t){
            if(i.getPatID()==Integer.parseInt(patid) && i.getBranch().equals(this.laboratorianBranch)){
                tp.add(i);
            }
        }
        
        return tp;
    }

    public void updateTestStat(Test patTest) {
        ArrayList<Test> t = Test.getTestList();
        for(Test i: t){
            if(i.getTestID()==patTest.getTestID()){
                i.setTestStat("Test Done");
            }
        }
        
        File f = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            f = new File("testObject.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for(Test i: t){
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

    public ArrayList<Test> getTestPendingList() {
        ArrayList<Test> t = new ArrayList<Test>();
        ArrayList<Test> tp = new ArrayList<Test>();
        t = Test.getTestList();
        for(Test i: t){
            if(i.getTestStat().equals("Test Pending") && i.getBranch().equals(this.laboratorianBranch)){
                tp.add(i);
            }
        }
        
        return tp;
    }
    
    public void generateReport(ArrayList<Test> t, int testId, String des, Patient ptemp) {
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        Test temp = new Test();
        
        for(Test i: t){
            if(i.getTestID()==testId){
                i.setTestStat("Report Done");
                temp = i;
                break;
            }
        }
        
        try {
            f = new File("testObject.bin");
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            for(Test i: t){
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
        
        TestReport.setTestReport(testId, ptemp.getPatName(), temp.getPatID(), temp.getTestName(), des);
    }
    
}
