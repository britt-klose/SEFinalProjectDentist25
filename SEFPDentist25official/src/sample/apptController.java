package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.sql.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class apptController {
  @FXML
  dentist connectNow =new dentist();
  Connection connectDB = connectNow.getConnection();
      public Label bookedMessage;
    public TextArea idResults, dateResults;
    public TextField searchID, searchDate, idField, procedureField, dateField, dentistField, assistantField, noteField;

 @FXML
    public void searchApptID(ActionEvent event) {
        String patientID = searchID.getText();
        int parsedInt=Integer.parseInt(patientID);
      
        String connectQuery = "SELECT procedure_id, procedure_date, dentist, assistant, notes FROM patient_history where patient_id ="+ parsedInt;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);
            StringBuilder results = new StringBuilder();
            while(queryOutput.next()){
              String procedureId= queryOutput.getString("procedure_id");
              String procedure_date= queryOutput.getString("procedure_date");
              String dentist= queryOutput.getString("dentist");
              String assistant= queryOutput.getString("assistant");
              String notes= queryOutput.getString("notes");

              results.append("Procedure ID: ").append(procedureId).append("\n")
                     .append("Date: ").append(procedure_date).append("\n")
                     .append("Dentist: ").append(dentist).append("\n")
                     .append("Assistant: ").append(assistant).append("\n")
                     .append("Notes: ").append(notes).append("\n")
                     .append("----------------------\n");      
            }
            idResults.setText(results.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void searchApptDate(ActionEvent event) {
        String date = searchDate.getText();
      
        String connectQuery = "SELECT patient_id, procedure_id, dentist, assistant, notes FROM patient_history where procedure_date='"+ date + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);
            StringBuilder results = new StringBuilder();
            while(queryOutput.next()){
              String patientId= queryOutput.getString("patient_id");
              String procedureId= queryOutput.getString("procedure_id");
              String procedure_date= queryOutput.getString("procedure_date");
              String dentist= queryOutput.getString("dentist");
              String assistant= queryOutput.getString("assistant");
              String notes= queryOutput.getString("notes");

              results.append("Patient ID: ").append(patientId).append("\n")
              .append("Procedure ID: ").append(procedureId).append("\n")
              .append("Date: ").append(procedure_date).append("\n")
              .append("Dentist: ").append(dentist).append("\n")
              .append("Assistant: ").append(assistant).append("\n")
              .append("Notes: ").append(notes).append("\n")
              .append("----------------------\n");  
   
            }
            dateResults.setText(results.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void addApptButton(ActionEvent event) {
        String pID =idField.getText();
        String procedID = procedureField.getText();
        String date = dateField.getText();
        String dentist = dentistField.getText();
        String assistant = assistantField.getText();
        String note = noteField.getText();

        String connectQuery ="insert into patient_history values('" + pID + "', '" + procedID + "', '" + date + "', '" + dentist + "', '" + assistant + "', '" + note + "' )";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            bookedMessage.setText("Appointment added!");
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    @FXML
    public void menuButton(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
}
