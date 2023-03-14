
package Nonuser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import mainpkg.AppendableObjectOutputStream;


public class Test implements Serializable{

    
    private int testID, patID;
    private String date, testName, testStat, branch;

    public Test() {
    }

    public Test(int testID, int patID, String date, String testName, String testStat, String branch) {
        this.testID = testID;
        this.patID = patID;
        this.date=date;
        this.testName = testName;
        this.testStat = testStat;
        this.branch = branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    
    
    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }
    
    

    public int getPatID() {
        return patID;
    }

    public void setPatID(int patID) {
        this.patID = patID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestStat() {
        return testStat;
    }

    public void setTestStat(String testStat) {
        this.testStat = testStat;
    }
    
    public static ArrayList<Test> getTestList() {
        ArrayList<Test> t = new ArrayList<Test>();
        
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois =null;
        
        try {
            f = new File("testObject.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    t.add((Test)ois.readObject());
                }
            }
            catch(IOException | ClassNotFoundException e){
                //..
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) {
                //..
            }
        }
        
        return t;
    }
    
    public static void setTest(int patid, String date, String testname, String teststat, String branch) {
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois =null;
        FileOutputStream fos = null;
        ObjectOutputStream oos =null;
        ArrayList<Test> t = new ArrayList<Test>();
        
        try {
            f = new File("testObject.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    t.add((Test)ois.readObject());
                }
            }
            catch(IOException | ClassNotFoundException e){
                //...
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        Test temp;
        try {
            f = new File("testObject.bin");
            if(!t.isEmpty()){
                temp = new Test(t.get(t.size()-1).getTestID()+1, patid, date,  testname, teststat,  branch);
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
            }
            else{
                temp = new Test(10001, patid, date,  testname, teststat,  branch);
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos); 
            }
            oos.writeObject(temp);
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
    
}
