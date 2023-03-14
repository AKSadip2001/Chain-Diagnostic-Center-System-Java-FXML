package mainpkg;

import Nonuser.Test;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import users.Laboratorian;
import users.Patient;


public class GenerateReportLaboratorianController implements Initializable {

    @FXML
    private TextField patidtextfield;
    @FXML
    private TextField testidtextfield;
    @FXML
    private TextArea reportdestextarea;

    private Image image;
    private Laboratorian l;
    
    
    public void initData(Laboratorian i){
        l=i;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        image=null;
    }    

    @FXML
    private void addimgonclick(ActionEvent event) throws MalformedURLException {
        FileChooser fc2 = new FileChooser();
        fc2.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.bmp", "*.png"));
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Do you want to add an Image?");
        Optional<ButtonType> result = a.showAndWait();
        if(result.get() == ButtonType.OK){ 
            File imageFile = fc2.showOpenDialog(null);
            String imagePath = imageFile.getAbsolutePath();
            ImageData data = ImageDataFactory.create(imagePath);
            image = new Image(data);
            image.setAutoScale(true);

            a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("The image is added successfully.");
            a.showAndWait();
        }
    }

    @FXML
    private void generatereportonclick(ActionEvent event) {
        ArrayList<Patient> p;
        ArrayList<Test> t;
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        p = new ArrayList<Patient>();
        
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
                //...
            }          
        } catch (IOException ex) { } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        t = new ArrayList<Test>();
        DataInputStream dis = null;
        
        
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
        } catch (IOException ex) { 
            //..
        } 
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
        Patient ptemp = new Patient();
        for(Patient i: p){
            if(i.getId()==Integer.parseInt(patidtextfield.getText())){
                ptemp=i;
                break;
            }
        }
        
        Test ttemp = new Test();
        for(Test i: t){
            if(i.getTestID()==Integer.parseInt(testidtextfield.getText())){
                ttemp=i;
                break;
            }
        }
        
        try{
            FileChooser fc1 = new FileChooser();
            fc1.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
            f = fc1.showSaveDialog(null);
            if(f!=null){              
                PdfWriter pw = new PdfWriter(new FileOutputStream(f));
                PdfDocument pdf =  new PdfDocument(pw);
                pdf.addNewPage();
                Document doc = new Document(pdf);
                doc.setLeftMargin(70);
                
                
                String newline = "\n";
                Paragraph lineSpace = new Paragraph(newline);
                lineSpace.setHeight(8);
                
                String patlabelheading = "Patient Details:\n";
                Paragraph patlabel = new Paragraph(patlabelheading);
                patlabel.setBold();
                
                String paraText1 
                        = "ID: " + patidtextfield.getText()+"\n"
                        + "Name: " + ptemp.getPatName()+"\n"
                        + "Age: " + ptemp.getPatAge()+"\n"
                        + "Gender: " + ptemp.getPatGender()+"\n"
                        + "Contact: " + ptemp.getPatContact()+"\n";
                Paragraph para1 = new Paragraph(paraText1);
                
                Text titleText = new Text("REPORT of "+ttemp.getTestName());
                titleText.setFontSize(18f);
                Paragraph pageTitle = new Paragraph(titleText);
                pageTitle.setBold();    //OR titleText.setBold();
                pageTitle.setTextAlignment(TextAlignment.CENTER);

                
                doc.add(pageTitle);
                doc.add(lineSpace);
                doc.add(patlabel);
                doc.add(para1);
                doc.add(lineSpace);
                
                if(image!=null){
                    doc.add(image);
                    doc.add(lineSpace);
                }
                
                
                String paraText2 = "REPORT DESCRIPTION:\n";
                Paragraph para2 = new Paragraph(paraText2);
                para2.setBold();
                doc.add(para2);
                
                String paraText3 = reportdestextarea.getText();
                Paragraph para3 = new Paragraph(paraText3);
                doc.add(para3);
                doc.add(lineSpace);
                
                
                doc.close();
                
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("The PDF is saved successfully.");
                a.showAndWait();    
            }
            else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("Saving as PDF: cancelled!");
                a.showAndWait();               
            }
        }
        catch(FileNotFoundException e){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Oops! Exception: " + e.toString()+ " occured.");
        }
        
        l.generateReport(t, Integer.parseInt(testidtextfield.getText()), reportdestextarea.getText(), ptemp);
        
    }

    @FXML
    private void backonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LaboratorianDashboard.fxml"));
        Parent lds = loader.load();
        Scene newScene = new Scene(lds);
        LaboratorianDashboardController controller = loader.getController();
        controller.initData(l);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newScene);
        window.show();
    }
    
}
