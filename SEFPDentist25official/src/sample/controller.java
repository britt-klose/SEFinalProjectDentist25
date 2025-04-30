package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controller {
    @FXML
  

    public void patientButton(ActionEvent event)throws Exception{
      Parent root = FXMLLoader.load(getClass().getResource("patients.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
    
}
