package Nonuser;

import java.io.Serializable;

public abstract class Bill implements Serializable{
    protected int billId;
    protected Float billAmount;
    protected String billDate, branch;

    public Bill() {
    }

    public Bill(int billId, Float billAmount, String billDate, String branch) {
        this.billId = billId;
        this.billAmount = billAmount;
        this.billDate = billDate;
        this.branch = branch;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Float billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public void setBill(int billId, Float billAmount, String billDate, String branch){
        this.billId = billId;
        this.billAmount = billAmount;
        this.billDate = billDate;
        this.branch = branch;
    }
}
