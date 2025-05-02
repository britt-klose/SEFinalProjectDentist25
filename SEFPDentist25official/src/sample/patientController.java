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

    public Label firstNLabel, lastNLabel, deleteMessage;
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
            deleteMessage.setText("Patient successfully deleted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void addPatientButton(ActionEvent event) {
      
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
