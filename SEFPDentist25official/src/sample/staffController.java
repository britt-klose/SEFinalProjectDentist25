package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;;

public class staffController {
  @FXML
  dentist connectNow =new dentist();
  Connection connectDB = connectNow.getConnection();
  public TextArea dentistListField, assistantListField, dentistResultsField;
  public TextField searchBarField;

  @FXML
    public void dentistListButton(ActionEvent event) {
      String connectQuery = "select distinct dentist from patient_history order by dentist asc";
      try{
          Statement statement = connectDB.createStatement();
          ResultSet queryOutput=statement.executeQuery(connectQuery);
          StringBuilder dentists = new StringBuilder();
          while(queryOutput.next()){
            dentists.append(queryOutput.getString("dentist")).append("\n");
          }
          dentistListField.setText(dentists.toString());

      }catch(Exception e){
          e.printStackTrace();
      }
    }

    @FXML
    public void assistantListButton(ActionEvent event) {
      String connectQuery = "select distinct assistant from patient_history order by assistant asc";
      try{
          Statement statement = connectDB.createStatement();
          ResultSet queryOutput=statement.executeQuery(connectQuery);
          StringBuilder assistants = new StringBuilder();
          while(queryOutput.next()){
            assistants.append(queryOutput.getString("assistant")).append("\n");
          }
          assistantListField.setText(assistants.toString());

      }catch(Exception e){
          e.printStackTrace();
      }
    }

    public void searchDentistBtn(ActionEvent event) {
      String strDentistName = searchBarField.getText();
      String connectQuery ="select patients.first_name, patients.last_name, procedure_name, procedure_date, assistant from patient_history inner join patients on patient_history.patient_id=patients.patient_id inner join procedures on patient_history.procedure_id=procedures.procedure_id where dentist='" + strDentistName + "'";
      try{
          Statement statement = connectDB.createStatement();
          ResultSet queryOutput=statement.executeQuery(connectQuery);
          StringBuilder results = new StringBuilder();
          while(queryOutput.next()){
            String firstName =queryOutput.getString("first_name");
            String lastName=queryOutput.getString("last_name");
            String procedure=queryOutput.getString("procedure_name");
            String date=queryOutput.getString("procedure_date");
            String assistant =queryOutput.getString("assistant");

            results.append("Patient: ")
                   .append(firstName).append(" ").append(lastName).append("\n")
                   .append("Procedure: ").append(procedure).append("\n")
                   .append("Date: ").append(date).append("\n")
                   .append("Assistant: ").append(assistant).append("\n")
                   .append("----------------------\n");
          }
          dentistResultsField.setText(results.toString());

      }catch(Exception e){
          e.printStackTrace();
      }
    }


    @FXML
    public void mainMenuButton(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
}
