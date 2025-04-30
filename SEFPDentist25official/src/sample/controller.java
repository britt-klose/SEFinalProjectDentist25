package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
  
    public void procedureButton(ActionEvent event)throws Exception{
      Parent root = FXMLLoader.load(getClass().getResource("procedure.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }

    @FXML
  

    public void staffButton(ActionEvent event)throws Exception{
      Parent root = FXMLLoader.load(getClass().getResource("staff.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }

    @FXML
  

    public void apptButton(ActionEvent event)throws Exception{
      Parent root = FXMLLoader.load(getClass().getResource("appt.fxml"));
      Scene scene = new Scene(root);
      Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    }
    
}
