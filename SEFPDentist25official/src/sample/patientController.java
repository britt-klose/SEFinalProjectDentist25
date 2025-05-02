package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class patientController {
    @FXML
    dentist connectNow =new dentist();
    Connection connectDB = connectNow.getConnection();

    public Label firstNLabel, lastNLabel, deleteMessage, addMessage, updateMessage;
    @FXML
    public TextField searchField, ssnField, DOBField, firstNameField, lastnameField, phoneField, emailField, deletePatField;

    @FXML
    public void searchButton(ActionEvent event) {
        String patientID = searchField.getText();
        int parsedInt=Integer.parseInt(patientID);
      
        String connectQuery = "SELECT first_name, last_name FROM patients where patient_id ="+ parsedInt;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);
            while(queryOutput.next()){
                firstNLabel.setText(queryOutput.getString("first_name"));
                lastNLabel.setText(queryOutput.getString("last_name"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void delpatientButton(ActionEvent event) {
        String patientId = deletePatField.getText();
        int parsedId=Integer.parseInt(patientId);
        String connectQuery = "DELETE FROM patients where patient_id ="+ parsedId;
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            deleteMessage.setText("Patient successfully deleted!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addPatientButton(ActionEvent event) {
        String SSN = ssnField.getText();
        String strFirstname = firstNameField.getText();
        String strLastname = lastnameField.getText();
        String strPhone = phoneField.getText();
        String strEmail = emailField.getText();
        String strDOB = DOBField.getText();

        String connection ="insert into patients values('" + SSN + "', '" + strFirstname + "', '" + strLastname + "', '" + strPhone + "', '" + strEmail + "', '" + strDOB + "', ";
        String connectQuery = connection +  "'address', 'city', 'state', 'zipecode', 'F')";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            addMessage.setText(strFirstname + " was successfully added!");
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    @FXML
    public void updatePatientButton(ActionEvent event) {
        String SSN = ssnField.getText();
        String strFirstname = firstNameField.getText();
        String strLastname = lastnameField.getText();
        String strPhone = phoneField.getText();
        String strEmail = emailField.getText();
        String strDOB = DOBField.getText();

        String connectQuery ="update patients set first_name= '" + strFirstname + "', last_name= '" + strLastname + "', phone='" + strPhone + "', email='" + strEmail + "', date_of_birth='" + strDOB + "'where patient_id = '" + SSN + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            updateMessage.setText(strFirstname + " was successfully updated!");
        }catch(Exception e){
            e.printStackTrace();
        }
      
    }
    
    @FXML
    public void returntoMainMenu(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }

   
}
