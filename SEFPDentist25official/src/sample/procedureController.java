package sample;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class procedureController {
  @FXML
  dentist connectNow =new dentist();
  Connection connectDB = connectNow.getConnection();
    public Label addMessage, updateMessage;
    public TextArea procedureListField, procedureResultsField;
    public TextField searchProcedField, procedureid, procedureName, descriptionField, pricefield, Anesthesia;

  @FXML
    public void procedureListButton(ActionEvent event) {
      String connectQuery = "select procedure_name from procedures order by procedure_name asc";
      try{
          Statement statement = connectDB.createStatement();
          ResultSet queryOutput=statement.executeQuery(connectQuery);
          StringBuilder procedures = new StringBuilder();
          while(queryOutput.next()){
            procedures.append(queryOutput.getString("procedure_name")).append("\n");
          }
          procedureListField.setText(procedures.toString());

      }catch(Exception e){
          e.printStackTrace();
      }
    }

    @FXML
    public void searchProcedureBtn(ActionEvent event) {
        String procedureID = searchProcedField.getText();
        int parsedInt=Integer.parseInt(procedureID);
      
        String connectQuery = "SELECT procedure_name, description, start_price, requires_anestesia FROM procedures where procedure_id ="+ parsedInt;
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput=statement.executeQuery(connectQuery);
            StringBuilder results = new StringBuilder();
            while(queryOutput.next()){
              String procedureName= queryOutput.getString("procedure_name");
              String description= queryOutput.getString("description");
              String start_price= queryOutput.getString("start_price");
              String requires_anestesia= queryOutput.getString("requires_anestesia");

              results.append("Procedure Name: ").append(procedureName).append("\n")
                     .append("Description: ").append(description).append("\n")
                     .append("Start Price: ").append(start_price).append("\n")
                     .append("Requires Anesthesia: ").append(requires_anestesia).append("\n");
            }
            procedureResultsField.setText(results.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void addProcedureButton(ActionEvent event) {
        String pID =procedureid.getText();
        String name = procedureName.getText();
        String description = descriptionField.getText();
        String startPrice = pricefield.getText();
        String anesthesia = Anesthesia.getText();

        String connectQuery ="insert into procedures values('" + pID + "', '" + name + "', '" + description + "', '" + startPrice + "', '" + anesthesia + "' )'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            addMessage.setText("Procedure successfully added!");
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }

    @FXML
    public void updateProcedureButton(ActionEvent event) {
        String ProId = procedureid.getText();
        String name = procedureName.getText();
        String description = descriptionField.getText();
        String startPrice = pricefield.getText();
        String anesthesia = Anesthesia.getText();

        String connectQuery ="update procedures set procedure_name= '" + name + "', description= '" + description + "', start_price='" + startPrice + "', requires_anestesia='" + anesthesia + "'where procedure_id = '" + ProId + "'";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(connectQuery);
            updateMessage.setText("Procedure successfully updated!");
        }catch(Exception e){
            e.printStackTrace();
        }
      
    }

 @FXML
    public void returntoMain(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
    
}
