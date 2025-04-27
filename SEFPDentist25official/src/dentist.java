import java.sql.*;
//import java.text.SimpleDateFormat;
import java.util.Scanner;

public class dentist {
    static final String driverName="com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/dentistsdb";
    static final String USER = "root";
    static final String pas = "";
    //static String strSQL="select patient_id, first_name, last_name, date_of_birth from patients order by last_name";
    static String strSQL;
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, pas);
            Statement stmt = conn.createStatement();
        
             ResultSet rs = stmt.executeQuery(strSQL);) {
            //     while (rs.next())
            //     {
            //         System.out.print(rs.getInt("patient_id") + " ");
            //         System.out.print(rs.getString("first_name") + " ");
            //         System.out.print(rs.getString("last_name") + " ");
            //         System.out.print(rs.getString("date_of_birth")+ ", ");
                    
            //     }

                Scanner input=new Scanner(System.in);
				System.out.print("Hell Welcome to Clean Dental Clinic!");
				System.out.print("\nSelect '1' for procedures, '2' for patients, '3' for staff");
				String strMenuOption=String.valueOf(input.next().charAt(0));

				switch(strMenuOption)
				{
					case "1": //Procedures
					System.out.println("===PROCEDURES===");
					System.out.print("\n Type 'v' to view all procedures, 's' to search, 'a' to add, 'u' to update, 'd' to delete: ");
					String procedureOption=String.valueOf(input.next().charAt(0));

					switch (procedureOption)
					{

						case "v":
							System.out.println("===PROCEDURE LIST===");
							strSQL="select * from procedures order by procedure_name";
							System.out.println(strSQL);
							break;

						case "s":
							System.out.println("===SEARCH===");
							System.out.print("enter procedure name: ");
							String strProcedureName=input.next();
							
							strSQL="select * from procedures where procedure_name='" + strProcedureName + "'";
							System.out.println(strSQL);
							break;
							
						case "a":
							System.out.println("===ADD NEW PROCEDURE===");
							System.out.print("Enter procedure ID: ");
							int pId=input.nextInt();
							System.out.print("Enter procedure name: ");
							String strPName=input.next();
							System.out.print("Enter procedure description: ");
							String strPDescription=input.next();
							System.out.print("Enter procedure start price: ");
							String strPrice=input.next();
							System.out.print("Enter Y or N for requires anesthesia: ");
							String strAnesthesisa=input.next();

							strSQL="insert into procedures values('" + pId + "', '" + strPName + "', '" + strPDescription + "', '" + strPrice + "', '" + strAnesthesisa + "')";
							stmt.executeUpdate(strSQL);
							System.out.println(strSQL);
							break;
							
						case "u":
							System.out.println("===UPDATE===");
							System.out.print("Enter id of procedure to update: ");
							pId=input.nextInt();

							System.out.print("Enter new procedure name or 'x' to skip: ");
							strPName=input.next();

							System.out.print("Enter new procedure description or 'x' to skip: ");
							strPDescription=input.next();

							System.out.print("Enter new procedure price or 'x' to skip: ");
							strPrice=input.next();

							System.out.print("Update to Y or N for requires anesthesia or 'x' to skip: ");
							strAnesthesisa=input.next();

									
							if (!strPName.equals("x"))
							{
								strSQL="update procedures set procedure_name='" + strPName + "' where procedure_id=" + pId;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}
							
							if (!strPDescription.equals("x"))
							{
								strSQL="update procedures set description='" + strPDescription + "' where procedure_id=" + pId;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}
							
							if (!strPrice.equals("x"))
							{
								strSQL="update procedures set start_price='" + strPrice + "' where procedure_id=" + pId;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}

							if (!strAnesthesisa.equals("x"))
							{
								strSQL="update procedures set requires_anestesia='" + strAnesthesisa + "' where procedure_id=" + pId;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}
							break;
							
						case "d":
							System.out.println("===DELETE===");
							System.out.print("Enter id of procedure to delete: ");
							pId=input.nextInt();
							strSQL="delete from procedures where procedure_id=" + pId;
							System.out.println(strSQL);
							stmt.executeUpdate(strSQL);
							break;

					}
					break;

	////////////////////////////////////////////////////////////////////////////////////////

					case "2": //Patients
					System.out.println("===PATIENTS===");
					System.out.print("\nType 'a' to add, 'u' to update, 'd' to delete: ");
					String patientChoice=String.valueOf(input.next().charAt(0));
					switch (patientChoice)
					{
						case "a":
							System.out.println("===ADD===");
							System.out.print("enter SSN: ");
							int SSN=input.nextInt();
						
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
								strSQL="update patients set last_name='" + strLastname + "' where patient_id=" + id;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}
							
							if (!strFirstname.equals("x"))
							{
								strSQL="update patients set first_name='" + strFirstname + "' where patient_id=" + id;
								System.out.println(strSQL);
								stmt.executeUpdate(strSQL);
							}
							
							if (!strPhone.equals("x"))
							{
								strSQL="update patients set phone='" + strPhone + "' where patient_id=" + id;
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
					break;

////////////////////////////////////////////////////////////////////////////////////////

					case "3": // Staff
					System.out.println("===STAFF===");
					System.out.print("\n Type 'd' to view all dentists, 'a' to view all assistants, 's' to search a dentist by name: ");
					String dentistOption=String.valueOf(input.next().charAt(0));

					switch(dentistOption)
					{
						case "d":
							System.out.println("===DENTIST LIST===");
							strSQL="select distinct dentist from patient_history order by dentist asc";
							System.out.println(strSQL);
							break;
						
						case "a":
							System.out.println("===ASSISTANT LIST===");
							strSQL="select distinct assistant from patient_history order by assistant asc";
							System.out.println(strSQL);
							break;

						case "s":
							System.out.println("===SEARCH===");
							System.out.println("Enter dentist name: ");
							String strDentistName=input.next();
							System.out.println("Loading " + strDentistName + "'s history ....");
							strSQL="select concat(patient.first_name, ' ', patient.last_name) as 'patient', procedure_name, procedure_date, assistant from patient_history inner join patients on patient_history.patient_id=patients.patient_id inner join procedures on patient_history.procedure_id=procedures.procedure_id where dentist='" + strDentistName + "'";
							System.out.println(strSQL);
							break;
					}

				}
			rs.close();
			conn.close();


            } catch (SQLException ex){ ex.printStackTrace();} // was ClassNotFoundException
            catch (Exception e) {e.printStackTrace();}	 // was SQLException

    }
         
    
}
