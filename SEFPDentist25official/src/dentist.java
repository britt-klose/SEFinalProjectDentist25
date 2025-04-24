import java.sql.*;

public class dentist {
    static final String DB_URL="jdbc:mysql://localhost/dentistsdb";
    static final String USER = "root";
    static final String pas = "";
    static final String QUERY = "SELECT first_name, last_name FROM patients";
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, pas);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
                while (rs.next()){
                    System.out.print("FirstName: " + rs.getString("first_name"));
                    System.out.print(", LastName: " + rs.getString("last_name"));
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

    }
         
    
}
