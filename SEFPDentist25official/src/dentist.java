import java.sql.*;
//import java.text.SimpleDateFormat;
import java.util.Scanner;

public class dentist {
    static final String driverName="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/dentistsdb";
    static final String USER = "root";
    static final String pas = "";
    static String strSQL="select patient_id, first_name, last_name, date_of_birth from patients order by last_name";
    

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, pas);
            Statement stmt = conn.createStatement();
        
            ResultSet rs = stmt.executeQuery(strSQL);) {
                while (rs.next())
                {
                    System.out.print(rs.getInt("patient_id") + " ");
                    System.out.print(rs.getString("first_name") + " ");
                    System.out.print(rs.getString("last_name") + " ");
                    System.out.print(rs.getString("date_of_birth")+ ", ");
                    
                }

                Scanner input=new Scanner(System.in);
				System.out.print("\nType 'a' to add, 'u' to update, 'd' to delete: ");
				String strChoice=String.valueOf(input.next().charAt(0));
			
			
			switch (strChoice)
			{
				case "a":
                    System.out.println("===ADD===");
					System.out.print("enter SSN: ");
					int SSN=input.nextInt();
                
					System.out.println("===ADD===");
					System.out.print("enter first_name: ");
					String strFirstname=input.next();
					
					System.out.print("enter last_name: ");
					String strLastname=input.next();
					
					System.out.print("enter phone: ");
					String strPhone=input.next();

                    // System.out.print("enter email: ");
					// String strEmail=input.next();

                    // System.out.print("enter DOB: ");
					// String strDOB=input.next();
                    //Date strDOB1 = new SimpleDateFormat("dd-MM-yyyy").parse(strDOB); 

					
					strSQL="insert into patients values('" + SSN + "', '" + strFirstname + "', '" + strLastname + "', '" + strPhone + "', ";
					strSQL=strSQL + "'email', '1993-03-03', 'address', 'city', 'state', 'zipecode', 'F')";
					System.out.println(strSQL);
					stmt.executeUpdate(strSQL);
					break;
					
				case "u":
					System.out.println("===UPDATE===");
					System.out.print("enter id of patient to update: ");
					int id=input.nextInt();
					
					System.out.print("enter new lastname or 'x' to skip: ");
					strLastname=input.next();
					
					System.out.print("enter new firstname or 'x' to skip: ");
					strFirstname=input.next();
					
					System.out.print("enter new phone or 'x' to skip: ");
					strPhone=input.next();
					
					if (!strLastname.equals("x"))
					{
						strSQL="update patients set lastname='" + strLastname + "' where patient_id=" + id;
						System.out.println(strSQL);
						stmt.executeUpdate(strSQL);
					}
					
					if (!strFirstname.equals("x"))
					{
						strSQL="update patients set firstname='" + strFirstname + "' where patient_id=" + id;
						System.out.println(strSQL);
						stmt.executeUpdate(strSQL);
					}
					
					if (!strPhone.equals("x"))
					{
						strSQL="update patients set dob='" + strPhone + "' where patient_id=" + id;
						System.out.println(strSQL);
						stmt.executeUpdate(strSQL);
					}
					break;
					
				case "d":
					System.out.println("===DELETE===");
					System.out.print("enter id of patient to delete: ");
					id=input.nextInt();
					strSQL="delete from patients where patient_id=" + id;
					System.out.println(strSQL);
					stmt.executeUpdate(strSQL);
					break;
					
			}
			rs.close();
			conn.close();


            } catch (SQLException ex){ ex.printStackTrace();} // was ClassNotFoundException
            catch (Exception e) {e.printStackTrace();}	 // was SQLException

    }
         
    
}
