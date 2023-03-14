package Nonuser;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class TestReport implements Serializable{
    private int patId, testId;
    private String patName, reportName, reportDescription;

    public TestReport(int patId, int testId, String patName, String reportName, String reportDescription) {
        this.patId = patId;
        this.testId = testId;
        this.patName = patName;
        this.reportName = reportName;
        this.reportDescription = reportDescription;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
    
    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
    
    public static void setTestReport(int testId, String patName, int patId, String testName, String des) {
        File f = null;
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        
        try {
            f = new File("testReport.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
            } else {
                fos = new FileOutputStream(f);
            }
            
            dos = new DataOutputStream(fos);
            dos.writeInt(testId);
            dos.writeInt(patId);
            dos.writeUTF(patName);
            dos.writeUTF(testName);
            dos.writeUTF(des);
        } catch (IOException iOException) {
            //....
        }
         finally {
            try {
                if(dos != null) dos.close();
            } catch (IOException ex) {
                //Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
    

