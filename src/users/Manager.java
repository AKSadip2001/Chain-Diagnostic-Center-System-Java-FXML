package users;

import Nonuser.Commission;
import Nonuser.Expense;
import Nonuser.TestBill;
import Nonuser.VisitBill;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Manager extends Staff implements Serializable{
    private String managerPosition; 

    public Manager() {
    }

    public Manager(int id, String password, String staffName, String staffAge, String staffContact, String satffAdd, String managerPosition) {
        super(staffName, staffAge, staffContact, satffAdd, id, password);
        this.managerPosition=managerPosition;
    }
    
    //Implemented Methods...

    public ArrayList<Expense> getExpList(String b) {
        ArrayList<Expense> exptemp = new ArrayList<Expense>();
        ArrayList<Expense> exptemp2 = new ArrayList<Expense>();
        
        exptemp = Expense.getExpenseList();
        
        for(Expense e: exptemp){
            if(e.getExpBranch().equals(b)){
                exptemp2.add(e);
            }
        }
        
        return exptemp2;
    }

    @Override
    public Expense addexp(String expname, float amount, String date) {
        return new Expense(expname, this.id, "NULL", date, amount);
    }
    
    public Expense addexp(String expname, float amount, String branch, String date) {
        return new Expense(expname, this.id, branch, date, amount);
    }
    
    public void addDocCommission(String docid, float amount, String date, String note) {
        Commission c = new Commission(amount, date, note);
        c.addCommission();
        File f = null;
        FileOutputStream fos = null;
        
        ArrayList<Doctor> dtemp = new ArrayList<Doctor>();
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        try {
            f = new File("docObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    dtemp.add((Doctor)ois.readObject());
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
        
        ObjectOutputStream oos = null;
        try {
            f = new File("docObjects.bin");
            
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            for(Doctor i: dtemp){
                if(i.getId()==Integer.parseInt(docid)){
                    i.getComList().add(c);
                }
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
    

    public Float[] getStatisticalData(String b) {
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        
        ArrayList<Doctor> dtemp = new ArrayList<Doctor>();
        ArrayList<VisitBill> vblist = new ArrayList<VisitBill>();
        ArrayList<TestBill> tblist = new ArrayList<TestBill>();
        
        Float data[] = new Float[4];
        for(int i=0; i<4; i++){
            data[i] = 0f;
        }
        
        try {
            f = new File("docObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    dtemp.add((Doctor)ois.readObject());
                }
            }
            catch(IOException | ClassNotFoundException e){
                //...
                for(Doctor i: dtemp){
                    if(i.getDocBranch().equals(b)){
                        for(Commission c: i.getComList()){
                            data[0] += c.getComAmount();
                        }
                    }
                }
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
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
                    for(VisitBill i: vblist){
                        if(i.getBranch().equals(b)){
                            data[1] += i.getBillAmount();
                        }
                    }
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
                    for(TestBill i: tblist){
                        if(i.getBranch().equals(b)){
                            data[2] += i.getBillAmount();
                        }
                    }
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
        
        
        Expense temp = new Expense();
        
        DataInputStream dis = null;
        try {
            f = new File("expenses.bin");
            if(f.exists()){
                fis = new FileInputStream(f);
                dis = new DataInputStream(fis);
                while(true){
                    temp.setExpName(dis.readUTF());
                    temp.setExpAmount(dis.readFloat());
                    temp.setStaffId(dis.readInt());
                    temp.setExpBranch(dis.readUTF());
                    temp.setDate(dis.readUTF());
                    if(temp.getExpBranch().equals(b)){
                        data[3] += temp.getExpAmount();
                    }
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
        
        return data;
    }

    
    
    
}
