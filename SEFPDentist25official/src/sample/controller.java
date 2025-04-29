package sample;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class controller {
    @FXML
    private Label dentistLabel;
    
    public void connectButton(ActionEvent event) {
        DatabaseConnection connectNow =new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String connectQuery= "SELECT * FROM dentist";
        try {
            java.sql.Statement statement = connectDB.createStatement();
            java.sql.ResultSet queryResult = statement.executeQuery(connectQuery);
            while (queryResult.next()) {
                String name = queryResult.getString("name");
                String surname = queryResult.getString("surname");
                String email = queryResult.getString("email");
                System.out.println(name + " " + surname + " " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
