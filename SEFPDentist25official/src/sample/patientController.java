package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class patientController {
    @FXML
    private Label firstNLabel, lastNLabel;
    public TextField searchField;

    public void searchButton(ActionEvent event) {
        dentist connectNow =new dentist();
        Connection connectDB = connectNow.getConnection();
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


    public void returntoMainMenu(ActionEvent event) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }

   
}
