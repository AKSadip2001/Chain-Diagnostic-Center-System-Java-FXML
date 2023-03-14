package users;

import Nonuser.Expense;
import java.io.Serializable;

public abstract class Staff extends userclass implements Serializable{
    protected String staffName, staffAge, staffContact, satffAdd;

    public Staff() {
    }

    public Staff(String staffName, String staffAge, String staffContact, String satffAdd, int id, String password) {
        super(id, password);
        this.staffName = staffName;
        this.staffAge = staffAge;
        this.staffContact = staffContact;
        this.satffAdd = satffAdd;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffAge() {
        return staffAge;
    }

    public void setStaffAge(String staffAge) {
        this.staffAge = staffAge;
    }

    public String getStaffContact() {
        return staffContact;
    }

    public void setStaffContact(String staffContact) {
        this.staffContact = staffContact;
    }

    public String getSatffAdd() {
        return satffAdd;
    }

    public void setSatffAdd(String satffAdd) {
        this.satffAdd = satffAdd;
    }
    
    /**
     *
     * @param expname
     * @param amount
     * @param date
     * @return
     */
    public abstract Expense addexp(String expname, float amount, String date);
    
}
