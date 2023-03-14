package Nonuser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class TestBill extends Bill implements Serializable{
    private Float due;
    private String testName;

    public TestBill() {
    }

    public TestBill(Float due, String testName) {
        this.due = due;
        this.testName = testName;
    }

    public TestBill(int billId, Float due, String testName, Float billAmount, String billDate, String branch) {
        super(billId, billAmount, billDate, branch);
        this.due = due;
        this.testName = testName;
    }

    public Float getDue() {
        return due;
    }

    public void setDue(Float due) {
        this.due = due;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    //Implemented Methods...
      
    public TestBill setTestBill(int billid, Float due, String testName, Float billAmount, String billDate, String branch) {
        this.setBill(billid, billAmount, billDate, branch);
        this.due = due;
        this.testName = testName;
        
        return this;
    }
    
    public static ArrayList<TestBill> getTestBills() {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        ArrayList<TestBill> tblist = new ArrayList<TestBill>();
        
        try {
                f = new File("testbillsobject.bin");
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                try{
                    while(true){
                        tblist.add((TestBill)ois.readObject());
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
        return tblist;
        
    }

    
    
    
    
}
